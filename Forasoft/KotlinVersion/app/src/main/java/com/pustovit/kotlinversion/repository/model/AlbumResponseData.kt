package com.pustovit.kotlinversion.repository.model

import com.google.gson.annotations.SerializedName

/**
 * This class is the response to a request from the service.
 *
 * Created by Pustovit Vladimir on 29.01.2020.
 * vovapust1989@gmail.com
 */

data class AlbumResponseData(
    @SerializedName("resultCount") val resultCount: Int,
    @SerializedName("results")  val albums: List<Album>
)


