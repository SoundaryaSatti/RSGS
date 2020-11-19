package com.integro.rsgs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.integro.rsgs.R;
import com.integro.rsgs.model.LayMission;
import com.integro.rsgs.model.SocialAction;

import java.util.ArrayList;

public class SocialActionAdater extends RecyclerView.Adapter<SocialActionAdater.MyViewHolder> {
    Context context;
    ArrayList<SocialAction>socialActionArrayList;

    public SocialActionAdater(Context context, ArrayList<SocialAction> socialActionArrayList) {
        this.context = context;
        this.socialActionArrayList = socialActionArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_social_action,parent,false);
        return new SocialActionAdater.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final SocialAction socialAction=socialActionArrayList.get(position);


        holder.tvSATitle.setText(socialAction.getTitle());
        holder.tvSADescription.setText(socialAction.getDescription());
        Glide.with(context)
                .load(socialAction.getImage())
                .into(holder.ivSAImage);

    }

    @Override
    public int getItemCount() {
        return socialActionArrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewSocialAction;
        ImageView ivSAImage;
        TextView tvSATitle,tvSADescription;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewSocialAction=itemView.findViewById(R.id.cr_SocialAction);
            ivSAImage=itemView.findViewById(R.id.iv_SAImage);
            tvSATitle=itemView.findViewById(R.id.tv_SATitle);
            tvSADescription=itemView.findViewById(R.id.tv_SADescription);
            //tvlmReadMore=itemView.findViewById(R.id.tv_lmReadMore);

        }
    }
}
