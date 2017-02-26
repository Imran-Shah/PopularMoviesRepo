package com.projects.nanodegree.popularmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Imran on 12/18/16.
 */

public class ReviewsResponseModel implements Parcelable {


    int id;

    List<ReviewItemModel> reviewList;

    public ReviewsResponseModel(){}

    protected ReviewsResponseModel(Parcel in) {
        id = in.readInt();
        reviewList = in.createTypedArrayList(ReviewItemModel.CREATOR);
    }

    public static final Creator<ReviewsResponseModel> CREATOR = new Creator<ReviewsResponseModel>() {
        @Override
        public ReviewsResponseModel createFromParcel(Parcel in) {
            return new ReviewsResponseModel(in);
        }

        @Override
        public ReviewsResponseModel[] newArray(int size) {
            return new ReviewsResponseModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ReviewItemModel> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<ReviewItemModel> reviewList) {
        this.reviewList = reviewList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeTypedList(reviewList);
    }
}
