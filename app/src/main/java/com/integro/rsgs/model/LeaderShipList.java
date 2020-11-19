package com.integro.rsgs.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LeaderShipList {

    @SerializedName("rgs_leadership")
    private ArrayList<LeaderShip>leaderShipArrayList;

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

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }
    public ArrayList<LeaderShip>getLeaderShipArrayList()
    {
        return leaderShipArrayList;
    }
    public void setLeaderShipArrayList(ArrayList<LeaderShip>leaderShipArrayList)
    {
        this.leaderShipArrayList=leaderShipArrayList;
    }
}