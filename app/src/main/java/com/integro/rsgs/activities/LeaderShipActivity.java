package com.integro.rsgs.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.integro.rsgs.R;
import com.integro.rsgs.adapters.LayMissionAdapter;
import com.integro.rsgs.adapters.LeaderShipAdapter;
import com.integro.rsgs.api.ApiClients;
import com.integro.rsgs.api.ApiServices;
import com.integro.rsgs.model.LayMission;
import com.integro.rsgs.model.LayMissionList;
import com.integro.rsgs.model.LeaderShip;
import com.integro.rsgs.model.LeaderShipList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class LeaderShipActivity extends AppCompatActivity {

    ApiServices apiServices;
    RecyclerView rvLeaderShip;
    LinearLayoutManager manager;
    LeaderShipAdapter leaderShipAdapter;
    ArrayList<LeaderShip> leaderShipArrayList;
    Call<LeaderShipList> leaderShipListCall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_ship);
         getSupportActionBar().hide();

        apiServices = ApiClients.getClients().create(ApiServices.class);
        rvLeaderShip=findViewById(R.id.rv_LeaderShip);
        manager = new LinearLayoutManager(this);
        rvLeaderShip.setLayoutManager(manager);
        leaderShipArrayList = new ArrayList<>();
        getLeaderShipList();
    }
    public void getLeaderShipList() {
        String date = "2020-11-16 02:29:51";
        leaderShipListCall = apiServices.getLeaderShipList(date);
        leaderShipListCall.enqueue(new Callback<LeaderShipList>() {
            @Override
            public void onResponse(Call<LeaderShipList> call, Response<LeaderShipList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getLeaderShipArrayList() != null) {
                        int size = response.body().getLeaderShipArrayList().size();
                        Log.d("RESPONSE", "news Size " + size);
                        leaderShipArrayList.addAll(response.body().getLeaderShipArrayList());
                        if (leaderShipArrayList.size() > 0) {
                            leaderShipAdapter = new LeaderShipAdapter(getApplicationContext(), leaderShipArrayList);
                            rvLeaderShip.setAdapter(leaderShipAdapter);
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
            public void onFailure(Call<LeaderShipList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onfailure:" + t.getMessage());

            }
        });
    }
}
