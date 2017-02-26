package com.projects.nanodegree.popularmovies.converters;

import android.content.ContentValues;

import com.projects.nanodegree.popularmovies.models.ReviewItemModel;
import com.projects.nanodegree.popularmovies.models.ReviewsResponseModel;
import com.projects.nanodegree.popularmovies.provider.MoviesContract;
import com.projects.nanodegree.popularmovies.transferObjects.ReviewItem;
import com.projects.nanodegree.popularmovies.transferObjects.ReviewsResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Imran on 12/18/16.
 */

public final class ReviewsConverter {

    public static ReviewsResponseModel convert(ReviewsResponse reviewsResponse){

        ReviewsResponseModel reviewsResponseModel = null;

        if(reviewsResponse!=null){
            reviewsResponseModel = new ReviewsResponseModel();
            reviewsResponseModel.setId(reviewsResponse.getId());
            reviewsResponseModel.setReviewList(convert(reviewsResponse.getReviewList()));

        }

        return reviewsResponseModel;

    }

    private static List<ReviewItemModel> convert(List<ReviewItem> reviewList) {
        List<ReviewItemModel>  reviewItemModelList =  null;
        if(reviewList!=null){
            reviewItemModelList = new ArrayList<>();
            for (ReviewItem item:reviewList
                 ) {
                reviewItemModelList.add(convert(item));

            }

        }
        return reviewItemModelList;
    }

    private static ReviewItemModel convert(ReviewItem item) {

        ReviewItemModel reviewItemModel = null;

        if(item!=null){
            reviewItemModel = new ReviewItemModel();
            reviewItemModel.setAuthor(item.getAuthor());
            reviewItemModel.setId(item.getId());
            reviewItemModel.setContent(item.getContent());
            reviewItemModel.setUrl(item.getUrl());

        }
        return reviewItemModel;
    }


    public static ContentValues convert(ReviewItemModel review, String movieId) {
        ContentValues values = new ContentValues();
        values.put(MoviesContract.ReviewEntry._ID, review.getId());
        values.put(MoviesContract.ReviewEntry.COLUMN_MOVIE_ID, movieId);
        values.put(MoviesContract.ReviewEntry.COLUMN_AUTHOR, review.getAuthor());
        values.put(MoviesContract.ReviewEntry.COLUMN_CONTENT, review.getContent());
        values.put(MoviesContract.ReviewEntry.COLUMN_URL, review.getUrl());
        return values;
    }

    public static ContentValues[] convert(List<ReviewItemModel> reviews, String movieId) {
        ContentValues[] values = new ContentValues[reviews.size()];

        int index = 0;
        for (ReviewItemModel review : reviews) {
            values[index] = convert(review, movieId);
            index++;
        }

        return values;
    }
}
