package com.integro.rsgs.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClients {

    private static final String BASE_URL= "http://goodshepherdbangalore.org/gs_app/";
    private static Retrofit retrofit=null;
    public static Retrofit getClients(){
        if (retrofit==null){
            retrofit= new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
