package com.projects.nanodegree.popularmovies.transferObjects;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Imran on 12/18/16.
 */

public class TrailersResponse {

        @SerializedName(value = "id")
        int id;

        @SerializedName(value = "youtube")
        List<TrailerItem> trailerList;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<TrailerItem> getTrailerList() {
        return trailerList;
    }

    public void setTrailerList(List<TrailerItem> trailerList) {
        this.trailerList = trailerList;
    }
}
