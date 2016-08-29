package com.projects.nanodegree.popularmovies.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.projects.nanodegree.popularmovies.R;
import com.projects.nanodegree.popularmovies.adapters.GridAdapter;
import com.projects.nanodegree.popularmovies.models.PopularMoviesModel;
import com.projects.nanodegree.popularmovies.utils.Constants;
import com.projects.nanodegree.popularmovies.utils.NetworkUtils;

import java.io.IOException;

public class MoviesActivity extends AppCompatActivity {

    private GridView gridView;
    private GridAdapter adapter;
    private String currentFilter;
    private PopularMoviesModel popularMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        gridView = (GridView) findViewById(R.id.grid_view_movies);

        if (NetworkUtils.isNetworkConnected(getApplicationContext())) {
            new NetworkTask().execute(Constants.POPULAR);
        } else {
            alertUser();
        }

        setUpListeners();


    }

    private void alertUser() {
        Toast.makeText(getApplicationContext(), Constants.CHECK_CONNECTIVITY, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (NetworkUtils.isNetworkConnected(getApplicationContext())) {
            setUpOptions(item);
        } else {
            alertUser();

        }
        return super.onOptionsItemSelected(item);
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

    private void setUpListeners() {

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                loadMovieDetails(position);
            }
        });

    }


    private void setUpOptions(MenuItem item) {
        if (item.getItemId() == R.id.action_settings_sort_by_ratings && getCurrentFilter() != Constants.TOP_RATED) {
            new NetworkTask().execute(Constants.TOP_RATED);

        } else if (item.getItemId() == R.id.action_settings_sort_by_popularity && getCurrentFilter() != Constants.POPULAR) {
            new NetworkTask().execute(Constants.POPULAR);

        } else {
            Toast.makeText(getApplicationContext(), Constants.ALREADY_SORTED, Toast.LENGTH_SHORT).show();
        }
    }

    public String getCurrentFilter() {
        return currentFilter;
    }

    public void setCurrentFilter(String currentFilter) {
        this.currentFilter = currentFilter;
    }

    public void setPopularMovies(PopularMoviesModel popularMovies) {
        this.popularMovies = popularMovies;
    }


    private class NetworkTask extends AsyncTask<String, Void, PopularMoviesModel> {


        @Override
        protected PopularMoviesModel doInBackground(String... params) {
            try {
                setCurrentFilter(params[0]);
                return NetworkUtils.fetchData(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return null;

            }
        }

        @Override
        protected void onPostExecute(PopularMoviesModel popularMovies) {

            if (popularMovies != null) {

                setPopularMovies(popularMovies);
                if (adapter == null) {
                    adapter = new GridAdapter(getApplicationContext(), popularMovies.getMovies());
                    if (gridView != null) gridView.setAdapter(adapter);
                } else {
                    adapter.setMovies(popularMovies.getMovies());
                    adapter.notifyDataSetChanged();
                }
            }
        }

    }


    private void loadMovieDetails(int position) {
        if (adapter != null) {

            Intent intent = new Intent(this, MovieDetailActivity.class);
            intent.putExtra(Constants.BUNDLE_MOVIE_DETAIL, adapter.getMovieDetail(position));
            startActivity(intent);

        }
    }


}
