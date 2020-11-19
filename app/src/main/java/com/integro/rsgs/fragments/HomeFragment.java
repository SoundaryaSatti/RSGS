package com.integro.rsgs.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.integro.rsgs.R;
import com.integro.rsgs.activities.EducationActivity;
import com.integro.rsgs.activities.LayMissionActivity;
import com.integro.rsgs.activities.SocialActionActivity;


public class HomeFragment extends Fragment implements View.OnClickListener {

TextView tvProject,tvOutReach,tvLayMission,tvEducation,tvSocialAction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);


        tvProject=view.findViewById(R.id.tv_project);
        tvOutReach=view.findViewById(R.id.tv_outreach);
        tvLayMission=view.findViewById(R.id.tv_laymission);
        tvEducation=view.findViewById(R.id.tv_education);
        tvSocialAction=view.findViewById(R.id.tv_socialaction);



        tvLayMission.setOnClickListener(this);
        tvEducation.setOnClickListener(this);
        tvSocialAction.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.tv_project:
                 break;
             case R.id.tv_outreach:
                 break;
             case R.id.tv_laymission:
                 Intent intentLayMission = new Intent(getContext(), LayMissionActivity.class);
                 startActivity(intentLayMission);
                 break;
             case R.id.tv_education:
                 Intent intentEducation = new Intent(getContext(), EducationActivity.class);
                 startActivity(intentEducation);
                 break;
             case R.id.tv_socialaction:
                 Intent intentSocialAction = new Intent(getContext(), SocialActionActivity.class);
                 startActivity(intentSocialAction);
                 break;
         }

    }
}
