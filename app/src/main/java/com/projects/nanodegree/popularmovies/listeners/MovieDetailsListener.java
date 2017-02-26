package com.projects.nanodegree.popularmovies.listeners;

import com.projects.nanodegree.popularmovies.models.ReviewsResponseModel;
import com.projects.nanodegree.popularmovies.models.TrailersResponseModel;

/**
 * Created by Imran on 12/18/16.
 */

public interface MovieDetailsListener {
     void onGetTrailerSuccess(TrailersResponseModel trailersResponseModel);
     void onGetTrailersError();
     void onGetReviewSuccess(ReviewsResponseModel reviewsResponseModel);
     void onGetReviewError();

}
