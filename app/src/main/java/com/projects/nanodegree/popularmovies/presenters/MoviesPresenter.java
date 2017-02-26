package com.projects.nanodegree.popularmovies.presenters;

import android.content.Context;
import android.database.Cursor;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.projects.nanodegree.popularmovies.converters.MoviesConverter;
import com.projects.nanodegree.popularmovies.listeners.MoviesListener;
import com.projects.nanodegree.popularmovies.models.MovieDetailModel;
import com.projects.nanodegree.popularmovies.network.VolleyHelper;
import com.projects.nanodegree.popularmovies.network.VolleyRequest;
import com.projects.nanodegree.popularmovies.provider.MoviesContract;
import com.projects.nanodegree.popularmovies.transferObjects.PopularMovies;
import com.projects.nanodegree.popularmovies.utils.Constants;

import java.util.ArrayList;

/**
 * Created by Imran on 12/17/16.
 */

public class MoviesPresenter {

    MoviesListener moviesInterface;
    Context context;

    public  MoviesPresenter(MoviesListener moviesInterface, Context context){
        this.moviesInterface = moviesInterface;
        this.context = context;


    }
    public void fetchMovies(String criteria){

        if(Constants.FAVORITES.equalsIgnoreCase(criteria)){
            fetchFavourites();
        }else {
            VolleyRequest request = new VolleyRequest(getURL(criteria), PopularMovies.class, null, onSuccessResponse, onFailureResponse);
            VolleyHelper.getInstance(context).addToRequestQueue(request);
        }
    }


    private Response.Listener onSuccessResponse = new Response.Listener() {
        @Override
        public void onResponse(Object response) {

            if(response instanceof PopularMovies){
                moviesInterface.onGetMoviesSuccess(MoviesConverter.convert((PopularMovies) response));
            }else{
                moviesInterface.onGetMoviesFailure();
            }

        }
    };


    private Response.ErrorListener onFailureResponse = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            moviesInterface.onGetMoviesFailure();

        }
    };

    public static String getURL(String selection) {
        StringBuffer buffer = new StringBuffer(Constants.API_BASE_URL);
        if (selection != null) {
            buffer.append(selection);
            buffer.append(Constants.API_KEY_PREFIX);
            buffer.append(Constants.API_KEY);
        }

        return buffer.toString();
    }


    public void fetchFavourites() {

        final String[] MOVIE_COLUMNS = {
                MoviesContract.MovieEntry._ID,
                MoviesContract.MovieEntry.COLUMN_BACKDROP_IMAGE,
                MoviesContract.MovieEntry.COLUMN_POPULARITY,
                MoviesContract.MovieEntry.COLUMN_POSTER_IMAGE,
                MoviesContract.MovieEntry.COLUMN_RELEASE_DATE,
                MoviesContract.MovieEntry.COLUMN_SYNOPSIS,
                MoviesContract.MovieEntry.COLUMN_TITLE,
                MoviesContract.MovieEntry.COLUMN_VOTE_AVERAGE
        };

        Cursor movieCursor = context.getContentResolver().query(MoviesContract.MovieEntry.CONTENT_URI, MOVIE_COLUMNS, null, null, null);

        if(movieCursor!=null) {
            ArrayList<MovieDetailModel> movies = new ArrayList<>();
            while (movieCursor.moveToNext()) {
                MovieDetailModel movie = MoviesConverter.convert(movieCursor);
                movie.setFavorite(true);
                movies.add(movie);
            }
            movieCursor.close();

            moviesInterface.onGetFavorites(movies);

        }

    }

}
