package com.projects.nanodegree.popularmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Imran on 8/18/16.
 */
public class MovieDetailModel implements Parcelable{

    private String posterPath;
    private String isAdult;
    private String overview;
    private String releaseDate;
    private String id;
    private String originalTitle;
    private String originalLanguage;
    private String title;
    private String backdropPath;
    private String popularity;
    private String voteCount;
    private String video;
    private String voteAverage;
    private boolean isFavorite;

    List<ReviewItemModel> reviewsList;

    List<TrailerItemModel> trailerList;

    public MovieDetailModel() {
    }


    protected MovieDetailModel(Parcel in) {
        posterPath = in.readString();
        isAdult = in.readString();
        overview = in.readString();
        releaseDate = in.readString();
        id = in.readString();
        originalTitle = in.readString();
        originalLanguage = in.readString();
        title = in.readString();
        backdropPath = in.readString();
        popularity = in.readString();
        voteCount = in.readString();
        video = in.readString();
        voteAverage = in.readString();
        isFavorite = in.readByte() != 0;
        reviewsList = in.createTypedArrayList(ReviewItemModel.CREATOR);
        trailerList = in.createTypedArrayList(TrailerItemModel.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(posterPath);
        dest.writeString(isAdult);
        dest.writeString(overview);
        dest.writeString(releaseDate);
        dest.writeString(id);
        dest.writeString(originalTitle);
        dest.writeString(originalLanguage);
        dest.writeString(title);
        dest.writeString(backdropPath);
        dest.writeString(popularity);
        dest.writeString(voteCount);
        dest.writeString(video);
        dest.writeString(voteAverage);
        dest.writeByte((byte) (isFavorite ? 1 : 0));
        dest.writeTypedList(reviewsList);
        dest.writeTypedList(trailerList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MovieDetailModel> CREATOR = new Creator<MovieDetailModel>() {
        @Override
        public MovieDetailModel createFromParcel(Parcel in) {
            return new MovieDetailModel(in);
        }

        @Override
        public MovieDetailModel[] newArray(int size) {
            return new MovieDetailModel[size];
        }
    };

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getIsAdult() {
        return isAdult;
    }

    public void setIsAdult(String isAdult) {
        this.isAdult = isAdult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(String voteCount) {
        this.voteCount = voteCount;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public List<ReviewItemModel> getReviewsList() {
        return reviewsList;
    }

    public void setReviewsList(List<ReviewItemModel> reviewsList) {
        this.reviewsList = reviewsList;
    }

    public List<TrailerItemModel> getTrailerList() {
        return trailerList;
    }

    public void setTrailerList(List<TrailerItemModel> trailerList) {
        this.trailerList = trailerList;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
