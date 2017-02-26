package com.projects.nanodegree.popularmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Imran on 12/18/16.
 */

public class TrailersResponseModel implements Parcelable {

        int id;

        List<TrailerItemModel> trailerList;


    public TrailersResponseModel(){}

    protected TrailersResponseModel(Parcel in) {
        id = in.readInt();
        trailerList = in.createTypedArrayList(TrailerItemModel.CREATOR);
    }

    public static final Creator<TrailersResponseModel> CREATOR = new Creator<TrailersResponseModel>() {
        @Override
        public TrailersResponseModel createFromParcel(Parcel in) {
            return new TrailersResponseModel(in);
        }

        @Override
        public TrailersResponseModel[] newArray(int size) {
            return new TrailersResponseModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<TrailerItemModel> getTrailerList() {
        return trailerList;
    }

    public void setTrailerList(List<TrailerItemModel> trailerList) {
        this.trailerList = trailerList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeTypedList(trailerList);
    }
}
