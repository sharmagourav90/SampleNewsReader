package com.sample.news.data.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Model class that contains multimedia data of news item.
 * Parcelable to pass data between fragments
 */
public class MediaEntity implements Parcelable {

    private String url;

    private String format;

    public MediaEntity() {
    }

    protected MediaEntity(Parcel in) {
        url = in.readString();
        format = in.readString();
    }

    public static final Creator<MediaEntity> CREATOR = new Creator<MediaEntity>() {
        @Override
        public MediaEntity createFromParcel(Parcel in) {
            return new MediaEntity(in);
        }

        @Override
        public MediaEntity[] newArray(int size) {
            return new MediaEntity[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(url);
        parcel.writeString(format);
    }
}
