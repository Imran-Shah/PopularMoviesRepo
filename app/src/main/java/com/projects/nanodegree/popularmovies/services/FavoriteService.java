package com.projects.nanodegree.popularmovies.services;

import android.app.IntentService;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.widget.Toast;

import com.projects.nanodegree.popularmovies.converters.MoviesConverter;
import com.projects.nanodegree.popularmovies.converters.ReviewsConverter;
import com.projects.nanodegree.popularmovies.converters.TrailersConverter;
import com.projects.nanodegree.popularmovies.models.MovieDetailModel;
import com.projects.nanodegree.popularmovies.provider.MoviesContract;

/**
 * Created by Imran on 2/18/17.
 */

public class FavoriteService extends IntentService {

    private static final String CLASS_NAME = FavoriteService.class.getSimpleName();
    public static final String KEY = "favorite_key";
    private static final String REMOVED_FROM_FAVORITES = "removed from favourites";
    private static final String ADDED_TO_FAVORITES = "added to favourites";
    private static final String SPACE = " ";


    MovieDetailModel movieDetail;

    public FavoriteService() {
        super(CLASS_NAME);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        movieDetail = intent.getParcelableExtra(KEY);

        String[] projection = new String[]{MoviesContract.MovieEntry._ID};
        String selection = MoviesContract.MovieEntry._ID + "=?";
        String[] selectionArgs = new String[1];
        selectionArgs[0] = "" + movieDetail.getId();

        Cursor movieCursor = getApplicationContext().getContentResolver().query(MoviesContract.MovieEntry.CONTENT_URI,
                projection, selection, selectionArgs, null);
        if (movieCursor != null) {
            if (!movieCursor.moveToFirst()) {

                getApplicationContext().getContentResolver().insert(MoviesContract.MovieEntry.CONTENT_URI,
                        MoviesConverter.convert(movieDetail));

                if (movieDetail.getReviewsList() != null) {
                    getApplicationContext().getContentResolver().bulkInsert(MoviesContract.ReviewEntry.CONTENT_URI,
                            ReviewsConverter.convert(movieDetail.getReviewsList(), movieDetail.getId()));
                }

                if (movieDetail.getTrailerList() != null) {
                    getApplicationContext().getContentResolver().bulkInsert(MoviesContract.VideoEntry.CONTENT_URI,
                            TrailersConverter.convert(movieDetail.getTrailerList(), movieDetail.getId()));
                }
                notifyFavUpdate(movieDetail.getTitle() + SPACE + ADDED_TO_FAVORITES);
            } else {
                deleteFavourite();
            }

            movieCursor.close();

        }
    }


    private void deleteFavourite() {

        String selectionMovie = MoviesContract.MovieEntry._ID + "=?";
        String selectionReview = MoviesContract.ReviewEntry.COLUMN_MOVIE_ID + "=?";
        String selectionTrailer = MoviesContract.VideoEntry.COLUMN_MOVIE_ID + "=?";
        String[] selectionArgs = new String[1];
        selectionArgs[0] = "" + movieDetail.getId();

        int id = getApplicationContext().getContentResolver().delete(MoviesContract.MovieEntry.CONTENT_URI,
                selectionMovie, selectionArgs);

        if (id > -1) {
            getApplicationContext().getContentResolver().delete(MoviesContract.ReviewEntry.CONTENT_URI,
                    selectionReview, selectionArgs);
            getApplicationContext().getContentResolver().delete(MoviesContract.VideoEntry.CONTENT_URI,
                    selectionTrailer, selectionArgs);

            notifyFavUpdate(movieDetail.getTitle() + SPACE + REMOVED_FROM_FAVORITES);
        }

    }

    private void notifyFavUpdate(String text) {
        final String message = String.format(text, movieDetail.getTitle());
        Handler mHandler = new Handler(getMainLooper());
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }


}
