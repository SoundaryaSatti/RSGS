package com.integro.rsgs.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MediaList {
    @SerializedName("rgs_gallery")
    private ArrayList<Media>mediaArrayList;

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

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public ArrayList<Media>getMediaArrayList(){
        return mediaArrayList;
    }
    public void setMediaArrayList(ArrayList<Media>mediaArrayList){
        this.mediaArrayList=mediaArrayList;
    }

}
