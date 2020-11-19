package com.integro.rsgs.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NewsList {
    @SerializedName("rgs_news")
    private ArrayList<News> newsArrayList;
    private int success;
    private String message;

    public int getSuccess ()
    {
        return success;
    }

    public void setSuccess (int success)
    {
        this.success = success;
    }

    public ArrayList<News> getNewsArrayList()
    {
        return newsArrayList;
    }
    public void setNewsArrayList(ArrayList<News>newsArrayList)
    {
        this.newsArrayList = newsArrayList;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

}
