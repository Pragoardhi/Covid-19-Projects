package com.example.covid19apps.CovidDataAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyAPIEndPointInterFace {
    String BASE_URL= "https://corona.lmao.ninja/v2/";

    @GET("countries")
    Call<List<CovidDataAPI>> getCovidData();
}
