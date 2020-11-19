package com.integro.rsgs.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EducationList {
    @SerializedName("rgs_education")
    private ArrayList<Education>educationArrayList;

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
    public ArrayList<Education>getEducationArrayList(){
        return educationArrayList;
    }

   public void setEducationArrayList(ArrayList<Education>educationArrayList){
        this.educationArrayList=educationArrayList;
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
