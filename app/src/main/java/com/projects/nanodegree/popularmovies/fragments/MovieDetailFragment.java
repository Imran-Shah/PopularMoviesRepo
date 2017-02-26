package com.projects.nanodegree.popularmovies.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.projects.nanodegree.popularmovies.R;
import com.projects.nanodegree.popularmovies.adapters.ReviewsAdapter;
import com.projects.nanodegree.popularmovies.adapters.TrailersAdapter;
import com.projects.nanodegree.popularmovies.listeners.MovieDetailsListener;
import com.projects.nanodegree.popularmovies.models.MovieDetailModel;
import com.projects.nanodegree.popularmovies.models.ReviewsResponseModel;
import com.projects.nanodegree.popularmovies.models.TrailersResponseModel;
import com.projects.nanodegree.popularmovies.presenters.MovieDetailsPresenter;
import com.projects.nanodegree.popularmovies.services.FavoriteService;
import com.projects.nanodegree.popularmovies.utils.Constants;
import com.squareup.picasso.Picasso;

/**
 * Created by Imran on 11/18/16.
 */

public class MovieDetailFragment extends BaseFragment implements MovieDetailsListener, TrailersAdapter.TrailerClickListener, ReviewsAdapter.ReviewItemClickListener {


    TextView tv_title, tv_release_date, tv_rating, tv_synopsis, tv_popularity;
    ImageView iv_movie;
    MovieDetailModel movieDetail;
    TrailersAdapter trailersAdapter;
    ReviewsAdapter reviewsAdapter;
    RecyclerView rv_reviews;
    RecyclerView rv_trailers;
    Button btn_favorite;


    private static final String REMOVE_FROM_FAVORITES = "Remove from favorites";
    private static final String UNABLE_TO_FETCH_REVIEWS_AT_THIS_TIME = "Unable to fetch reviews at this time";
    private static final String UNABLE_TO_FETCH_TRAILERS_AT_THIS_TIME = "Unable to fetch trailers at this time";


    public static  MovieDetailFragment newInstance(MovieDetailModel movieDetailModel) {

        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.BUNDLE_MOVIE_DETAIL, movieDetailModel);
        movieDetailFragment.setArguments(bundle);
        return movieDetailFragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        MovieDetailsPresenter presenter = new MovieDetailsPresenter(this, getActivity().getApplicationContext());
        if(savedInstanceState!=null)
            movieDetail = savedInstanceState.getParcelable(Constants.BUNDLE_MOVIE_DETAIL);
        if(getArguments()!=null)
            movieDetail = getArguments().getParcelable(Constants.BUNDLE_MOVIE_DETAIL);

        if(movieDetail!=null){

            presenter.getReviews(movieDetail.getId());
            presenter.getTrailers(movieDetail.getId());

        }


        return inflater.inflate(R.layout.fragment_movie_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setUpComponents(view);
        setDataToComponents();


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(Constants.BUNDLE_MOVIE_DETAIL, movieDetail);
        super.onSaveInstanceState(outState);
    }


    private void setUpComponents(View rootView) {
        tv_title = (TextView) rootView.findViewById(R.id.tv_title);
        tv_release_date = (TextView) rootView.findViewById(R.id.tv_release_date);
        tv_rating = (TextView) rootView.findViewById(R.id.tv_rating);
        tv_popularity = (TextView) rootView.findViewById(R.id.tv_popularity);
        tv_synopsis = (TextView) rootView.findViewById(R.id.tv_synopsis);
        iv_movie = (ImageView) rootView.findViewById(R.id.iv_movie);
        rv_reviews = (RecyclerView)rootView.findViewById(R.id.rv_reviews);
        rv_trailers = (RecyclerView)rootView.findViewById(R.id.rv_trailers);
        btn_favorite = (AppCompatButton)rootView.findViewById(R.id.btn_favorite);
    }

    private void setDataToComponents() {
        if (movieDetail != null) {
            tv_title.setText(movieDetail.getTitle());
            tv_release_date.setText(movieDetail.getReleaseDate());
            tv_rating.setText(movieDetail.getVoteAverage());
            tv_synopsis.setText(movieDetail.getOverview());
            tv_popularity.setText(movieDetail.getPopularity());

            String imageUrl = Constants.BASE_IMAGE_URL + movieDetail.getPosterPath();
            Picasso.with(getActivity().getApplicationContext()).load(imageUrl).into(iv_movie);

            if(movieDetail!=null && movieDetail.isFavorite()){
                btn_favorite.setText(REMOVE_FROM_FAVORITES);
            }
            btn_favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saveMovieAsFavourite();
                }
            });
        }
    }

    @Override
    public void onGetTrailerSuccess(TrailersResponseModel trailersResponseModel) {
        if(trailersResponseModel!=null && trailersResponseModel.getTrailerList()!=null) {
            movieDetail.setTrailerList(trailersResponseModel.getTrailerList());

            if(trailersAdapter==null) trailersAdapter = new TrailersAdapter(getContext(),trailersResponseModel, this);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
            rv_trailers.setLayoutManager(layoutManager);
            rv_trailers.setAdapter(trailersAdapter);

        }



    }

    @Override
    public void onGetTrailersError() {

        Toast.makeText(getContext(),  UNABLE_TO_FETCH_TRAILERS_AT_THIS_TIME, Toast.LENGTH_LONG).show();


    }

    @Override
    public void onGetReviewSuccess(ReviewsResponseModel reviewsResponseModel) {
        if(reviewsResponseModel!=null && reviewsResponseModel.getReviewList()!=null) {
            movieDetail.setReviewsList(reviewsResponseModel.getReviewList());
            if(reviewsAdapter==null) reviewsAdapter = new ReviewsAdapter(getContext(), reviewsResponseModel,this);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
            rv_reviews.setLayoutManager(layoutManager);
            rv_reviews.setAdapter(reviewsAdapter);

        }

    }

    @Override
    public void onGetReviewError() {

        Toast.makeText(getContext(),  UNABLE_TO_FETCH_REVIEWS_AT_THIS_TIME, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onReviewItemClicked(String url) {

        if(url!=null) {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(url));
            startActivity(intent);
        }

    }

    @Override
    public void onTrailerClicked(String key) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.YOUTUBE_APP_PATH + key));
            getActivity().startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(Constants.YOUTUBE_WATCH_URL + key));
            startActivity(intent);
        }
    }

    private void saveMovieAsFavourite() {
        Intent addOrDeleteFav = new Intent(getActivity(), FavoriteService.class);
        addOrDeleteFav.putExtra(FavoriteService.KEY, movieDetail);
        getActivity().startService(addOrDeleteFav);
    }
}
