package com.projects.nanodegree.popularmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Imran on 8/18/16.
 */
public class PopularMoviesModel implements Parcelable {

    List<MovieDetailModel> movies;

    public PopularMoviesModel() {
    }

    protected PopularMoviesModel(Parcel in) {
        movies = in.createTypedArrayList(MovieDetailModel.CREATOR);
    }

    public static final Creator<PopularMoviesModel> CREATOR = new Creator<PopularMoviesModel>() {
        @Override
        public PopularMoviesModel createFromParcel(Parcel in) {
            return new PopularMoviesModel(in);
        }

        @Override
        public PopularMoviesModel[] newArray(int size) {
            return new PopularMoviesModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(movies);
    }

    public List<MovieDetailModel> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieDetailModel> movies) {
        this.movies = movies;
    }
}
