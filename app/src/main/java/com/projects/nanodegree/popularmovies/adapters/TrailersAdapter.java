package com.projects.nanodegree.popularmovies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.projects.nanodegree.popularmovies.R;
import com.projects.nanodegree.popularmovies.models.TrailerItemModel;
import com.projects.nanodegree.popularmovies.models.TrailersResponseModel;
import com.projects.nanodegree.popularmovies.utils.Constants;
import com.squareup.picasso.Picasso;

/**
 * Created by Imran on 12/25/16.
 */

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.TrailersViewHolder> {

    Context context;
    TrailersResponseModel trailersResponseModel;
    TrailerClickListener trailerClickListener;

    public TrailersAdapter(Context context, TrailersResponseModel trailersResponseModel, TrailerClickListener trailerClickListener){
        this.context = context;
        this.trailersResponseModel = trailersResponseModel;
        this.trailerClickListener = trailerClickListener;
    }

    @Override
    public TrailersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trailers_recycler_view, parent, false);
        return new TrailersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrailersViewHolder holder, int position) {

        if(trailersResponseModel!=null && trailersResponseModel.getTrailerList()!=null && trailersResponseModel.getTrailerList().size()>0){

            TrailerItemModel trailerItemModel = trailersResponseModel.getTrailerList().get(position);

            Picasso.with(context).load(Constants.YOUTUBE_IMAGE_BASE_URL + trailerItemModel.getSource() + Constants.YOUTUBE_IMAGE_EXTENSION)
                    .into(holder.iv_trailer);
            holder.tv_no_trailers.setVisibility(View.GONE);

        }else {
            holder.tv_no_trailers.setText("No trailers were returned for this movie");
            holder.iv_trailer.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return trailersResponseModel!=null && trailersResponseModel.getTrailerList()!=null && trailersResponseModel.getTrailerList().size()>0? trailersResponseModel.getTrailerList().size():1;
    }

    public interface TrailerClickListener{
        void onTrailerClicked(String key);
    }

    public class TrailersViewHolder extends RecyclerView.ViewHolder{

        ImageView iv_trailer;
        TextView tv_no_trailers;
        public TrailersViewHolder(View itemView) {
            super(itemView);
            iv_trailer = (ImageView)itemView.findViewById(R.id.iv_trailer);
            tv_no_trailers = (TextView)itemView.findViewById(R.id.tv_no_trailers);
            iv_trailer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(trailersResponseModel!=null && trailersResponseModel.getTrailerList()!=null && trailersResponseModel.getTrailerList().get(getAdapterPosition())!=null)
                    trailerClickListener.onTrailerClicked(trailersResponseModel.getTrailerList().get(getAdapterPosition()).getSource());
                }
            });
        }
    }
}
