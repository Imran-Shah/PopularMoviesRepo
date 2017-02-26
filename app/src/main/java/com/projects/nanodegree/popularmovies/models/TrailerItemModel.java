package com.projects.nanodegree.popularmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Imran on 12/18/16.
 */

public class TrailerItemModel implements Parcelable {

    String name;

    String source;

    String size;

    String type;

    String id;

    String key;


    public TrailerItemModel(){}

    protected TrailerItemModel(Parcel in) {

        name = in.readString();
        source = in.readString();
        size = in.readString();
        type = in.readString();
        id = in.readString();
        key = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeString(source);
        dest.writeString(size);
        dest.writeString(type);
        dest.writeString(id);
        dest.writeString(key);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TrailerItemModel> CREATOR = new Creator<TrailerItemModel>() {
        @Override
        public TrailerItemModel createFromParcel(Parcel in) {
            return new TrailerItemModel(in);
        }

        @Override
        public TrailerItemModel[] newArray(int size) {
            return new TrailerItemModel[size];
        }
    };



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
