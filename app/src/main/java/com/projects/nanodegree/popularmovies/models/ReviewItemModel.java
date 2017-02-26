package com.projects.nanodegree.popularmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Imran on 12/18/16.
 */

public class ReviewItemModel implements Parcelable {

    String id;

    String author;

    String content;

    String url;

    public ReviewItemModel(){}

    protected ReviewItemModel(Parcel in) {
        id = in.readString();
        author = in.readString();
        content = in.readString();
        url = in.readString();
    }

    public static final Creator<ReviewItemModel> CREATOR = new Creator<ReviewItemModel>() {
        @Override
        public ReviewItemModel createFromParcel(Parcel in) {
            return new ReviewItemModel(in);
        }

        @Override
        public ReviewItemModel[] newArray(int size) {
            return new ReviewItemModel[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(author);
        parcel.writeString(content);
        parcel.writeString(url);
    }
}
