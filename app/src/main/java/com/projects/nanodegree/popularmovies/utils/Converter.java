package com.projects.nanodegree.popularmovies.utils;

import com.projects.nanodegree.popularmovies.models.MovieDetailModel;
import com.projects.nanodegree.popularmovies.models.PopularMoviesModel;
import com.projects.nanodegree.popularmovies.transferObjects.MovieDetail;
import com.projects.nanodegree.popularmovies.transferObjects.PopularMovies;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Imran on 8/21/16.
 */
public final class Converter {

    public static PopularMoviesModel convert(PopularMovies popularMovies) {
        PopularMoviesModel popularMoviesModel = null;
        if (popularMovies != null) {
            popularMoviesModel = new PopularMoviesModel();
            popularMoviesModel.setMovies(convert(popularMovies.getMovies()));


        }
        return popularMoviesModel;
    }

    private static List<MovieDetailModel> convert(List<MovieDetail> movies) {
        List<MovieDetailModel> movieList = null;
        if (movies != null) {
            movieList = new ArrayList<>();
            for (MovieDetail movieDetail : movies) {
                if (movieDetail != null) {
                    movieList.add(convert(movieDetail));
                }
            }
        }
        return movieList;
    }

    private static MovieDetailModel convert(MovieDetail movieDetail) {
        MovieDetailModel movieDetailModel = null;
        if (movieDetail != null) {
            movieDetailModel = new MovieDetailModel();
            movieDetailModel.setPosterPath(movieDetail.getPosterPath());
            movieDetailModel.setIsAdult(movieDetail.getIsAdult());
            movieDetailModel.setOverview(movieDetail.getOverview());
            movieDetailModel.setReleaseDate(movieDetail.getReleaseDate());
            movieDetailModel.setId(movieDetail.getId());
            movieDetailModel.setOriginalTitle(movieDetail.getOriginalTitle());
            movieDetailModel.setOriginalLanguage(movieDetail.getOriginalLanguage());
            movieDetailModel.setTitle(movieDetail.getTitle());
            movieDetailModel.setBackdropPath(movieDetail.getBackdropPath());
            movieDetailModel.setPopularity(movieDetail.getVoteCount());
            movieDetailModel.setVideo(movieDetail.getVideo());
            movieDetailModel.setVoteCount(movieDetail.getVoteCount());
            movieDetailModel.setVoteAverage(movieDetail.getVoteAverage());

        }
        return movieDetailModel;

    }

}
