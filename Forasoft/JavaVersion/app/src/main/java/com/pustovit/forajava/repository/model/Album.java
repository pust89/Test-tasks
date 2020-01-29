package com.pustovit.forajava.repository.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The class is the entity of the Album.
 *
 * Created by Pustovit Vladimir on 26.01.2020.
 * vovapust1989@gmail.com
 */

public class Album implements Parcelable{

    @SerializedName("wrapperType")
    @Expose
    private String wrapperType;
    @SerializedName("collectionType")
    @Expose
    private String collectionType;
    @SerializedName("artistId")
    @Expose
    private Integer artistId;
    @SerializedName("collectionId")
    @Expose
    private Integer collectionId;
    @SerializedName("artistName")
    @Expose
    private String artistName;
    @SerializedName("collectionName")
    @Expose
    private String collectionName;
    @SerializedName("collectionCensoredName")
    @Expose
    private String collectionCensoredName;
    @SerializedName("artistViewUrl")
    @Expose
    private String artistViewUrl;
    @SerializedName("collectionViewUrl")
    @Expose
    private String collectionViewUrl;
    @SerializedName("artworkUrl60")
    @Expose
    private String artworkUrl60;
    @SerializedName("artworkUrl100")
    @Expose
    private String artworkUrl100;
    @SerializedName("collectionPrice")
    @Expose
    private Double collectionPrice;
    @SerializedName("collectionExplicitness")
    @Expose
    private String collectionExplicitness;
    @SerializedName("contentAdvisoryRating")
    @Expose
    private String contentAdvisoryRating;
    @SerializedName("trackCount")
    @Expose
    private Integer trackCount;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("releaseDate")
    @Expose
    private String releaseDate;
    @SerializedName("primaryGenreName")
    @Expose
    private String primaryGenreName;
    @SerializedName("amgArtistId")
    @Expose
    private Integer amgArtistId;
    public final static Parcelable.Creator<Album> CREATOR = new Parcelable.Creator<Album>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        public Album[] newArray(int size) {
            return (new Album[size]);
        }

    }
            ;

    protected Album(Parcel in) {
        this.wrapperType = ((String) in.readValue((String.class.getClassLoader())));
        this.collectionType = ((String) in.readValue((String.class.getClassLoader())));
        this.artistId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.collectionId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.artistName = ((String) in.readValue((String.class.getClassLoader())));
        this.collectionName = ((String) in.readValue((String.class.getClassLoader())));
        this.collectionCensoredName = ((String) in.readValue((String.class.getClassLoader())));
        this.artistViewUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.collectionViewUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.artworkUrl60 = ((String) in.readValue((String.class.getClassLoader())));
        this.artworkUrl100 = ((String) in.readValue((String.class.getClassLoader())));
        this.collectionPrice = ((Double) in.readValue((Double.class.getClassLoader())));
        this.collectionExplicitness = ((String) in.readValue((String.class.getClassLoader())));
        this.contentAdvisoryRating = ((String) in.readValue((String.class.getClassLoader())));
        this.trackCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.copyright = ((String) in.readValue((String.class.getClassLoader())));
        this.country = ((String) in.readValue((String.class.getClassLoader())));
        this.currency = ((String) in.readValue((String.class.getClassLoader())));
        this.releaseDate = ((String) in.readValue((String.class.getClassLoader())));
        this.primaryGenreName = ((String) in.readValue((String.class.getClassLoader())));
        this.amgArtistId = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Album() {
    }

    public String getWrapperType() {
        return wrapperType;
    }

    public void setWrapperType(String wrapperType) {
        this.wrapperType = wrapperType;
    }

    public String getCollectionType() {
        return collectionType;
    }

    public void setCollectionType(String collectionType) {
        this.collectionType = collectionType;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getCollectionCensoredName() {
        return collectionCensoredName;
    }

    public void setCollectionCensoredName(String collectionCensoredName) {
        this.collectionCensoredName = collectionCensoredName;
    }

    public String getArtistViewUrl() {
        return artistViewUrl;
    }

    public void setArtistViewUrl(String artistViewUrl) {
        this.artistViewUrl = artistViewUrl;
    }

    public String getCollectionViewUrl() {
        return collectionViewUrl;
    }

    public void setCollectionViewUrl(String collectionViewUrl) {
        this.collectionViewUrl = collectionViewUrl;
    }

    public String getArtworkUrl60() {
        return artworkUrl60;
    }

    public void setArtworkUrl60(String artworkUrl60) {
        this.artworkUrl60 = artworkUrl60;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }

    public Double getCollectionPrice() {
        return collectionPrice;
    }

    public void setCollectionPrice(Double collectionPrice) {
        this.collectionPrice = collectionPrice;
    }

    public String getCollectionExplicitness() {
        return collectionExplicitness;
    }

    public void setCollectionExplicitness(String collectionExplicitness) {
        this.collectionExplicitness = collectionExplicitness;
    }

    public String getContentAdvisoryRating() {
        return contentAdvisoryRating;
    }

    public void setContentAdvisoryRating(String contentAdvisoryRating) {
        this.contentAdvisoryRating = contentAdvisoryRating;
    }

    public Integer getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(Integer trackCount) {
        this.trackCount = trackCount;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public void setPrimaryGenreName(String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
    }

    public Integer getAmgArtistId() {
        return amgArtistId;
    }

    public void setAmgArtistId(Integer amgArtistId) {
        this.amgArtistId = amgArtistId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(wrapperType);
        dest.writeValue(collectionType);
        dest.writeValue(artistId);
        dest.writeValue(collectionId);
        dest.writeValue(artistName);
        dest.writeValue(collectionName);
        dest.writeValue(collectionCensoredName);
        dest.writeValue(artistViewUrl);
        dest.writeValue(collectionViewUrl);
        dest.writeValue(artworkUrl60);
        dest.writeValue(artworkUrl100);
        dest.writeValue(collectionPrice);
        dest.writeValue(collectionExplicitness);
        dest.writeValue(contentAdvisoryRating);
        dest.writeValue(trackCount);
        dest.writeValue(copyright);
        dest.writeValue(country);
        dest.writeValue(currency);
        dest.writeValue(releaseDate);
        dest.writeValue(primaryGenreName);
        dest.writeValue(amgArtistId);
    }

    public int describeContents() {
        return 0;
    }
    @Override
    public String toString() {
        return "Album{" +
                "collectionId=" + collectionId +
                ", collectionName='" + collectionName + '\'' +
                ", artworkUrl100='" + artworkUrl100 + '\'' +
                ", artistId=" + artistId +
                ", artistName='" + artistName + '\'' +
                '}';
    }
}
