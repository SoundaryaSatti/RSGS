package com.integro.rsgs.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.integro.rsgs.R;
import com.integro.rsgs.adapters.NewsImagesAdapterVp;
import com.integro.rsgs.api.ApiClients;
import com.integro.rsgs.api.ApiServices;
import com.integro.rsgs.model.News;
import com.integro.rsgs.model.NewsImage;
import com.integro.rsgs.model.NewsImageList;

import java.util.ArrayList;

import pl.pzienowicz.autoscrollviewpager.AutoScrollViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsDetailsActivity extends AppCompatActivity {
    private static final String TAG = "NewsDetailsActivity";

    String itemId;

    ArrayList<NewsImage> newsImagesArrayList;
    AutoScrollViewPager vpNewsImages;
    NewsImagesAdapterVp newsImagesAdapterVp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        getSupportActionBar().hide();

        newsImagesArrayList = new ArrayList<>();

        final  String TAG="NewsDetailsActivity";
        News news = (News) getIntent().getSerializableExtra("NEWS");

        //ImageView ivImageND=findViewById(R.id.iv_NewsDImage);
        vpNewsImages=findViewById(R.id.vp_NewsImage);

        TextView tvTitleND = findViewById(R.id.tv_newstitle);
        TextView tvDateND=findViewById(R.id.tv_newsdate);
        TextView tvDiscriptionND = findViewById(R.id.tv_newsdescription);

        tvDateND.setText(news.getDate());
        tvTitleND.setText(news.getTitle());
        tvDiscriptionND.setText(news.getDescription());
       // Glide.with(getApplicationContext()).load(news.getImage()).into(ivImageND);
    getNewsImagesList();

    }
    public void getNewsImagesList() {
        Call<NewsImageList> newsImagesCall;
        newsImagesCall = ApiClients.getClients().create(ApiServices.class).getNewsImageLIst(itemId);
        newsImagesCall.enqueue(new Callback<NewsImageList>() {
            @Override
            public void onResponse(Call<NewsImageList> call, Response<NewsImageList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getNewsImageArrayList() != null) {
                        int size = response.body().getNewsImageArrayList().size();
                        Log.d("response", "NewsImages" + size);
                        if (size > 0) {
                            newsImagesArrayList.addAll(response.body().getNewsImageArrayList());
                            newsImagesAdapterVp = new NewsImagesAdapterVp(getApplicationContext(),newsImagesArrayList);
                            vpNewsImages.startAutoScroll(3000);
                            vpNewsImages.setCycle(true);
                            vpNewsImages.setAdapter(newsImagesAdapterVp);
                            Log.i(TAG, "onResponse: " + newsImagesArrayList.size());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsImageList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onfailure:" + t.getMessage());
            }
        });
    }
    }

