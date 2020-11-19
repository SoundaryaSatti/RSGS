package com.integro.rsgs.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.integro.rsgs.R;
import com.integro.rsgs.adapters.LayMissionAdapter;
import com.integro.rsgs.api.ApiClients;
import com.integro.rsgs.api.ApiServices;
import com.integro.rsgs.model.LayMission;
import com.integro.rsgs.model.LayMissionList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class LayMissionActivity extends AppCompatActivity {
    ApiServices apiServices;
    RecyclerView rvLayMission;
    LinearLayoutManager manager;
    LayMissionAdapter layMissionAdapter;
    ArrayList<LayMission> layMissionArrayList;
    Call<LayMissionList> layMissionListCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lay_mission);
        getSupportActionBar().hide();

        apiServices = ApiClients.getClients().create(ApiServices.class);
        rvLayMission=findViewById(R.id.rv_LayMission);
        manager = new LinearLayoutManager(this);
        rvLayMission.setLayoutManager(manager);
        layMissionArrayList = new ArrayList<>();
        getLayMissionList();
    }

    public void getLayMissionList() {
        String date = "2020-07-06 01:59:02";
        layMissionListCall = apiServices.getLayMissionList(date);
        layMissionListCall.enqueue(new Callback<LayMissionList>() {
            @Override
            public void onResponse(Call<LayMissionList> call, Response<LayMissionList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getLayMissionArrayList() != null) {
                        int size = response.body().getLayMissionArrayList().size();
                        Log.d("RESPONSE", "news Size " + size);
                        layMissionArrayList.addAll(response.body().getLayMissionArrayList());
                        if (layMissionArrayList.size() > 0) {
                            layMissionAdapter = new LayMissionAdapter(getApplicationContext(), layMissionArrayList);
                            rvLayMission.setAdapter(layMissionAdapter);
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
            public void onFailure(Call<LayMissionList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onfailure:" + t.getMessage());

            }
        });

    }
}
