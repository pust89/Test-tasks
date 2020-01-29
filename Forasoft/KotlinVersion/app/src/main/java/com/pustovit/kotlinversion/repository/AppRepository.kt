package com.pustovit.kotlinversion.repository

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pustovit.kotlinversion.repository.model.Album
import com.pustovit.kotlinversion.repository.model.AlbumAndSongsResponseData
import com.pustovit.kotlinversion.repository.model.AlbumResponseData
import com.pustovit.kotlinversion.repository.model.Song
import com.pustovit.kotlinversion.repository.service.MusicDataService
import com.pustovit.kotlinversion.repository.service.RetrofitInstance
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * Created by Pustovit Vladimir on 29.01.2020.
 * vovapust1989@gmail.com
 */

class AppRepository {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    var mutableLiveDataSongs = MutableLiveData<ArrayList<Song>>()
    var mutableLiveDataAlbums = MutableLiveData<List<Album>>()

    private val musicDataService: MusicDataService = RetrofitInstance.getAlbumDataService()
    private val mutableLiveDataStatus: MutableLiveData<String> = MutableLiveData()
    private var albumsQueryMap: MutableMap<String, String>
    /**
     * The method sets album search options
     *
     * @param term        The URL-encoded text string you want to search for
     * @param countryCode The two-letter country code for the store you want to search. The search
     * uses the default store front for the specified country.
     * For example: US. The default is US.
     * @param limit       The number of search results you want the iTunes Store to return. 1 to 200
     */
    fun searchSettinsForAlbum(
        term: String,
        countryCode: String,
        limit: Int
    ) {
        albumsQueryMap = HashMap()
        albumsQueryMap["term"] = term
        albumsQueryMap["country"] = countryCode
        albumsQueryMap["limit"] = limit.toString()
    }

    /**
     * Album search method by terms.
     *
     *
     * This method processes the request and passes it to the service.
     * Then it processes the result and returns it.
     * This method also transfers the status of the work.(String "ok" or error message).
     *
     * @param albumName search query.
     * @return MutableLiveData with album list.
     */
    fun findAlbumsByName(albumName: String): MutableLiveData<List<Album>> {
        var albumName = albumName

        Log.d(TAG, "AppRepository:findAlbumsByName: albumName$albumName")


        if (!TextUtils.isEmpty(albumName)) {
            albumName = albumName.trim { it <= ' ' }
            albumName = albumName.replace(" ".toRegex(), "+")
            albumsQueryMap["term"] = albumName
            compositeDisposable.add(
                musicDataService.findAlbumsByName(albumsQueryMap)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .flatMap(Function<AlbumResponseData, Observable<List<Album>>> { albumResponseData ->
                        val tempList: MutableList<Album> = ArrayList<Album>()

                        tempList.addAll(albumResponseData.albums)

                        tempList.sortWith(Comparator { o1, o2 -> o1.collectionName.compareTo(o2.collectionName) })

                        Observable.just<List<Album>>(tempList)
                    }).subscribeWith(object : DisposableObserver<List<Album>>() {
                        private var tempStatus: String? = STATUS_OK


                        override fun onNext(albums: List<Album>) {
                            mutableLiveDataAlbums.postValue(albums)
                            tempStatus = STATUS_OK
                        }

                        override fun onError(e: Throwable) {
                            tempStatus = e.message
                            mutableLiveDataStatus.postValue(tempStatus)
                        }

                        override fun onComplete() {
                            Log.d(TAG, "onComplete: tempStatus: $tempStatus")
                            mutableLiveDataStatus.postValue(tempStatus)
                        }
                    }
                    ))
        }
        return mutableLiveDataAlbums
    }

    /**
     * Method for finding all the songs in an album.
     *
     *
     * This method processes the request and passes it to the service.
     * Then it processes the result and returns it.
     * This method also transfers the status of the work.(String "ok" or error message).
     *
     * @param albumId album id.
     * @return MutableLiveData with album list.
     */
    fun findSongsByAlbumId(albumId: Int): MutableLiveData<ArrayList<Song>> {


        compositeDisposable.add(
            musicDataService.findSongsByAlbumId(albumId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .concatMap(Function<AlbumAndSongsResponseData, Observable<ArrayList<Song>>> { albumAndSongsResponseData ->

                    val tempList: ArrayList<Song> = ArrayList<Song>()

                        tempList.addAll(albumAndSongsResponseData.songs)
                        tempList.removeAt(0)

                    tempList.sortWith(Comparator { o1, o2 -> o1.trackNumber.compareTo(o2.trackNumber) })

                    Observable.just<ArrayList<Song>>(tempList)
                }).subscribeWith(object : DisposableObserver<ArrayList<Song>>() {
                    private var tempStatus: String? = null
                    private var tempSongs: ArrayList<Song>? = null
                    override fun onNext(songs: ArrayList<Song>) {
                        tempSongs = songs
                        tempStatus = STATUS_OK
                    }

                    override fun onError(e: Throwable) {
                        tempStatus = e.message
                        mutableLiveDataStatus.postValue(tempStatus)
                    }

                    override fun onComplete() {
                        Log.d(
                            TAG,
                            "onComplete: tempStatus: $tempStatus"
                        )
                        mutableLiveDataStatus.postValue(tempStatus)
                        mutableLiveDataSongs.postValue(tempSongs)
                    }
                })
        )
        return mutableLiveDataSongs
    }

    /**
     * Method returns work status
     *
     * @return - LiveData with String status. May contain value "ok"
     * or an error string.(throwable.getMessage())
     */
    fun getStatus(): LiveData<String?> {
        return mutableLiveDataStatus
    }

    /**
     * Frees up resources.
     */
    fun clear() {
        compositeDisposable.dispose()
    }

    companion object {
        private const val TAG = "myTag"
        private const val STATUS_OK = "ok"
    }

    init {
        albumsQueryMap = HashMap()
        albumsQueryMap["term"] = ""
        albumsQueryMap["country"] = "us"
        albumsQueryMap["limit"] = "200"
    }

}