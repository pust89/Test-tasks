package com.pustovit.forajava.viewmodel;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.pustovit.forajava.repository.model.Album;
import com.pustovit.forajava.repository.AppRepository;
import com.pustovit.forajava.repository.model.Song;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * MainActivityViewModel class.
 *
 * Created by Pustovit Vladimir on 27.01.2020.
 * vovapust1989@gmail.com
 *
 */
public class MainActivityViewModel extends ViewModel {
    private static final String TAG = "myTag";
    private AppRepository repository;
    private CompositeDisposable compositeDisposable;


    public MainActivityViewModel() {
        repository = new AppRepository();
        compositeDisposable = new CompositeDisposable();
    }

    /**
     *The method passes the request to the repository and returns the result.
     *
     * @param albumName search query.
     * @return  LiveData with album list.
     */
    public LiveData<List<Album>> findAlbumsByName(String albumName) {
        Log.d(TAG, "MainActivityViewModel:findAlbumsByName: albumName"+albumName);
        return repository.findAlbumsByName(albumName);
    }

    /**
     *Method returns work status
     *
     * @return - LiveData with String status. May contain value "ok"
     *              or an error string.(throwable.getMessage())
     */
    public LiveData<String> getStatus() {
        return repository.getStatus();
    }


    /**
     *The method passes the request to the repository.
     * @param albumId - album Id.
     * @return - LiveData with song list.
     */
    public LiveData<ArrayList<Song>> findSongsByAlbumId(int albumId) {
        return repository.findSongsByAlbumId(albumId);
    }

    /**
     * Add Disposable from MainActivity to CompositeDisposable
     * @param disposable
     */
    public void addDisposable(Disposable disposable){
        compositeDisposable.add(disposable);
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
        repository.clear();
    }
}