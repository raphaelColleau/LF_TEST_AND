package com.superappli.lafourchette.data.model;

import com.google.gson.annotations.SerializedName;

public class Pics_main
{

    //    664x374
    @SerializedName("664x374")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString()
    {
        return "ClassPojo url " + url +"]";
    }
}