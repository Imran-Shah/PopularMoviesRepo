package com.projects.nanodegree.popularmovies.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.projects.nanodegree.popularmovies.R;
import com.projects.nanodegree.popularmovies.fragments.MovieDetailFragment;
import com.projects.nanodegree.popularmovies.models.MovieDetailModel;
import com.projects.nanodegree.popularmovies.models.PopularMoviesModel;
import com.projects.nanodegree.popularmovies.utils.Constants;

public class MoviesActivity extends AppCompatActivity {

    private static final String PLEASE_TAP_ON_A_MOVIE_THUMBNAIL_TO_SEE_MORE_DETAILS = "Please tap on a movie thumbnail to see more details";
    private PopularMoviesModel popularMovies;
    private FrameLayout detailsContainer;
    private boolean isMultiPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        detailsContainer = (FrameLayout)findViewById(R.id.detailsContainer);
        if(detailsContainer!=null) {
            isMultiPane = true;
            Toast.makeText(getApplicationContext(),  PLEASE_TAP_ON_A_MOVIE_THUMBNAIL_TO_SEE_MORE_DETAILS, Toast.LENGTH_SHORT).show();
        }



    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }



    public void alertUser() {
        Toast.makeText(getApplicationContext(), Constants.CHECK_CONNECTIVITY, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null && popularMovies != null)
            outState.putParcelable(Constants.BUNDLE_MOVIES, popularMovies);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        PopularMoviesModel popularMovies = savedInstanceState.getParcelable(Constants.BUNDLE_MOVIES);
        setPopularMovies(popularMovies);
        super.onRestoreInstanceState(savedInstanceState);
    }



    public void setPopularMovies(PopularMoviesModel popularMovies) {
        this.popularMovies = popularMovies;
    }

    public void loadMovieDetails(MovieDetailModel movieDetailModel) {

            MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constants.BUNDLE_MOVIE_DETAIL, movieDetailModel);
             movieDetailFragment.setArguments(bundle);
            if(isMultiPane)
                getSupportFragmentManager().beginTransaction().replace(R.id.detailsContainer, movieDetailFragment).commit();
        else{
            Intent intent = new Intent(this, MovieDetailActivity.class);
            intent.putExtra(Constants.BUNDLE_MOVIE_DETAIL, movieDetailModel);
            startActivity(intent);

        }
    }

}
