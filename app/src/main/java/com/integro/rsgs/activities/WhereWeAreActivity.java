package com.integro.rsgs.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.integro.rsgs.R;
import com.integro.rsgs.adapters.EducationAdapter;
import com.integro.rsgs.adapters.WhereWeAreAdapter;
import com.integro.rsgs.api.ApiClients;
import com.integro.rsgs.api.ApiServices;
import com.integro.rsgs.model.Education;
import com.integro.rsgs.model.EducationList;
import com.integro.rsgs.model.WhereWeAre;
import com.integro.rsgs.model.WhereWeAreList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class WhereWeAreActivity extends AppCompatActivity {
    ApiServices apiServices;
    RecyclerView rvWWR;
    LinearLayoutManager manager;
    WhereWeAreAdapter whereWeAreAdapter;
    ArrayList<WhereWeAre> weAreArrayList;
    Call<WhereWeAreList> whereWeAreListCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_where_we_are);
        getSupportActionBar().hide();

        apiServices = ApiClients.getClients().create(ApiServices.class);
        rvWWR=findViewById(R.id.rv_WWR);
        manager = new LinearLayoutManager(this);
        rvWWR.setLayoutManager(manager);
        weAreArrayList = new ArrayList<>();
        getWhereWeAreList();
    }
    public void getWhereWeAreList() {
        String date = "2020-11-16 02:23:35";
        whereWeAreListCall= apiServices.getWhereWeAreList(date);
        whereWeAreListCall.enqueue(new Callback<WhereWeAreList>() {
            @Override
            public void onResponse(Call<WhereWeAreList> call, Response<WhereWeAreList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getWhereWeAreArrayList() != null) {
                        int size = response.body().getWhereWeAreArrayList().size();
                        Log.d("RESPONSE", "news Size " + size);
                        weAreArrayList.addAll(response.body().getWhereWeAreArrayList());
                        if (weAreArrayList.size() > 0) {
                            whereWeAreAdapter = new WhereWeAreAdapter(getApplicationContext(), weAreArrayList);
                            rvWWR.setAdapter(whereWeAreAdapter);
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
            public void onFailure(Call<WhereWeAreList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onfailure:" + t.getMessage());

            }
        });

    }
}
