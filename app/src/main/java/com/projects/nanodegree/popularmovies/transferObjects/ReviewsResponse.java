package com.projects.nanodegree.popularmovies.transferObjects;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Imran on 12/18/16.
 */

public class ReviewsResponse {


    @SerializedName(value = "id")
    int id;

    @SerializedName(value = "results")
    List<ReviewItem> reviewList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ReviewItem> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<ReviewItem> reviewList) {
        this.reviewList = reviewList;
    }
}
