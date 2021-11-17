package com.example.covid19apps.Login;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginJsonPlaceHolderApi {
    @Headers({
            "x-api-key: 454041184B0240FBA3AACD15A1F7A8BB"
    })
    @FormUrlEncoded
    @POST("login")
    Call<LoginPost> createPost(@FieldMap Map<String,String> fields);
}
