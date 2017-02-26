package com.projects.nanodegree.popularmovies.transferObjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Imran on 12/18/16.
 */

public class TrailerItem {


    @SerializedName(value = "name")
    String name;

    @SerializedName(value = "source")
    String source;

    @SerializedName(value = "size")
    String size;

    @SerializedName(value = "type")
    String type;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
