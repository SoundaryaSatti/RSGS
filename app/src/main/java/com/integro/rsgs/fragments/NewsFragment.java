package com.integro.rsgs.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.integro.rsgs.R;
import com.integro.rsgs.adapters.NewsAdapter;
import com.integro.rsgs.api.ApiClients;
import com.integro.rsgs.api.ApiServices;
import com.integro.rsgs.model.News;
import com.integro.rsgs.model.NewsList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class NewsFragment extends Fragment {
    ApiServices apiServices;
    private RecyclerView rvnews;
    LinearLayoutManager manager;
    //StaggeredGridLayoutManager manager;
    NewsAdapter newsAdapter;
    ArrayList<News> newsArrayList;
    Call<NewsList> newsListCall;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        apiServices = ApiClients.getClients().create(ApiServices.class);
        rvnews = view.findViewById(R.id.rv_news);
        newsArrayList = new ArrayList<>();
        //manager = new StaggeredGridLayoutManager(2,RecyclerView.VERTICAL);
        manager = new LinearLayoutManager(getContext());
        rvnews.setLayoutManager(manager);
        getNewsList();
        return view;
    }

    public void getNewsList() {
        String date = "2020-11-16 02:01:41";
        newsListCall = apiServices.getNewsList(date);
        newsListCall.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                Log.i("RESONSE", "" + response.isSuccessful());
                if (response.isSuccessful()) {
                    if (response.body().getSuccess() == 1) {
                        int size = response.body().getNewsArrayList().size();
                        if (size > 0) {
                            newsArrayList.addAll(response.body().getNewsArrayList());
                            rvnews.setLayoutManager(manager);
                            rvnews.setHasFixedSize(true);
                            newsAdapter = new NewsAdapter(getContext(), newsArrayList);
                            rvnews.setAdapter(newsAdapter);
                        } else {
                            Toast.makeText(getContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getContext(), "Something went wrong, try again", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onfailure:" + t.getMessage());
            }
        });
    }


}
