package com.integro.rsgs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.integro.rsgs.R;
import com.integro.rsgs.model.NewsImage;

import java.util.ArrayList;

public class NewsImagesAdapterVp extends PagerAdapter {
    Context context;
    ArrayList<NewsImage>newsImageArrayList;
    LayoutInflater inflater;

    public NewsImagesAdapterVp(Context context, ArrayList<NewsImage> newsImageArrayList) {
        this.context = context;
        this.newsImageArrayList = newsImageArrayList;
    }

    @Override
    public int getCount() {
        return newsImageArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView ivNewsImage;

        View view= LayoutInflater.from(context).inflate(R.layout.card_news_image,container,false);

        ivNewsImage=view.findViewById(R.id.iv_NewsImage);

        Glide.with(context)
                .load(newsImageArrayList.get(position).getImage())
                .into(ivNewsImage);

        /*String url="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSppVj8MavDX1f4O-Cx_VKoOsdIrKoQNxPNjkvzh_kxPSVCogu02W1OzMG-XA&s=10";
        Glide.with(context)
                .load(url)
                .into(ivNewsImage);*/

        ((ViewPager)container).addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((LinearLayout) object);
    }
}
