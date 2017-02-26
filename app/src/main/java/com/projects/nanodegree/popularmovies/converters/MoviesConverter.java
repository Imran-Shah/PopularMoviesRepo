package com.projects.nanodegree.popularmovies.converters;

import android.content.ContentValues;
import android.database.Cursor;

import com.projects.nanodegree.popularmovies.models.MovieDetailModel;
import com.projects.nanodegree.popularmovies.models.PopularMoviesModel;
import com.projects.nanodegree.popularmovies.provider.MoviesContract;
import com.projects.nanodegree.popularmovies.transferObjects.MovieDetail;
import com.projects.nanodegree.popularmovies.transferObjects.PopularMovies;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Imran on 8/21/16.
 */
public final class MoviesConverter {

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



    public static ContentValues convert(MovieDetailModel movie) {
        ContentValues values = new ContentValues();
        values.put(MoviesContract.MovieEntry._ID, movie.getId());
        values.put(MoviesContract.MovieEntry.COLUMN_BACKDROP_IMAGE, movie.getBackdropPath());
        values.put(MoviesContract.MovieEntry.COLUMN_POPULARITY, movie.getPopularity());
        values.put(MoviesContract.MovieEntry.COLUMN_POSTER_IMAGE, movie.getPosterPath());
        values.put(MoviesContract.MovieEntry.COLUMN_RELEASE_DATE, movie.getReleaseDate());
        values.put(MoviesContract.MovieEntry.COLUMN_SYNOPSIS, movie.getOverview());
        values.put(MoviesContract.MovieEntry.COLUMN_TITLE, movie.getTitle());
        values.put(MoviesContract.MovieEntry.COLUMN_VOTE_AVERAGE, movie.getVoteAverage());
        return values;
    }


    public static MovieDetailModel convert(Cursor cursor) {

        MovieDetailModel movieItemModel = new MovieDetailModel();
        movieItemModel.setId(String.valueOf(cursor.getInt(cursor.getColumnIndex(MoviesContract.MovieEntry._ID))));
        movieItemModel.setTitle(cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_TITLE)));
        movieItemModel.setPosterPath(cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_POSTER_IMAGE)));
        movieItemModel.setOverview(cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_SYNOPSIS)));
        movieItemModel.setPopularity(String.valueOf(cursor.getFloat(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_POPULARITY))));
        movieItemModel.setReleaseDate(cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_RELEASE_DATE)));
        movieItemModel.setBackdropPath(cursor.getString(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_BACKDROP_IMAGE)));
        movieItemModel.setVoteAverage(String.valueOf(cursor.getFloat(cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_VOTE_AVERAGE))));

        return movieItemModel;
    }

}
