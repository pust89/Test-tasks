package com.pustovit.forajava.repository.service;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Instance of Retrofit.
 *
 * Created by Pustovit Vladimir on 26.01.2020.
 * vovapust1989@gmail.com
 */

public class RetrofitInstance {

    private static Retrofit instance = null;
    private static final String BASE_URL = "https://itunes.apple.com/";

    public static synchronized MusicDataService getAlbumDataService() {

        if (instance == null) {

            OkHttpClient client = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(false)
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .writeTimeout(5, TimeUnit.SECONDS)
                    .readTimeout(5, TimeUnit.SECONDS)
                    .build();


            instance = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//RxJAVA
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .client(client)
                    .build();


        }

        return instance.create(MusicDataService.class);
    }

    private RetrofitInstance() {

    }

}
