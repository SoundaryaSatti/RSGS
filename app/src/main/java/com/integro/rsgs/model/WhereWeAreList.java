package com.integro.rsgs.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WhereWeAreList {
    @SerializedName("rgs_whereweare")
    private ArrayList<WhereWeAre>whereWeAreArrayList;

    private String success;
    private String message;

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }
    public ArrayList<WhereWeAre>getWhereWeAreArrayList(){
        return whereWeAreArrayList;
    }
    public void setWhereWeAreArrayList(ArrayList<WhereWeAre>whereWeAreArrayList){
        this.whereWeAreArrayList=whereWeAreArrayList;
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
