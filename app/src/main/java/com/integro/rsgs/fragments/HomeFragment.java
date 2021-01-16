package com.integro.rsgs.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.integro.rsgs.R;
import com.integro.rsgs.activities.EducationActivity;
import com.integro.rsgs.activities.LayMissionActivity;
import com.integro.rsgs.activities.SocialActionActivity;
import com.integro.rsgs.activities.WebActivity;
import com.integro.rsgs.adapters.NewsViewPagerAdapter;
import com.integro.rsgs.api.ApiClients;
import com.integro.rsgs.api.ApiServices;
import com.integro.rsgs.model.News;
import com.integro.rsgs.model.NewsList;
import java.util.ArrayList;

import pl.pzienowicz.autoscrollviewpager.AutoScrollViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment implements View.OnClickListener {

    TextView tvProject, tvOutReach, tvLayMission, tvEducation, tvSocialAction;
    ImageView ivDonate;

    private ApiServices apiServices;
    private AutoScrollViewPager viewPagerNews;
    private ArrayList<News> newsArrayList;
    Call<NewsList> newsListCall;
    private NewsViewPagerAdapter newsViewPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        apiServices = ApiClients.getClients().create(ApiServices.class);
        newsArrayList = new ArrayList<>();
        viewPagerNews = view.findViewById(R.id.vpNews);

        tvProject = view.findViewById(R.id.tv_project);
        tvOutReach = view.findViewById(R.id.tv_outreach);
        tvLayMission = view.findViewById(R.id.tv_laymission);
        tvEducation = view.findViewById(R.id.tv_education);
        tvSocialAction = view.findViewById(R.id.tv_socialaction);
        ivDonate=view.findViewById(R.id.iv_donate);


        tvLayMission.setOnClickListener(this);
        tvEducation.setOnClickListener(this);
        tvSocialAction.setOnClickListener(this);
        ivDonate.setOnClickListener(this);
        getNewsList();
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
            case R.id.iv_donate:
                Intent intentDonate = new Intent(getContext(), WebActivity.class);
                String url = "http://snehadhara.goodshepherdbangalore.org/payment.phpgoogle-services.json";
                intentDonate.putExtra("TAG", url);
                startActivity(intentDonate);
                break;

        }
    }
    private void getNewsList() {
        String date = "2020-11-16 02:01:41";
        newsListCall = apiServices.getNewsList(date);
        newsListCall.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                //checking response success or not
                if (response.isSuccessful()) {
                    //success api response

                    //response contains data or not
                    if (response.body().getSuccess() == 1) {
                        //data available

                        //getting the size of the arraylist
                        int size = response.body().getNewsArrayList().size();

                        //checking size is 0 or not
                        if (size > 0) {
                            newsArrayList.addAll(response.body().getNewsArrayList());
                            newsViewPagerAdapter = new NewsViewPagerAdapter(getContext(), newsArrayList);
                            viewPagerNews.setAdapter(newsViewPagerAdapter);
                            viewPagerNews.startAutoScroll(3000);
                            viewPagerNews.setCycle(true);

                        } else {
                            // showing message if size is 0
                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        //data not available
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
                }
                @Override
                public void onFailure(Call<NewsList> call, Throwable t) {
                    Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            }
        }




