package com.integro.rsgs.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.integro.rsgs.R;
import com.integro.rsgs.activities.NewsDetailsActivity;
import com.integro.rsgs.model.News;

import java.util.ArrayList;

public class NewsViewPagerAdapter extends PagerAdapter {

    Context context;
    ArrayList<News> newsArrayList;
    LayoutInflater inflater;

    public NewsViewPagerAdapter(Context context, ArrayList<News> newsArrayList) {
        this.context = context;
        this.newsArrayList = newsArrayList;
    }

    @Override
    public int getCount() {
        return newsArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView ivFullImage;

        TextView tvTitle;

        News news=newsArrayList.get(position);

        View view = LayoutInflater.from(context).inflate(R.layout.card_view_pager, container, false);

        ivFullImage = (ImageView) view.findViewById(R.id.vp_ImageH);
          tvTitle = view.findViewById(R.id.tv_titleH);
          tvTitle.setText(newsArrayList.get(position).getTitle());

        Glide.with(context)
                .load(newsArrayList.get(position).getImage())
                .into(ivFullImage);

        ((ViewPager) container).addView(view);

        ivFullImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, NewsDetailsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("NEWS", news);
                context.startActivity(intent);

            }
        });

        tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTitle=new Intent(context, NewsDetailsActivity.class);
                intentTitle.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intentTitle.putExtra("NEWS", news);
                context.startActivity(intentTitle);

            }
        });

        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }
}
