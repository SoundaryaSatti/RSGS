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
import com.integro.rsgs.model.LeaderShip;

import java.util.ArrayList;

public class LeaderShipAdapter extends RecyclerView.Adapter<LeaderShipAdapter.MyViewHolder> {

    Context context;
    ArrayList<LeaderShip> leaderShipArrayList;

    public LeaderShipAdapter(Context context, ArrayList<LeaderShip> leaderShipArrayList) {
        this.context = context;
        this.leaderShipArrayList = leaderShipArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_leader_ship,parent,false);
        return new LeaderShipAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final LeaderShip leaderShip=leaderShipArrayList.get(position);

        holder.tvlsTitle.setText(leaderShip.getTitle());
        holder.tvlsDescription.setText(leaderShip.getDescription());
        Glide.with(context)
                .load(leaderShip.getImage())
                .into(holder.ivlsImage);
    }

    @Override
    public int getItemCount() {
        return leaderShipArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewLeaderShip;
        ImageView ivlsImage;
        TextView tvlsTitle,tvlsDescription;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewLeaderShip=itemView.findViewById(R.id.cr_LeaderShip);
            ivlsImage=itemView.findViewById(R.id.iv_LeaderShipImage);
            tvlsTitle=itemView.findViewById(R.id.tv_lsTitle);
            tvlsDescription=itemView.findViewById(R.id.tv_lsDescription);
            //tvlmReadMore=itemView.findViewById(R.id.tv_lmReadMore);

        }
    }
}
