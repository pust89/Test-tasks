package com.pustovit.forajava.repository.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * This class is the response to a request from the service.
 *
 * Created by Pustovit Vladimir on 26.01.2020.
 * vovapust1989@gmail.com
 */

public class AlbumAndSongsResponseData
{
    @SerializedName("resultCount")
    @Expose
    private Integer resultCount;
    @SerializedName("results")
    @Expose
    private List<Song> songs = null;


    public AlbumAndSongsResponseData() {
    }

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

}
