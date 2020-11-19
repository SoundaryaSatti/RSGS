package com.integro.rsgs.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.integro.rsgs.R;
import com.integro.rsgs.adapters.EducationAdapter;
import com.integro.rsgs.adapters.LayMissionAdapter;
import com.integro.rsgs.api.ApiClients;
import com.integro.rsgs.api.ApiServices;
import com.integro.rsgs.model.Education;
import com.integro.rsgs.model.EducationList;
import com.integro.rsgs.model.LayMission;
import com.integro.rsgs.model.LayMissionList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class EducationActivity extends AppCompatActivity {
    ApiServices apiServices;
    RecyclerView rvEducation;
    LinearLayoutManager manager;
    EducationAdapter educationAdapter;
    ArrayList<Education> educationArrayList;
    Call<EducationList> educationListCall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);
        getSupportActionBar().hide();

        apiServices = ApiClients.getClients().create(ApiServices.class);
        rvEducation=findViewById(R.id.rv_Education);
        manager = new LinearLayoutManager(this);
        rvEducation.setLayoutManager(manager);
        educationArrayList = new ArrayList<>();
        getEducationList();

    }
    public void getEducationList() {
        String date = "2020-11-16 02:23:35";
        educationListCall= apiServices.getEducationList(date);
        educationListCall.enqueue(new Callback<EducationList>() {
            @Override
            public void onResponse(Call<EducationList> call, Response<EducationList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getEducationArrayList() != null) {
                        int size = response.body().getEducationArrayList().size();
                        Log.d("RESPONSE", "news Size " + size);
                        educationArrayList.addAll(response.body().getEducationArrayList());
                        if (educationArrayList.size() > 0) {
                            educationAdapter = new EducationAdapter(getApplicationContext(), educationArrayList);
                            rvEducation.setAdapter(educationAdapter);
                        } else {
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Something went wrong, try again", Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onFailure(Call<EducationList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onfailure:" + t.getMessage());

            }
        });

    }
}
