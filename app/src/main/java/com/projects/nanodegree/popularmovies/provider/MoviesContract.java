package com.projects.nanodegree.popularmovies.provider;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Imran on 2/18/17.
 */

public class MoviesContract {

    public static final String CONTENT_AUTHORITY = "com.projects.nanodegree.popularmovies";


    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);


    public static final String MOVIE_PATH = "movies";


    public static final String VIDEO_PATH = "videos";


    public static final String REVIEW_PATH = "reviews";


    public static final class MovieEntry implements BaseColumns {

        public static final String TABLE_NAME = "movie";

        public static final String COLUMN_TITLE = "title";

        public static final String COLUMN_SYNOPSIS = "synopsis";

        public static final String COLUMN_RELEASE_DATE = "release_date";

        public static final String COLUMN_POPULARITY = "popularity";

        public static final String COLUMN_VOTE_AVERAGE = "vote_average";

        public static final String COLUMN_POSTER_IMAGE = "poster_image";

        public static final String COLUMN_BACKDROP_IMAGE = "backdrop_image";

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(MOVIE_PATH).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + MOVIE_PATH;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + MOVIE_PATH;

        public static Uri buildMovieUri(String id) {
            return CONTENT_URI.buildUpon().appendPath(id).build();
        }
    }


    public static final class VideoEntry implements BaseColumns {

        public static final String TABLE_NAME = "video";

        public static final String COLUMN_MOVIE_ID = "movie_id";

        public static final String COLUMN_NAME = "name";

        public static final String COLUMN_KEY = "key";

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(VIDEO_PATH).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + MOVIE_PATH;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + MOVIE_PATH;

        public static Uri buildMoviePathWithVideos(long movieId) {
            return BASE_CONTENT_URI.buildUpon().appendPath(MOVIE_PATH)
                    .appendPath(Long.toString(movieId)).appendPath(VIDEO_PATH).build();
        }

        public static Uri buildVideoUri(String id) {
            return CONTENT_URI.buildUpon().appendPath(id).build();
        }
    }

    public static final class ReviewEntry implements BaseColumns {

        public static final String TABLE_NAME = "review";

        public static final String COLUMN_MOVIE_ID = "movie_id";

        public static final String COLUMN_AUTHOR = "author";

        public static final String COLUMN_CONTENT = "content";

        public static final String COLUMN_URL = "url";

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(REVIEW_PATH).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + MOVIE_PATH;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + MOVIE_PATH;

        public static Uri buildMoviePathWithReviews(long movieId) {
            return BASE_CONTENT_URI.buildUpon().appendPath(MOVIE_PATH)
                    .appendPath(Long.toString(movieId)).appendPath(REVIEW_PATH).build();
        }

        public static Uri buildReviewUri(String id) {
            return CONTENT_URI.buildUpon().appendPath(id).build();
        }
    }
}
