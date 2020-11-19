package com.integro.rsgs.adapters;
import android.content.Context;
import android.content.Intent;
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

import java.util.ArrayList;

public class LayMissionAdapter extends RecyclerView.Adapter<LayMissionAdapter.MyViewHolder> {
    Context context;
    ArrayList<LayMission> layMissionArrayList;

    public LayMissionAdapter(Context context, ArrayList<LayMission> layMissionArrayList) {
        this.context = context;
        this.layMissionArrayList = layMissionArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_lay_mission,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final LayMission layMission=layMissionArrayList.get(position);


        holder.tvlmTitle.setText(layMission.getTitle());
        holder.tvlmDescription.setText(layMission.getDescription());
        Glide.with(context)
                .load(layMission.getImage())
                .into(holder.ivlmImage);
    }

    @Override
    public int getItemCount() {
        return layMissionArrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewLayMission;
        ImageView ivlmImage;
        TextView tvlmTitle,tvlmDescription,tvlmReadMore;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewLayMission=itemView.findViewById(R.id.cr_LayMission);
            ivlmImage=itemView.findViewById(R.id.iv_LayMissionImage);
            tvlmTitle=itemView.findViewById(R.id.tv_lmTitle);
            tvlmDescription=itemView.findViewById(R.id.tv_lmDescription);
            //tvlmReadMore=itemView.findViewById(R.id.tv_lmReadMore);

        }
    }
}
