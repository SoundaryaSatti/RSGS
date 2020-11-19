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
import com.integro.rsgs.model.Ministries;
import com.integro.rsgs.model.WhereWeAre;

import java.util.ArrayList;

public class MinistriesAdapter extends RecyclerView.Adapter<MinistriesAdapter.MyViewHolder> {

    Context context;
    ArrayList<Ministries>ministriesArrayList;

    public MinistriesAdapter(Context context, ArrayList<Ministries> ministriesArrayList) {
        this.context = context;
        this.ministriesArrayList = ministriesArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_ministries,parent,false);
        return new MinistriesAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Ministries ministries=ministriesArrayList.get(position);

        holder.tvMinistriesTitle.setText(ministries.getTitle());
        holder.tvMinistriesDescription.setText(ministries.getDescription());

    }

    @Override
    public int getItemCount() {
        return ministriesArrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewMinistries;
        TextView tvMinistriesTitle,tvMinistriesDescription;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewMinistries=itemView.findViewById(R.id.cr_Ministries);
            tvMinistriesTitle=itemView.findViewById(R.id.tv_MinistriesTitle);
            tvMinistriesDescription=itemView.findViewById(R.id.tv_MinistriesDescription);

        }
    }

}
