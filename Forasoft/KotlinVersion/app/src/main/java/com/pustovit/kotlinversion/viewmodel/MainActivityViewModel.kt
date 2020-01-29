package com.pustovit.kotlinversion.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.pustovit.kotlinversion.repository.AppRepository
import com.pustovit.kotlinversion.repository.model.Album
import com.pustovit.kotlinversion.repository.model.Song
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.*

/**
 * Created by Pustovit Vladimir on 29.01.2020.
 * vovapust1989@gmail.com
 */

class MainActivityViewModel: ViewModel() {
    private val TAG = "myTag"
    private var repository: AppRepository = AppRepository()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()



    /**
     * The method passes the request to the repository and returns the result.
     *
     * @param albumName search query.
     * @return  LiveData with album list.
     */
    fun findAlbumsByName(albumName: String): LiveData<List<Album>> {
        Log.d(TAG, "MainActivityViewModel:findAlbumsByName: albumName$albumName")
        return repository.findAlbumsByName(albumName)
    }

    /**
     * Method returns work status
     *
     * @return - LiveData with String status. May contain value "ok"
     * or an error string.(throwable.getMessage())
     */
    fun getStatus(): LiveData<String?>? {
        return repository.getStatus()
    }


    /**
     * The method passes the request to the repository.
     * @param albumId - album Id.
     * @return - LiveData with song list.
     */
    fun findSongsByAlbumId(albumId: Int): LiveData<ArrayList<Song>> {
        return repository.findSongsByAlbumId(albumId)
    }

    /**
     * Add Disposable from MainActivity to CompositeDisposable
     * @param disposable
     */
    fun addDisposable(disposable: Disposable?) {
        compositeDisposable.add(disposable!!)
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
        repository.clear()
    }
}