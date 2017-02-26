package com.projects.nanodegree.popularmovies.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.projects.nanodegree.popularmovies.R;
import com.projects.nanodegree.popularmovies.activities.MoviesActivity;
import com.projects.nanodegree.popularmovies.adapters.GridAdapter;
import com.projects.nanodegree.popularmovies.listeners.MoviesListener;
import com.projects.nanodegree.popularmovies.models.MovieDetailModel;
import com.projects.nanodegree.popularmovies.models.PopularMoviesModel;
import com.projects.nanodegree.popularmovies.network.NetworkUtils;
import com.projects.nanodegree.popularmovies.presenters.MoviesPresenter;
import com.projects.nanodegree.popularmovies.utils.Constants;

import java.util.ArrayList;


/**
 * Created by Imran on 11/17/16.
 */

public class MoviesFragment extends BaseFragment implements MoviesListener {

    private static final String UNABLE_TO_FETCH_MOVIES = "Unable to fetch movies";

    MoviesActivity activity;
    private GridView gridView;
    private GridAdapter adapter;
    private String currentFilter;
    private PopularMoviesModel popularMovies;
    private MoviesPresenter moviesPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        gridView = (GridView) view.findViewById(R.id.grid_view_movies);
        moviesPresenter = new MoviesPresenter(this, getActivity().getApplicationContext());
        if (NetworkUtils.isNetworkConnected(getActivity().getApplicationContext())) {
            setCurrentFilter(Constants.POPULAR);
            moviesPresenter.fetchMovies(Constants.POPULAR);
        }
        else
            activity.alertUser();


        setUpListeners();


    }

    @Override
    public void onAttach(Context context) {
        this.activity = (MoviesActivity)context;
        super.onAttach(context);
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


    private void setUpListeners() {

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                gridView.setItemChecked(position, true);
                activity.loadMovieDetails(adapter.getMovieDetail(position));
            }
        });

    }

    @Override
    public void onGetMoviesSuccess(PopularMoviesModel popularMovies) {

        if (popularMovies != null) {

            setPopularMovies(popularMovies);
            if (adapter == null) {
                adapter = new GridAdapter(getContext(), popularMovies.getMovies());
                if (gridView != null) gridView.setAdapter(adapter);


            } else {
                adapter.setMovies(popularMovies.getMovies());
                adapter.notifyDataSetChanged();
            }
        }

    }

    @Override
    public void onGetMoviesFailure() {
        Toast.makeText(getContext(), UNABLE_TO_FETCH_MOVIES, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onGetFavorites(ArrayList<MovieDetailModel> movies) {

        if (adapter == null) {
            adapter = new GridAdapter(getActivity().getApplicationContext(), movies);
            if (gridView != null) gridView.setAdapter(adapter);
            if(popularMovies.getMovies()!=null)
                activity.loadMovieDetails(movies.get(0));

        } else {
            adapter.setMovies(movies);
            adapter.notifyDataSetChanged();
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (NetworkUtils.isNetworkConnected(getActivity().getApplicationContext())) {
            setUpOptions(item);
        } else {
            activity.alertUser();

        }
        return super.onOptionsItemSelected(item);
    }


    private void setUpOptions(MenuItem item) {


        if (item.getItemId() == R.id.action_settings_sort_by_ratings && getCurrentFilter() != Constants.TOP_RATED) {
            setCurrentFilter(Constants.TOP_RATED);
           moviesPresenter.fetchMovies(Constants.TOP_RATED);

        } else if (item.getItemId() == R.id.action_settings_sort_by_popularity && getCurrentFilter() != Constants.POPULAR) {
            setCurrentFilter(Constants.POPULAR);
            moviesPresenter.fetchMovies(Constants.POPULAR);
        } else if (item.getItemId() == R.id.action_settings_favorites) {
            setCurrentFilter(Constants.FAVORITES);
            moviesPresenter.fetchMovies(Constants.FAVORITES);
        }


        else {
            Toast.makeText(getActivity().getApplicationContext(), Constants.ALREADY_SORTED, Toast.LENGTH_SHORT).show();
        }
    }


}
