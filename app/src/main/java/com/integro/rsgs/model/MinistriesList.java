package com.integro.rsgs.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MinistriesList {

    @SerializedName("rgs_ministries")
    private ArrayList<Ministries>ministriesArrayList;

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

    public ArrayList<Ministries>getMinistriesArrayList(){
        return ministriesArrayList;
    }
    public void setMinistriesArrayList(ArrayList<Ministries>ministriesArrayList){
        this.ministriesArrayList=ministriesArrayList;
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
