package com.pustovit.forajava.repository.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The class is the entity of the Song.
 *
 * Created by Pustovit Vladimir on 26.01.2020.
 * vovapust1989@gmail.com
 */

public class Song implements Parcelable {

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
@SerializedName("amgArtistId")
@Expose
private Integer amgArtistId;
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
@SerializedName("kind")
@Expose
private String kind;
@SerializedName("trackId")
@Expose
private Integer trackId;
@SerializedName("trackName")
@Expose
private String trackName;
@SerializedName("trackCensoredName")
@Expose
private String trackCensoredName;
@SerializedName("collectionArtistName")
@Expose
private String collectionArtistName;
@SerializedName("trackViewUrl")
@Expose
private String trackViewUrl;
@SerializedName("previewUrl")
@Expose
private String previewUrl;
@SerializedName("artworkUrl30")
@Expose
private String artworkUrl30;
@SerializedName("trackPrice")
@Expose
private Double trackPrice;
@SerializedName("trackExplicitness")
@Expose
private String trackExplicitness;
@SerializedName("discCount")
@Expose
private Integer discCount;
@SerializedName("discNumber")
@Expose
private Integer discNumber;
@SerializedName("trackNumber")
@Expose
private Integer trackNumber;
@SerializedName("trackTimeMillis")
@Expose
private Integer trackTimeMillis;
@SerializedName("isStreamable")
@Expose
private Boolean isStreamable;
@SerializedName("collectionArtistId")
@Expose
private Integer collectionArtistId;
@SerializedName("collectionArtistViewUrl")
@Expose
private String collectionArtistViewUrl;
public final static Parcelable.Creator<Song> CREATOR=new Creator<Song>(){


@SuppressWarnings({
        "unchecked"
})
public Song createFromParcel(Parcel in){
        return new Song(in);
        }

public Song[]newArray(int size){
        return(new Song[size]);
        }

        }
        ;

protected Song(Parcel in){
        this.wrapperType=((String)in.readValue((String.class.getClassLoader())));
        this.collectionType=((String)in.readValue((String.class.getClassLoader())));
        this.artistId=((Integer)in.readValue((Integer.class.getClassLoader())));
        this.collectionId=((Integer)in.readValue((Integer.class.getClassLoader())));
        this.amgArtistId=((Integer)in.readValue((Integer.class.getClassLoader())));
        this.artistName=((String)in.readValue((String.class.getClassLoader())));
        this.collectionName=((String)in.readValue((String.class.getClassLoader())));
        this.collectionCensoredName=((String)in.readValue((String.class.getClassLoader())));
        this.artistViewUrl=((String)in.readValue((String.class.getClassLoader())));
        this.collectionViewUrl=((String)in.readValue((String.class.getClassLoader())));
        this.artworkUrl60=((String)in.readValue((String.class.getClassLoader())));
        this.artworkUrl100=((String)in.readValue((String.class.getClassLoader())));
        this.collectionPrice=((Double)in.readValue((Double.class.getClassLoader())));
        this.collectionExplicitness=((String)in.readValue((String.class.getClassLoader())));
        this.trackCount=((Integer)in.readValue((Integer.class.getClassLoader())));
        this.copyright=((String)in.readValue((String.class.getClassLoader())));
        this.country=((String)in.readValue((String.class.getClassLoader())));
        this.currency=((String)in.readValue((String.class.getClassLoader())));
        this.releaseDate=((String)in.readValue((String.class.getClassLoader())));
        this.primaryGenreName=((String)in.readValue((String.class.getClassLoader())));
        this.kind=((String)in.readValue((String.class.getClassLoader())));
        this.trackId=((Integer)in.readValue((Integer.class.getClassLoader())));
        this.trackName=((String)in.readValue((String.class.getClassLoader())));
        this.trackCensoredName=((String)in.readValue((String.class.getClassLoader())));
        this.collectionArtistName=((String)in.readValue((String.class.getClassLoader())));
        this.trackViewUrl=((String)in.readValue((String.class.getClassLoader())));
        this.previewUrl=((String)in.readValue((String.class.getClassLoader())));
        this.artworkUrl30=((String)in.readValue((String.class.getClassLoader())));
        this.trackPrice=((Double)in.readValue((Double.class.getClassLoader())));
        this.trackExplicitness=((String)in.readValue((String.class.getClassLoader())));
        this.discCount=((Integer)in.readValue((Integer.class.getClassLoader())));
        this.discNumber=((Integer)in.readValue((Integer.class.getClassLoader())));
        this.trackNumber=((Integer)in.readValue((Integer.class.getClassLoader())));
        this.trackTimeMillis=((Integer)in.readValue((Integer.class.getClassLoader())));
        this.isStreamable=((Boolean)in.readValue((Boolean.class.getClassLoader())));
        this.collectionArtistId=((Integer)in.readValue((Integer.class.getClassLoader())));
        this.collectionArtistViewUrl=((String)in.readValue((String.class.getClassLoader())));
        }

public Song(){
        }

public String getWrapperType(){
        return wrapperType;
        }

public void setWrapperType(String wrapperType){
        this.wrapperType=wrapperType;
        }

public String getCollectionType(){
        return collectionType;
        }

public void setCollectionType(String collectionType){
        this.collectionType=collectionType;
        }

public Integer getArtistId(){
        return artistId;
        }

public void setArtistId(Integer artistId){
        this.artistId=artistId;
        }

public Integer getCollectionId(){
        return collectionId;
        }

public void setCollectionId(Integer collectionId){
        this.collectionId=collectionId;
        }

public Integer getAmgArtistId(){
        return amgArtistId;
        }

public void setAmgArtistId(Integer amgArtistId){
        this.amgArtistId=amgArtistId;
        }

public String getArtistName(){
        return artistName;
        }

public void setArtistName(String artistName){
        this.artistName=artistName;
        }

public String getCollectionName(){
        return collectionName;
        }

public void setCollectionName(String collectionName){
        this.collectionName=collectionName;
        }

public String getCollectionCensoredName(){
        return collectionCensoredName;
        }

public void setCollectionCensoredName(String collectionCensoredName){
        this.collectionCensoredName=collectionCensoredName;
        }

public String getArtistViewUrl(){
        return artistViewUrl;
        }

public void setArtistViewUrl(String artistViewUrl){
        this.artistViewUrl=artistViewUrl;
        }

public String getCollectionViewUrl(){
        return collectionViewUrl;
        }

public void setCollectionViewUrl(String collectionViewUrl){
        this.collectionViewUrl=collectionViewUrl;
        }

public String getArtworkUrl60(){
        return artworkUrl60;
        }

public void setArtworkUrl60(String artworkUrl60){
        this.artworkUrl60=artworkUrl60;
        }

public String getArtworkUrl100(){
        return artworkUrl100;
        }

public void setArtworkUrl100(String artworkUrl100){
        this.artworkUrl100=artworkUrl100;
        }

public Double getCollectionPrice(){
        return collectionPrice;
        }

public void setCollectionPrice(Double collectionPrice){
        this.collectionPrice=collectionPrice;
        }

public String getCollectionExplicitness(){
        return collectionExplicitness;
        }

public void setCollectionExplicitness(String collectionExplicitness){
        this.collectionExplicitness=collectionExplicitness;
        }

public Integer getTrackCount(){
        return trackCount;
        }

public void setTrackCount(Integer trackCount){
        this.trackCount=trackCount;
        }

public String getCopyright(){
        return copyright;
        }

public void setCopyright(String copyright){
        this.copyright=copyright;
        }

public String getCountry(){
        return country;
        }

public void setCountry(String country){
        this.country=country;
        }

public String getCurrency(){
        return currency;
        }

public void setCurrency(String currency){
        this.currency=currency;
        }

public String getReleaseDate(){
        return releaseDate;
        }

public void setReleaseDate(String releaseDate){
        this.releaseDate=releaseDate;
        }

public String getPrimaryGenreName(){
        return primaryGenreName;
        }

public void setPrimaryGenreName(String primaryGenreName){
        this.primaryGenreName=primaryGenreName;
        }

public String getKind(){
        return kind;
        }

public void setKind(String kind){
        this.kind=kind;
        }

public Integer getTrackId(){
        return trackId;
        }

public void setTrackId(Integer trackId){
        this.trackId=trackId;
        }

public String getTrackName(){
        return trackName;
        }

public void setTrackName(String trackName){
        this.trackName=trackName;
        }

public String getTrackCensoredName(){
        return trackCensoredName;
        }

public void setTrackCensoredName(String trackCensoredName){
        this.trackCensoredName=trackCensoredName;
        }

public String getCollectionArtistName(){
        return collectionArtistName;
        }

public void setCollectionArtistName(String collectionArtistName){
        this.collectionArtistName=collectionArtistName;
        }

public String getTrackViewUrl(){
        return trackViewUrl;
        }

public void setTrackViewUrl(String trackViewUrl){
        this.trackViewUrl=trackViewUrl;
        }

public String getPreviewUrl(){
        return previewUrl;
        }

public void setPreviewUrl(String previewUrl){
        this.previewUrl=previewUrl;
        }

public String getArtworkUrl30(){
        return artworkUrl30;
        }

public void setArtworkUrl30(String artworkUrl30){
        this.artworkUrl30=artworkUrl30;
        }

public Double getTrackPrice(){
        return trackPrice;
        }

public void setTrackPrice(Double trackPrice){
        this.trackPrice=trackPrice;
        }

public String getTrackExplicitness(){
        return trackExplicitness;
        }

public void setTrackExplicitness(String trackExplicitness){
        this.trackExplicitness=trackExplicitness;
        }

public Integer getDiscCount(){
        return discCount;
        }

public void setDiscCount(Integer discCount){
        this.discCount=discCount;
        }

public Integer getDiscNumber(){
        return discNumber;
        }

public void setDiscNumber(Integer discNumber){
        this.discNumber=discNumber;
        }

public Integer getTrackNumber(){
        return trackNumber;
        }

public void setTrackNumber(Integer trackNumber){
        this.trackNumber=trackNumber;
        }

public Integer getTrackTimeMillis(){
        return trackTimeMillis;
        }

public void setTrackTimeMillis(Integer trackTimeMillis){
        this.trackTimeMillis=trackTimeMillis;
        }

public Boolean getIsStreamable(){
        return isStreamable;
        }

public void setIsStreamable(Boolean isStreamable){
        this.isStreamable=isStreamable;
        }

public Integer getCollectionArtistId(){
        return collectionArtistId;
        }

public void setCollectionArtistId(Integer collectionArtistId){
        this.collectionArtistId=collectionArtistId;
        }

public String getCollectionArtistViewUrl(){
        return collectionArtistViewUrl;
        }

public void setCollectionArtistViewUrl(String collectionArtistViewUrl){
        this.collectionArtistViewUrl=collectionArtistViewUrl;
        }

public void writeToParcel(Parcel dest,int flags){
        dest.writeValue(wrapperType);
        dest.writeValue(collectionType);
        dest.writeValue(artistId);
        dest.writeValue(collectionId);
        dest.writeValue(amgArtistId);
        dest.writeValue(artistName);
        dest.writeValue(collectionName);
        dest.writeValue(collectionCensoredName);
        dest.writeValue(artistViewUrl);
        dest.writeValue(collectionViewUrl);
        dest.writeValue(artworkUrl60);
        dest.writeValue(artworkUrl100);
        dest.writeValue(collectionPrice);
        dest.writeValue(collectionExplicitness);
        dest.writeValue(trackCount);
        dest.writeValue(copyright);
        dest.writeValue(country);
        dest.writeValue(currency);
        dest.writeValue(releaseDate);
        dest.writeValue(primaryGenreName);
        dest.writeValue(kind);
        dest.writeValue(trackId);
        dest.writeValue(trackName);
        dest.writeValue(trackCensoredName);
        dest.writeValue(collectionArtistName);
        dest.writeValue(trackViewUrl);
        dest.writeValue(previewUrl);
        dest.writeValue(artworkUrl30);
        dest.writeValue(trackPrice);
        dest.writeValue(trackExplicitness);
        dest.writeValue(discCount);
        dest.writeValue(discNumber);
        dest.writeValue(trackNumber);
        dest.writeValue(trackTimeMillis);
        dest.writeValue(isStreamable);
        dest.writeValue(collectionArtistId);
        dest.writeValue(collectionArtistViewUrl);
        }

public int describeContents(){
        return 0;
        }

        @Override
        public String toString() {
                return "Song{" +
                        "wrapperType='" + wrapperType + '\'' +
                        ", collectionType='" + collectionType + '\'' +
                        ", artistName='" + artistName + '\'' +
                        ", trackCount=" + trackCount +
                        ", trackNumber=" + trackNumber +
                        ", trackTimeMillis=" + trackTimeMillis +
                        '}';
        }
}
