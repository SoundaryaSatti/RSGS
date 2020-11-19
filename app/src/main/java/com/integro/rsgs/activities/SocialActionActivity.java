package com.integro.rsgs.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.integro.rsgs.R;
import com.integro.rsgs.adapters.LayMissionAdapter;
import com.integro.rsgs.adapters.SocialActionAdater;
import com.integro.rsgs.api.ApiClients;
import com.integro.rsgs.api.ApiServices;
import com.integro.rsgs.model.LayMission;
import com.integro.rsgs.model.LayMissionList;
import com.integro.rsgs.model.SocialAction;
import com.integro.rsgs.model.SocialActionList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SocialActionActivity extends AppCompatActivity {
    ApiServices apiServices;
    RecyclerView rvSocialAction;
    LinearLayoutManager manager;
    SocialActionAdater socialActionAdater;
    ArrayList<SocialAction> socialActionArrayList;
    Call<SocialActionList> socialActionListCall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_action);
        getSupportActionBar().hide();

        apiServices = ApiClients.getClients().create(ApiServices.class);
        rvSocialAction=findViewById(R.id.rv_SocialAction);
        manager = new LinearLayoutManager(this);
        rvSocialAction.setLayoutManager(manager);
        socialActionArrayList = new ArrayList<>();
        getSocialActionList();
    }

    public void getSocialActionList() {
        String date = "2020-07-06 01:59:02";
        socialActionListCall = apiServices.getSocialActionList(date);
        socialActionListCall.enqueue(new Callback<SocialActionList>() {
            @Override
            public void onResponse(Call<SocialActionList> call, Response<SocialActionList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSocialActionArrayList() != null) {
                        int size = response.body().getSocialActionArrayList().size();
                        Log.d("RESPONSE", "news Size " + size);
                        socialActionArrayList.addAll(response.body().getSocialActionArrayList());
                        if (socialActionArrayList.size() > 0) {
                            socialActionAdater = new SocialActionAdater(getApplicationContext(),socialActionArrayList);
                            rvSocialAction.setAdapter(socialActionAdater);
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
            public void onFailure(Call<SocialActionList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onfailure:" + t.getMessage());

            }
        });

    }
}
