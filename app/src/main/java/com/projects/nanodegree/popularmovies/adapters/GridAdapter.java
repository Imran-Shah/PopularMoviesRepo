package com.projects.nanodegree.popularmovies.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.projects.nanodegree.popularmovies.models.MovieDetailModel;
import com.projects.nanodegree.popularmovies.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Imran on 8/6/16.
 */
public class GridAdapter extends ArrayAdapter {

    Context context;
    List<MovieDetailModel> movies;

    public GridAdapter(Context context, List<MovieDetailModel> movies){
        super(context,0,movies);
        this.context = context;
        this.movies = movies;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setAdjustViewBounds(true);

        } else {
            imageView = (ImageView) convertView;
        }

        String imageUrl =  Constants.BASE_IMAGE_URL + movies.get(position).getPosterPath();
        Picasso.with(context).load(imageUrl).into(imageView);

        return imageView;

    }

    @Override
    public int getCount() {
        return movies!=null? movies.size(): 0;
    }

    public void setMovies(List<MovieDetailModel> movies) {
        this.movies = movies;
    }

    public MovieDetailModel getMovieDetail(int position){
        if(movies!=null) return movies.get(position);
        else return null;

    }


}
