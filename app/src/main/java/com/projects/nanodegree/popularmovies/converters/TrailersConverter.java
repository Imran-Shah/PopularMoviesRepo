package com.projects.nanodegree.popularmovies.converters;

import android.content.ContentValues;

import com.projects.nanodegree.popularmovies.models.TrailerItemModel;
import com.projects.nanodegree.popularmovies.models.TrailersResponseModel;
import com.projects.nanodegree.popularmovies.provider.MoviesContract;
import com.projects.nanodegree.popularmovies.transferObjects.TrailerItem;
import com.projects.nanodegree.popularmovies.transferObjects.TrailersResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Imran on 12/18/16.
 */

public final class TrailersConverter {

    public static TrailersResponseModel convert(TrailersResponse response){
        TrailersResponseModel responseModel = null;
        if(response!=null){
            responseModel =  new TrailersResponseModel();
            responseModel.setId(response.getId());
            responseModel.setTrailerList(convert(response.getTrailerList()));
        }
        return responseModel;
    }

    private static List<TrailerItemModel> convert(List<TrailerItem> trailerList) {
        List<TrailerItemModel> trailerItemModels = null;
        if(trailerList!=null){
            trailerItemModels = new ArrayList<>();
            for (TrailerItem item: trailerList
                 ) {
                trailerItemModels.add(convert(item));

            }

        }
        return trailerItemModels;
    }

    private static TrailerItemModel convert(TrailerItem item) {

        TrailerItemModel trailerItemModel = null;

        if(item!=null){
            trailerItemModel = new TrailerItemModel();
            trailerItemModel.setName(item.getName());
            trailerItemModel.setSource(item.getSource());
            trailerItemModel.setType(item.getType());
            trailerItemModel.setSize(item.getSize());
        }
        return trailerItemModel;
    }


    public static ContentValues convert(TrailerItemModel trailer, String movieId) {
        ContentValues values = new ContentValues();
        values.put(MoviesContract.VideoEntry._ID, trailer.getId());
        values.put(MoviesContract.VideoEntry.COLUMN_MOVIE_ID, movieId);
        values.put(MoviesContract.VideoEntry.COLUMN_NAME, trailer.getName());
        values.put(MoviesContract.VideoEntry.COLUMN_KEY, trailer.getKey());
        return values;
    }

    public static ContentValues[] convert(List<TrailerItemModel> trailers,String movieId) {
        ContentValues[] values = new ContentValues[trailers.size()];

        int index=0;
        for(TrailerItemModel review : trailers) {
            values[index] = convert(review,movieId);
            index++;
        }

        return values;
    }

}