package com.pustovit.kotlinversion.repository.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Pustovit Vladimir on 29.01.2020.
 * vovapust1989@gmail.com
 */

object RetrofitInstance {
    private var instance: Retrofit? = null
    private const val BASE_URL = "https://itunes.apple.com/"
    @Synchronized
    fun getAlbumDataService(): MusicDataService {
        if (instance == null) {
            val client = OkHttpClient.Builder()
                .retryOnConnectionFailure(false)
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build()
            instance = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //RxJAVA
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(client)
                .build()
        }
        return instance!!.create(MusicDataService::class.java)
    }
}