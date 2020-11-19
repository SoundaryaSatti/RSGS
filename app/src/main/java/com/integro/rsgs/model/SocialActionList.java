package com.integro.rsgs.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SocialActionList {
    @SerializedName("rgs_social")
    ArrayList<SocialAction>socialActionArrayList;

    private String success;

    private String message;

    public ArrayList<SocialAction>getSocialActionArrayList()
    {
        return socialActionArrayList;
    }
    public void setSocialActionArrayList(ArrayList<SocialAction>socialActionArrayList)
    {
        this.socialActionArrayList=socialActionArrayList;
    }

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
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

}
