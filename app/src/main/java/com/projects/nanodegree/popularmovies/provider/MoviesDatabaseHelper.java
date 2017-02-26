package com.projects.nanodegree.popularmovies.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Imran on 2/18/17.
 */

public class MoviesDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

    public static final String DATABASE_NAME = "favourite_movies.db";

    public MoviesDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + MoviesContract.MovieEntry.TABLE_NAME + " (" +
                MoviesContract.MovieEntry._ID + " TEXT PRIMARY KEY," +
                MoviesContract.MovieEntry.COLUMN_TITLE + " TEXT NOT NULL," +
                MoviesContract.MovieEntry.COLUMN_SYNOPSIS + " TEXT NULL," +
                MoviesContract.MovieEntry.COLUMN_RELEASE_DATE + " TEXT NOT NULL," +
                MoviesContract.MovieEntry.COLUMN_POPULARITY + " REAL NOT NULL," +
                MoviesContract.MovieEntry.COLUMN_VOTE_AVERAGE + " REAL NOT NULL," +
                MoviesContract.MovieEntry.COLUMN_POSTER_IMAGE + " TEXT NULL," +
                MoviesContract.MovieEntry.COLUMN_BACKDROP_IMAGE + " TEXT NULL);";

        final String SQL_CREATE_VIDEO_TABLE = "CREATE TABLE " + MoviesContract.VideoEntry.TABLE_NAME + " (" +
                MoviesContract.VideoEntry._ID + " TEXT PRIMARY KEY," +
                MoviesContract.VideoEntry.COLUMN_MOVIE_ID + " TEXT NOT NULL," +
                MoviesContract.VideoEntry.COLUMN_NAME + " TEXT NOT NULL," +
                MoviesContract.VideoEntry.COLUMN_KEY + " TEXT);";

        final String SQL_CREATE_REVIEW_TABLE = "CREATE TABLE " + MoviesContract.ReviewEntry.TABLE_NAME + " (" +
                MoviesContract.ReviewEntry._ID + " TEXT PRIMARY KEY," +
                MoviesContract.ReviewEntry.COLUMN_MOVIE_ID + " TEXT NOT NULL," +
                MoviesContract.ReviewEntry.COLUMN_AUTHOR + " TEXT NOT NULL," +
                MoviesContract.ReviewEntry.COLUMN_URL + " TEXT NOT NULL," +
                MoviesContract.ReviewEntry.COLUMN_CONTENT + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_MOVIE_TABLE);
        db.execSQL(SQL_CREATE_VIDEO_TABLE);
        db.execSQL(SQL_CREATE_REVIEW_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + MoviesContract.VideoEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MoviesContract.ReviewEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MoviesContract.MovieEntry.TABLE_NAME);
        onCreate(db);
    }
}
