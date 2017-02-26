package com.projects.nanodegree.popularmovies.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.projects.nanodegree.popularmovies.R;
import com.projects.nanodegree.popularmovies.fragments.MovieDetailFragment;
import com.projects.nanodegree.popularmovies.models.MovieDetailModel;
import com.projects.nanodegree.popularmovies.utils.Constants;

/**
 * Created by Imran on 8/21/16.
 */
public class MovieDetailActivity extends AppCompatActivity {

    MovieDetailModel movieDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movie_detail);
        Bundle bundle = getIntent().getExtras();
        movieDetail = bundle.getParcelable(Constants.BUNDLE_MOVIE_DETAIL);

        if (movieDetail != null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, MovieDetailFragment.newInstance(movieDetail), Constants.BUNDLE_MOVIE_DETAIL)
                    .commit();

        }

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (movieDetail != null)
            outState.putParcelable(Constants.BUNDLE_MOVIE_DETAIL, movieDetail);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        MovieDetailModel movieDetail = savedInstanceState.getParcelable(Constants.BUNDLE_MOVIE_DETAIL);
        if (movieDetail != null) setMovieDetail(movieDetail);
        super.onRestoreInstanceState(savedInstanceState);
    }

    public void setMovieDetail(MovieDetailModel movieDetail) {
        this.movieDetail = movieDetail;
    }
}

