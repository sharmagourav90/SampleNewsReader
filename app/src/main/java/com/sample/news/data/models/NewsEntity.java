package com.sample.news.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Model class that contains news item data.
 * Parcelable to pass data between fragments
 */
public class NewsEntity implements Parcelable {

    private String title;

    private String _abstract;

    private String url;

    private List<MediaEntity> multimedia = null;

    public NewsEntity() {
    }

    protected NewsEntity(Parcel in) {
        title = in.readString();
        _abstract = in.readString();
        url = in.readString();
        multimedia = in.createTypedArrayList(MediaEntity.CREATOR);
    }

    public static final Creator<NewsEntity> CREATOR = new Creator<NewsEntity>() {
        @Override
        public NewsEntity createFromParcel(Parcel in) {
            return new NewsEntity(in);
        }

        @Override
        public NewsEntity[] newArray(int size) {
            return new NewsEntity[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstract() {
        return _abstract;
    }

    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<MediaEntity> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<MediaEntity> multimedia) {
        this.multimedia = multimedia;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(_abstract);
        parcel.writeString(url);
        parcel.writeTypedList(multimedia);
    }
}
