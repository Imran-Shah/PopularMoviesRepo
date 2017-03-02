package com.projects.nanodegree.popularmovies.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
    private static final String CURRENT_FILTER = "current_filter";
    private static final String MOVIES = "movies";
    private static final String GRID_VISIBLE_POSITION = "position";
    private int firstVisiblePosition =0;
    private String previousFilter;


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

        String currentFilter = savedInstanceState!=null && savedInstanceState.getString(CURRENT_FILTER)!=null? savedInstanceState.getString(CURRENT_FILTER): getCurrentFilter();
        if(currentFilter!=null) setCurrentFilter(currentFilter);

        firstVisiblePosition = getCurrentFilter().equalsIgnoreCase(previousFilter) && savedInstanceState!=null ? savedInstanceState.getInt(GRID_VISIBLE_POSITION):0;

        if(Constants.FAVORITES.equals(currentFilter)) {
            moviesPresenter.fetchMovies(currentFilter);
            return;
        }


        if(savedInstanceState!=null && savedInstanceState.getParcelable(MOVIES)!=null) {
            popularMovies = savedInstanceState.getParcelable(MOVIES);
            onGetMoviesSuccess(popularMovies, false);

        }else if(NetworkUtils.isNetworkConnected(getActivity().getApplicationContext()))
            moviesPresenter.fetchMovies(currentFilter);

       /* else if(savedInstanceState!=null && savedInstanceState.getParcelable(MOVIES)!=null) {
            popularMovies = savedInstanceState.getParcelable(MOVIES);
            onGetMoviesSuccess(popularMovies);

        }*/ else activity.alertUser();


        setUpListeners();

    }

    @Override
    public void onAttach(Context context) {
        this.activity = (MoviesActivity)context;
        super.onAttach(context);
    }


    public String getCurrentFilter() {
        return currentFilter!=null? currentFilter: Constants.POPULAR;
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
                gridView.smoothScrollToPosition(firstVisiblePosition);
                activity.loadMovieDetails(adapter.getMovieDetail(position));
            }
        });

       gridView.smoothScrollToPosition(firstVisiblePosition);
    }

    @Override
    public void onGetMoviesSuccess(PopularMoviesModel popularMovies, boolean isNewResponse) {

        if (popularMovies != null) {

            setPopularMovies(popularMovies);

            if(isNewResponse && !getCurrentFilter().equalsIgnoreCase(previousFilter)){
                firstVisiblePosition = 0;
            }

            if (adapter == null) {
                adapter = new GridAdapter(getContext(), popularMovies.getMovies());
                if (gridView != null) gridView.setAdapter(adapter);


            } else {
                adapter.setMovies(popularMovies.getMovies());
                adapter.notifyDataSetChanged();
            }


            gridView.smoothScrollToPosition(firstVisiblePosition);
        }

    }

    @Override
    public void onGetMoviesFailure() {
        Toast.makeText(getContext(), UNABLE_TO_FETCH_MOVIES, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onGetFavorites(ArrayList<MovieDetailModel> movies) {

        if (adapter == null) {
            adapter = new GridAdapter(getActivity().getApplicationContext(), movies);
            if (gridView != null) gridView.setAdapter(adapter);
            if(popularMovies!=null && popularMovies.getMovies()!=null) {
                activity.loadMovieDetails(movies.get(0));
            }

        } else {
           // isNewFilterApplied = false;
            adapter.setMovies(movies);
            adapter.notifyDataSetChanged();
        }


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            setUpOptions(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putString(CURRENT_FILTER, getCurrentFilter());
        outState.putParcelable(MOVIES, popularMovies);
        if(gridView.getFirstVisiblePosition()!= GridView.INVALID_POSITION) {
            int position =  gridView.getFirstVisiblePosition();
            outState.putInt(GRID_VISIBLE_POSITION, position);
        }
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        if(savedInstanceState!=null)
       gridView.smoothScrollToPosition(savedInstanceState.getInt(GRID_VISIBLE_POSITION));
        super.onViewStateRestored(savedInstanceState);
    }

    private void setUpOptions(MenuItem item) {

        previousFilter =getCurrentFilter();

        if (item.getItemId() == R.id.action_settings_sort_by_ratings && getCurrentFilter() != Constants.TOP_RATED) {
            setCurrentFilter(Constants.TOP_RATED);
           moviesPresenter.fetchMovies(Constants.TOP_RATED);
            firstVisiblePosition = 0;

        } else if (item.getItemId() == R.id.action_settings_sort_by_popularity && getCurrentFilter() != Constants.POPULAR) {
            setCurrentFilter(Constants.POPULAR);
            moviesPresenter.fetchMovies(Constants.POPULAR);
            firstVisiblePosition = 0;

        } else if (item.getItemId() == R.id.action_settings_favorites) {
            setCurrentFilter(Constants.FAVORITES);
            moviesPresenter.fetchMovies(Constants.FAVORITES);
            firstVisiblePosition = 0;

        }

        else {
            Toast.makeText(getActivity().getApplicationContext(), Constants.ALREADY_SORTED, Toast.LENGTH_SHORT).show();
        }
    }


}
