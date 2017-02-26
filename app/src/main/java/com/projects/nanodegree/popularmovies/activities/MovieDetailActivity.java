package com.projects.nanodegree.popularmovies.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.projects.nanodegree.popularmovies.R;
import com.projects.nanodegree.popularmovies.fragments.MovieDetailFragment;
import com.projects.nanodegree.popularmovies.models.MovieDetailModel;
import com.projects.nanodegree.popularmovies.models.ReviewsResponseModel;
import com.projects.nanodegree.popularmovies.models.TrailersResponseModel;
import com.projects.nanodegree.popularmovies.utils.Constants;
import com.squareup.picasso.Picasso;

/**
 * Created by Imran on 8/21/16.
 */
public class MovieDetailActivity extends AppCompatActivity {

    TextView tv_title, tv_release_date, tv_rating, tv_synopsis, tv_popularity;
    ImageView iv_movie;
    MovieDetailModel movieDetail;
    FrameLayout movieDetailsContainer;
    TrailersResponseModel trailersResponseModel;
    ReviewsResponseModel reviewsResponseModel;

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

        //FrameLayout movieDetailsContainer = findViewById(R.id.movieDetailsContainer);

        //setUpComponents();
        //setDataToComponents();
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


    private void setUpComponents() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_release_date = (TextView) findViewById(R.id.tv_release_date);
        tv_rating = (TextView) findViewById(R.id.tv_rating);
        tv_popularity = (TextView) findViewById(R.id.tv_popularity);
        tv_synopsis = (TextView) findViewById(R.id.tv_synopsis);
        iv_movie = (ImageView) findViewById(R.id.iv_movie);
    }

    private void setDataToComponents() {
        if (movieDetail != null) {
            tv_title.setText(movieDetail.getTitle());
            tv_release_date.setText(movieDetail.getReleaseDate());
            tv_rating.setText(movieDetail.getVoteAverage());
            tv_synopsis.setText(movieDetail.getOverview());
            tv_popularity.setText(movieDetail.getPopularity());

            String imageUrl = Constants.BASE_IMAGE_URL + movieDetail.getPosterPath();
            Picasso.with(getApplicationContext()).load(imageUrl).into(iv_movie);
        }
    }

    public void setMovieDetail(MovieDetailModel movieDetail) {
        this.movieDetail = movieDetail;
    }
}

