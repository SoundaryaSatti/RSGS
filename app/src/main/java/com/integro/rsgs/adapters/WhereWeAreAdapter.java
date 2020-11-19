package com.integro.rsgs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.integro.rsgs.R;
import com.integro.rsgs.model.Education;
import com.integro.rsgs.model.Ministries;
import com.integro.rsgs.model.WhereWeAre;

import java.util.ArrayList;

public class WhereWeAreAdapter extends RecyclerView.Adapter<WhereWeAreAdapter.MyViewHolder> {

    Context context;
    ArrayList<WhereWeAre>whereWeAreArrayList;

    public WhereWeAreAdapter(Context context, ArrayList<WhereWeAre> whereWeAreArrayList) {
        this.context = context;
        this.whereWeAreArrayList = whereWeAreArrayList;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_where_we_are,parent,false);
        return new WhereWeAreAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final WhereWeAre whereWeAre=whereWeAreArrayList.get(position);

        holder.tvWWRTitle.setText(whereWeAre.getTitle());
        holder.tvWWRDescription.setText(whereWeAre.getDescription());

    }

    @Override
    public int getItemCount() {
        return whereWeAreArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewWWR;
        TextView tvWWRTitle,tvWWRDescription;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewWWR=itemView.findViewById(R.id.cr_WWA);
            tvWWRTitle=itemView.findViewById(R.id.tv_WWRTitle);
            tvWWRDescription=itemView.findViewById(R.id.tv_WWRDescription);

        }
    }

}
