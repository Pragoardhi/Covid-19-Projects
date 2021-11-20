package com.example.covid19apps.API;

import com.example.covid19apps.API.MyAPIEndPointInterFace;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private MyAPIEndPointInterFace API;

    public RetrofitInstance(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyAPIEndPointInterFace.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        retrofit.create(MyAPIEndPointInterFace.class);
        API = retrofit.create(MyAPIEndPointInterFace.class);
    }

    public MyAPIEndPointInterFace getAPI(){
        return API;
    }
}
