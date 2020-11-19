package com.integro.rsgs.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LayMissionList {
    @SerializedName("rgs_mission")
    private ArrayList<LayMission>layMissionArrayList;

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

    public ArrayList<LayMission>getLayMissionArrayList(){
        return layMissionArrayList;
    }
   public void setLayMissionArrayList(ArrayList<LayMission>layMissionArrayList){
        this.layMissionArrayList=layMissionArrayList;
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
