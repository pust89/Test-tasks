package com.pustovit.forajava.repository.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * This class is the response to a request from the service.
 *
 * Created by Pustovit Vladimir on 26.01.2020.
 * vovapust1989@gmail.com
 */

public class AlbumResponseData {

    @SerializedName("resultCount")
    @Expose
    private Integer albumCount;
    @SerializedName("results")
    @Expose
    private List<Album> albums = null;


    public AlbumResponseData() {
    }

    public Integer getAlbumCount() {
        return albumCount;
    }

    public void setAlbumCount(Integer resultCount) {
        this.albumCount = resultCount;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> results) {
        this.albums = results;
    }


}
