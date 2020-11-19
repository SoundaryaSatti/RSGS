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

import com.integro.rsgs.R;
import com.integro.rsgs.model.Education;
import com.integro.rsgs.model.LayMission;

import java.util.ArrayList;

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.MyViewHolder> {

    Context context;
    ArrayList<Education>educationArrayList;

    public EducationAdapter(Context context, ArrayList<Education> educationArrayList) {
        this.context = context;
        this.educationArrayList = educationArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_education,parent,false);
        return new EducationAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Education education=educationArrayList.get(position);

        holder.tveduTitle.setText(education.getTitle());
        holder.tveduDescription.setText(education.getDescription());
    }

    @Override
    public int getItemCount() {
        return educationArrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewEducation;
        TextView tveduTitle,tveduDescription;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewEducation=itemView.findViewById(R.id.cr_Education);
            tveduTitle=itemView.findViewById(R.id.tv_eduTitle);
            tveduDescription=itemView.findViewById(R.id.tv_eduDescription);

        }
    }

}
