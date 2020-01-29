package com.pustovit.kotlinversion.repository.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Pustovit Vladimir on 29.01.2020.
 * vovapust1989@gmail.com
 */

data class Song (
    @SerializedName("wrapperType") val wrapperType: String,
    @SerializedName("collectionType") val collectionType: String,
    @SerializedName("artistId") val artistId: Int,
    @SerializedName("collectionId") val collectionId: Int,
    @SerializedName("amgArtistId") val amgArtistId: Int,
    @SerializedName("artistName")val artistName: String,
    @SerializedName("collectionName") val collectionName: String,
    @SerializedName("collectionCensoredName") val collectionCensoredName: String,
    @SerializedName("artistViewUrl")val artistViewUrl: String,
    @SerializedName("collectionViewUrl") val collectionViewUrl: String,
    @SerializedName("artworkUrl60") val artworkUrl60: String,
    @SerializedName("artworkUrl100") val artworkUrl100: String,
    @SerializedName("collectionPrice") val collectionPrice: Double,
    @SerializedName("collectionExplicitness") val collectionExplicitness: String,
    @SerializedName("trackCount") val trackCount: Int,
    @SerializedName("copyright") val copyright: String,
    @SerializedName("country") val country: String,
    @SerializedName("currency")val currency: String,
    @SerializedName("releaseDate")val releaseDate: String,
    @SerializedName("primaryGenreName")val primaryGenreName: String,
    @SerializedName("kind")val kind: String,
    @SerializedName("trackId") val trackId: Int,
    @SerializedName("trackName") val trackName: String,
    @SerializedName("trackCensoredName")val trackCensoredName: String,
    @SerializedName("collectionArtistName") val collectionArtistName: String,
    @SerializedName("trackViewUrl") val trackViewUrl: String,
    @SerializedName("previewUrl") val previewUrl: String,
    @SerializedName("artworkUrl30")val artworkUrl30: String,
    @SerializedName("trackPrice") val trackPrice: Double,
    @SerializedName("trackExplicitness")val trackExplicitness: String,
    @SerializedName("discCount") val discCount: Int,
    @SerializedName("discNumber") val discNumber: Int,
    @SerializedName("trackNumber") val trackNumber: Int,
    @SerializedName("trackTimeMillis") val trackTimeMillis: Int,
    @SerializedName("isStreamable")val isStreamable: Boolean,
    @SerializedName("collectionArtistId") val collectionArtistId: Int,
    @SerializedName("collectionArtistViewUrl") val collectionArtistViewUrl: String
):Serializable

