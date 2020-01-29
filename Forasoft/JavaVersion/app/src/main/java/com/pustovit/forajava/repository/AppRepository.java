package com.pustovit.forajava.repository;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pustovit.forajava.repository.model.Album;
import com.pustovit.forajava.repository.model.AlbumAndSongsResponseData;
import com.pustovit.forajava.repository.model.Song;
import com.pustovit.forajava.repository.service.MusicDataService;
import com.pustovit.forajava.repository.model.AlbumResponseData;
import com.pustovit.forajava.repository.service.RetrofitInstance;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * Application repository.
 *
 * Created by Pustovit Vladimir on 25.01.2020.
 * vovapust1989@gmail.com
 */

public class AppRepository {
    private static final String TAG = "myTag";
    private static final String STATUS_OK = "ok";
    private CompositeDisposable compositeDisposable;

    private MusicDataService musicDataService;
    private MutableLiveData<List<Album>> mutableLiveDataAlbums;
    private MutableLiveData<ArrayList<Song>> mutableLiveDataSongs; //ArrayList because intent has only putParcelableArrayListExtra() method.
    private MutableLiveData<String> mutableLiveDataStatus;

    private Map<String, String> albumsQueryMap;

    public AppRepository() {
        this.musicDataService = RetrofitInstance.getAlbumDataService();
        compositeDisposable = new CompositeDisposable();
        this.mutableLiveDataStatus = new MutableLiveData<>();

        albumsQueryMap = new HashMap<>();
        albumsQueryMap.put("term", "");
        albumsQueryMap.put("country", "us");
        albumsQueryMap.put("limit", "200");
    }

    /**
     * The method sets album search options
     *
     * @param term        The URL-encoded text string you want to search for
     * @param countryCode The two-letter country code for the store you want to search. The search
     *                    uses the default store front for the specified country.
     *                    For example: US. The default is US.
     * @param limit       The number of search results you want the iTunes Store to return. 1 to 200
     */
    public void searchSettinsForAlbum(String term, String countryCode, int limit) {
        albumsQueryMap = new HashMap<>();
        albumsQueryMap.put("term", term);
        albumsQueryMap.put("country", countryCode);
        albumsQueryMap.put("limit", String.valueOf(limit));
    }

    /**
     * Album search method by terms.
     * <p>
     * This method processes the request and passes it to the service.
     * Then it processes the result and returns it.
     * This method also transfers the status of the work.(String "ok" or error message).
     *
     * @param albumName search query.
     * @return MutableLiveData with album list.
     */
    public MutableLiveData<List<Album>> findAlbumsByName(String albumName) {
        Log.d(TAG, "AppRepository:findAlbumsByName: albumName" + albumName);
        if (mutableLiveDataAlbums == null) {
            mutableLiveDataAlbums = new MutableLiveData<>();
        }
        if (!TextUtils.isEmpty(albumName)) {

            albumName = albumName.trim();

            albumName = albumName.replaceAll(" ", "+");

            albumsQueryMap.put("term", albumName);


            compositeDisposable.add(
                    musicDataService.findAlbumsByName(albumsQueryMap)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .flatMap(new Function<AlbumResponseData, Observable<List<Album>>>() {
                                @Override
                                public Observable<List<Album>> apply(AlbumResponseData albumResponseData) throws Exception {

                                    List<Album> tempList = new ArrayList<>();

                                    if (albumResponseData.getAlbums() != null) {
                                        tempList.addAll(albumResponseData.getAlbums());
                                    }

                                    Collections.sort(tempList, new Comparator<Album>() {

                                        /*
                                        Sort by name here.
                                         */
                                        @Override
                                        public int compare(Album o1, Album o2) {
                                            return o1.getCollectionName().compareTo(o2.getCollectionName());
                                        }
                                    });

                                    return Observable.just(tempList);
                                }
                            })
                            .subscribeWith(new DisposableObserver<List<Album>>() {
                                               private String tempStatus;
                                               private List<Album> tempAlbums;

                                               @Override
                                               public void onNext(List<Album> albums) {
                                                   tempAlbums = albums;
                                                   tempStatus = STATUS_OK;
                                               }

                                               @Override
                                               public void onError(Throwable e) {
                                                   tempStatus = e.getMessage();
                                                   mutableLiveDataStatus.postValue(tempStatus);
                                               }

                                               @Override
                                               public void onComplete() {
                                                   Log.d(TAG, "onComplete: tempStatus: " + tempStatus);
                                                   mutableLiveDataStatus.postValue(tempStatus);
                                                   mutableLiveDataAlbums.postValue(tempAlbums);
                                               }
                                           }

                            ));
        }

        return mutableLiveDataAlbums;
    }

    /**
     * Method for finding all the songs in an album.
     * <p>
     * This method processes the request and passes it to the service.
     * Then it processes the result and returns it.
     * This method also transfers the status of the work.(String "ok" or error message).
     *
     * @param albumId album id.
     * @return MutableLiveData with album list.
     */
    public MutableLiveData<ArrayList<Song>> findSongsByAlbumId(int albumId) {

        if (mutableLiveDataSongs == null) {
            mutableLiveDataSongs = new MutableLiveData<>();
        }
        compositeDisposable.add(
                musicDataService.findSongsByAlbumId(albumId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .concatMap(new Function<AlbumAndSongsResponseData, Observable<ArrayList<Song>>>() {
                            @Override
                            public Observable<ArrayList<Song>> apply(AlbumAndSongsResponseData albumAndSongsResponseData) throws Exception {

                                ArrayList<Song> tempList = new ArrayList<>();

                                if (albumAndSongsResponseData.getSongs() != null) {
                                    tempList.addAll(albumAndSongsResponseData.getSongs());
                                    tempList.remove(0);

                                    Collections.sort(tempList, new Comparator<Song>() {
                                        @Override
                                        public int compare(Song song1, Song song2) {
                                            return song1.getTrackNumber().compareTo(song2.getTrackNumber());
                                        }
                                    });
                                }
                                return Observable.just(tempList);
                            }
                        }).subscribeWith(new DisposableObserver<ArrayList<Song>>() {
                    private String tempStatus;
                    private ArrayList<Song> tempSongs;

                    @Override
                    public void onNext(ArrayList<Song> songs) {
                        tempSongs = songs;
                        tempStatus = STATUS_OK;
                    }

                    @Override
                    public void onError(Throwable e) {
                        tempStatus = e.getMessage();
                        mutableLiveDataStatus.postValue(tempStatus);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: tempStatus: " + tempStatus);
                        mutableLiveDataStatus.postValue(tempStatus);
                        mutableLiveDataSongs.postValue(tempSongs);
                    }
                }));


        return mutableLiveDataSongs;
    }

    /**
     * Method returns work status
     *
     * @return - LiveData with String status. May contain value "ok"
     * or an error string.(throwable.getMessage())
     */
    public LiveData<String> getStatus() {
        return mutableLiveDataStatus;
    }

    /**
     * Frees up resources.
     */
    public void clear() {
        compositeDisposable.dispose();
    }


}
