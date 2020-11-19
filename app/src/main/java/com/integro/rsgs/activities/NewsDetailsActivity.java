package com.integro.rsgs.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.integro.rsgs.R;
import com.integro.rsgs.model.News;

public class NewsDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        getSupportActionBar().hide();


        final  String TAG="NewsDetailsActivity";
        News news = (News) getIntent().getSerializableExtra("NEWS");

        ImageView ivImageND=findViewById(R.id.iv_NewsDImage);
        TextView tvTitleND = findViewById(R.id.tv_newstitle);
        TextView tvDateND=findViewById(R.id.tv_newsdate);
        TextView tvDiscriptionND = findViewById(R.id.tv_newsdescription);

        tvDateND.setText(news.getDate());
        tvTitleND.setText(news.getTitle());
        tvDiscriptionND.setText(news.getDescription());
        Glide.with(getApplicationContext()).load(news.getImage()).into(ivImageND);

    }
    }

