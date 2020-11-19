package com.integro.rsgs.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.integro.rsgs.R;
import com.integro.rsgs.adapters.MediaAdapter;
import com.integro.rsgs.api.ApiClients;
import com.integro.rsgs.api.ApiServices;
import com.integro.rsgs.model.Media;
import com.integro.rsgs.model.MediaList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MediaActivity extends AppCompatActivity {

    ApiServices apiServices;
    RecyclerView rvMedia;
    // LinearLayoutManager manager;
    StaggeredGridLayoutManager manager;
    MediaAdapter mediaAdapter;
    ArrayList<Media> mediaArrayList;
    Call<MediaList> mediaListCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        getSupportActionBar();

        apiServices = ApiClients.getClients().create(ApiServices.class);
        rvMedia = findViewById(R.id.rv_Media);
        //manager = new LinearLayoutManager(this);
        manager = new StaggeredGridLayoutManager(2,RecyclerView.VERTICAL);
        rvMedia.setLayoutManager(manager);
        mediaArrayList = new ArrayList<>();
        getMediaList();
    }
    public void getMediaList() {
        String date = "2020-11-16 02:42:31";
        mediaListCall = apiServices.getMediaList(date);
        mediaListCall.enqueue(new Callback<MediaList>() {
            @Override
            public void onResponse(Call<MediaList> call, Response<MediaList> response) {
                Log.i("RESONSE", "" + response.isSuccessful());
                if (response.isSuccessful()) {
                    if (response.body().getSuccess() == 1) {
                        int size = response.body().getMediaArrayList().size();
                        if (size > 0) {
                            mediaArrayList.addAll(response.body().getMediaArrayList());
                            mediaAdapter = new MediaAdapter(getApplicationContext(), mediaArrayList);
                            rvMedia.setAdapter(mediaAdapter);
                            rvMedia.setHasFixedSize(true);
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
            public void onFailure(Call<MediaList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onfailure:" + t.getMessage());

            }
        });
    }
}
