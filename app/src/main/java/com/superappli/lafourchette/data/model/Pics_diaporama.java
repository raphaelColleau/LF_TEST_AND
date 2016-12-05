package com.superappli.lafourchette.data.model;

import com.google.gson.annotations.SerializedName;

public class Pics_diaporama {

//    664x374
    @SerializedName("664x374")
    private String url;

    private String label;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLabel ()
    {
        return label;
    }

    public void setLabel (String label)
    {
        this.label = label;
    }

    @Override
    public String toString()
    {
        return "ClassPojo url " + url  + " label = "+label+"]";
    }
}