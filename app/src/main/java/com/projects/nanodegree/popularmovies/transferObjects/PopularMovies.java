package com.projects.nanodegree.popularmovies.transferObjects;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Imran on 8/18/16.
 */
public class PopularMovies {

    @SerializedName("results")
    List<MovieDetail> movies;

    public List<MovieDetail> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieDetail> movies) {
        this.movies = movies;
    }
}
