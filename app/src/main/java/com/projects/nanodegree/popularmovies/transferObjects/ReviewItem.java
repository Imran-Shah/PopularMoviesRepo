package com.projects.nanodegree.popularmovies.transferObjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Imran on 12/18/16.
 */

public class ReviewItem {

    @SerializedName(value = "id")
    String id;

    @SerializedName(value = "author")
    String author;

    @SerializedName(value = "content")
    String content;

    @SerializedName(value = "url")
    String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
