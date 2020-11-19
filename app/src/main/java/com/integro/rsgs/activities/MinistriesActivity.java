package com.integro.rsgs.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.integro.rsgs.R;
import com.integro.rsgs.adapters.MinistriesAdapter;
import com.integro.rsgs.adapters.WhereWeAreAdapter;
import com.integro.rsgs.api.ApiClients;
import com.integro.rsgs.api.ApiServices;
import com.integro.rsgs.model.Ministries;
import com.integro.rsgs.model.MinistriesList;
import com.integro.rsgs.model.WhereWeAre;
import com.integro.rsgs.model.WhereWeAreList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MinistriesActivity extends AppCompatActivity {

    ApiServices apiServices;
    RecyclerView rvMinistries;
    LinearLayoutManager manager;
    MinistriesAdapter ministriesAdapter;
    ArrayList<Ministries> ministriesArrayList;
    Call<MinistriesList> ministriesListCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ministries);
        getSupportActionBar().hide();

        apiServices = ApiClients.getClients().create(ApiServices.class);
        rvMinistries=findViewById(R.id.rv_Ministries);
        manager = new LinearLayoutManager(this);
        rvMinistries.setLayoutManager(manager);
        ministriesArrayList = new ArrayList<>();
        getMinistriesList();
    }
    public void getMinistriesList() {
        String date = "2020-11-16 02:38:43";
        ministriesListCall= apiServices.getMinistriesList(date);
        ministriesListCall.enqueue(new Callback<MinistriesList>() {
            @Override
            public void onResponse(Call<MinistriesList> call, Response<MinistriesList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getMinistriesArrayList() != null) {
                        int size = response.body().getMinistriesArrayList().size();
                        Log.d("RESPONSE", "news Size " + size);
                        ministriesArrayList.addAll(response.body().getMinistriesArrayList());
                        if (ministriesArrayList.size() > 0) {
                            ministriesAdapter = new MinistriesAdapter(getApplicationContext(), ministriesArrayList);
                            rvMinistries.setAdapter(ministriesAdapter);
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
            public void onFailure(Call<MinistriesList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onfailure:" + t.getMessage());

            }
        });

    }
}
