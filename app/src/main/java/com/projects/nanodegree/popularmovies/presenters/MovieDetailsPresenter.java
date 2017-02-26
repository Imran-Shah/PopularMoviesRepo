package com.projects.nanodegree.popularmovies.presenters;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.projects.nanodegree.popularmovies.converters.ReviewsConverter;
import com.projects.nanodegree.popularmovies.converters.TrailersConverter;
import com.projects.nanodegree.popularmovies.listeners.MovieDetailsListener;
import com.projects.nanodegree.popularmovies.network.VolleyHelper;
import com.projects.nanodegree.popularmovies.network.VolleyRequest;
import com.projects.nanodegree.popularmovies.transferObjects.ReviewsResponse;
import com.projects.nanodegree.popularmovies.transferObjects.TrailersResponse;
import com.projects.nanodegree.popularmovies.utils.Constants;

/**
 * Created by Imran on 12/18/16.
 */

public class MovieDetailsPresenter {

    MovieDetailsListener movieDetailsListener;
    Context context;


    public MovieDetailsPresenter(MovieDetailsListener movieDetailsListener, Context context){
        this.movieDetailsListener = movieDetailsListener;
        this.context = context;

    }


    public void getTrailers(String movieId){

        VolleyRequest request = new VolleyRequest(formURL(movieId, Constants.TRAILERS), TrailersResponse.class, null,onGetTrailersSuccess, onGetTrailersError);
        VolleyHelper.getInstance(context).addToRequestQueue(request);


    }

    private Response.Listener onGetTrailersSuccess = new Response.Listener() {
        @Override
        public void onResponse(Object response) {

            if(response instanceof TrailersResponse){

                movieDetailsListener.onGetTrailerSuccess(TrailersConverter.convert((TrailersResponse) response));

            }

        }
    };
    private Response.ErrorListener onGetTrailersError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

            movieDetailsListener.onGetTrailersError();

        }
    };

    public static String formURL(String movieId, String criteria) {
        StringBuffer buffer = new StringBuffer(Constants.API_BASE_URL);
        if (movieId != null) {
            buffer.append(movieId);
            buffer.append(criteria);
            buffer.append(Constants.API_KEY_PREFIX);
            buffer.append(Constants.API_KEY);
        }

        return buffer.toString();
    }

    public void getReviews(String movieId){

        VolleyRequest request = new VolleyRequest(formURL(movieId, Constants.REVIEWS), ReviewsResponse.class, null,onGetReviewsSuccess, onGetReviewsError);
        VolleyHelper.getInstance(context).addToRequestQueue(request);

    }


    private Response.Listener onGetReviewsSuccess = new Response.Listener() {
        @Override
        public void onResponse(Object response) {
            if(response instanceof ReviewsResponse){

                movieDetailsListener.onGetReviewSuccess(ReviewsConverter.convert((ReviewsResponse)response));

            }

        }
    };
    private Response.ErrorListener onGetReviewsError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

            movieDetailsListener.onGetReviewError();


        }
    };
}
