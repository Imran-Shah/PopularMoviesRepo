package com.projects.nanodegree.popularmovies.listeners;

import com.projects.nanodegree.popularmovies.models.MovieDetailModel;
import com.projects.nanodegree.popularmovies.models.PopularMoviesModel;

import java.util.ArrayList;

/**
 * Created by Imran on 12/17/16.
 */

public interface MoviesListener {

    void onGetMoviesSuccess(PopularMoviesModel response, boolean isNewResponse);
    void onGetMoviesFailure();
    void onGetFavorites(ArrayList<MovieDetailModel> movies);
}
