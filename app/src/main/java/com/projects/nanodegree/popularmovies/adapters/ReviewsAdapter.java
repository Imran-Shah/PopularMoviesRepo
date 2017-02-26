package com.projects.nanodegree.popularmovies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projects.nanodegree.popularmovies.R;
import com.projects.nanodegree.popularmovies.models.ReviewItemModel;
import com.projects.nanodegree.popularmovies.models.ReviewsResponseModel;

import java.util.List;

/**
 * Created by Imran on 1/22/17.
 */

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder> {

    private static final String NO_REVIEWS_WERE_RETURNED_FOR_THIS_MOVIE = "No reviews were returned for this movie";
    Context context;
    List<ReviewItemModel> reviewList;
    ReviewItemClickListener reviewItemClickListener;

    public ReviewsAdapter(Context context, ReviewsResponseModel reviewsResponseModel, ReviewItemClickListener reviewItemClickListener ){

        this.context = context;
        this.reviewList = reviewsResponseModel!=null ? reviewsResponseModel.getReviewList(): null;
        this.reviewItemClickListener = reviewItemClickListener;
    }
    @Override
    public ReviewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review_recycler_view, parent, false);
        ReviewsViewHolder viewHolder = new ReviewsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ReviewsViewHolder holder, int position) {
        ReviewItemModel reviewItemModel = reviewList!=null && reviewList.size()>0? reviewList.get(position):null;

        if(reviewItemModel!=null){
            holder.userName.setText(reviewItemModel.getAuthor());
            holder.review.setText(reviewItemModel.getContent());
            holder.readMore.setVisibility(View.VISIBLE);
        }else if(reviewItemModel==null || reviewItemModel.getContent()==null){
            holder.userName.setText(NO_REVIEWS_WERE_RETURNED_FOR_THIS_MOVIE);
            holder.readMore.setVisibility(View.GONE);
            holder.review.setVisibility(View.GONE);
        }


    }


    @Override
    public int getItemCount() {
        return reviewList!=null && reviewList.size()>0 ? reviewList.size():1;
    }

    public interface ReviewItemClickListener{
        void onReviewItemClicked(String url);
    }

    public class ReviewsViewHolder extends RecyclerView.ViewHolder{

        TextView userName;
        TextView review;
        TextView readMore;


        public ReviewsViewHolder(View itemView) {
            super(itemView);
            userName = (TextView) itemView.findViewById(R.id.userName);
            review = (TextView) itemView.findViewById(R.id.review);
            readMore = (TextView) itemView.findViewById(R.id.readMore);

            readMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(reviewList!=null && reviewList.get(getAdapterPosition())!=null)
                    reviewItemClickListener.onReviewItemClicked(reviewList.get(getAdapterPosition()).getUrl());
                }
            });

        }
    }
}
