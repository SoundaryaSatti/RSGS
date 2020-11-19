package com.integro.rsgs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.integro.rsgs.R;
import com.integro.rsgs.model.Media;

import java.util.ArrayList;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.MyViewHolder> {

    Context context;
    ArrayList<Media>mediaArrayList;

    public MediaAdapter(Context context, ArrayList<Media> mediaArrayList) {
        this.context = context;
        this.mediaArrayList = mediaArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_media,parent,false);
        return new MediaAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Media media=mediaArrayList.get(position);
        holder.tvMediaTitle.setText(media.getTitle());
        Glide.with(context).load(media.getImage()).into(holder.ivMediaImage);

    }

    @Override
    public int getItemCount() {
        return mediaArrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvMediaTitle;
        ImageView ivMediaImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMediaTitle=itemView.findViewById(R.id.tv_MediaTitle);
            ivMediaImage=itemView.findViewById(R.id.iv_MediaImage);
        }
    }

}
