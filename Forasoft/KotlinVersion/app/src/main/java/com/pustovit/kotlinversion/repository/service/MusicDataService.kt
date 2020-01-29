package com.pustovit.kotlinversion.repository.service

import com.pustovit.kotlinversion.repository.model.AlbumAndSongsResponseData
import com.pustovit.kotlinversion.repository.model.AlbumResponseData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * Service API.
 *
 * Created by Pustovit Vladimir on 29.01.2020.
 * vovapust1989@gmail.com
 */

interface MusicDataService {

    @GET("search?entity=album&attribute=albumTerm")
    fun findAlbumsByName(@QueryMap queryMap: Map<String,String>): Observable<AlbumResponseData>

    @GET("lookup?entity=song")
    fun findSongsByAlbumId(@Query("id") albumId: Int):Observable<AlbumAndSongsResponseData>
}

