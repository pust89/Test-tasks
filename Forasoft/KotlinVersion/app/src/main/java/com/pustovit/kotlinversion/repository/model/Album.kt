package com.pustovit.kotlinversion.repository.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * The class is the entity of the Album.
 *
 * Created by Pustovit Vladimir on 29.01.2020.
 * vovapust1989@gmail.com
 */

data class Album(

    @SerializedName("wrapperType") val wrapperType : String,
    @SerializedName("collectionType") val collectionType : String,
    @SerializedName("artistId") val artistId : Int,
    @SerializedName("collectionId") val collectionId : Int,
    @SerializedName("artistName") val artistName : String,
    @SerializedName("collectionName") val collectionName : String,
    @SerializedName("collectionCensoredName") val collectionCensoredName : String,
    @SerializedName("artistViewUrl") val artistViewUrl : String,
    @SerializedName("collectionViewUrl") val collectionViewUrl : String,
    @SerializedName("artworkUrl60") val artworkUrl60 : String,
    @SerializedName("artworkUrl100") val artworkUrl100 : String,
    @SerializedName("collectionPrice") val collectionPrice : Double,
    @SerializedName("collectionExplicitness") val collectionExplicitness : String,
    @SerializedName("contentAdvisoryRating") val contentAdvisoryRating : String,
    @SerializedName("trackCount") val trackCount : Int,
    @SerializedName("copyright") val copyright : String,
    @SerializedName("country") val country : String,
    @SerializedName("currency") val currency : String,
    @SerializedName("releaseDate") val releaseDate : String,
    @SerializedName("primaryGenreName") val primaryGenreName : String
) : Serializable
