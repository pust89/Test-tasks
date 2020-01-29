package com.pustovit.forajava.repository.service;

import com.pustovit.forajava.repository.model.AlbumAndSongsResponseData;
import com.pustovit.forajava.repository.model.AlbumResponseData;

import java.util.Map;


import io.reactivex.Observable;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Service API.
 *
 */

public interface MusicDataService {

    @GET("search?entity=album&attribute=albumTerm")
    Observable<AlbumResponseData> findAlbumsByName(@QueryMap Map<String,String> queryMap);


    @GET("lookup?entity=song")
    Observable<AlbumAndSongsResponseData> findSongsByAlbumId(@Query("id") int albumsId);
}
