package com.example.covid19apps.Home.RecyclerView;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.covid19apps.Home.API.ResponseItem;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CovidDataRepository {

    private MyAPIEndPointInterFace API;
    private MutableLiveData<List<ResponseItem>> coListLiveData = new MutableLiveData<>();

    private final ExecutorService networkExecutor =
            Executors.newFixedThreadPool(4);
    private final Executor mainThread = new Executor() {
        private Handler handler = new Handler(Looper.getMainLooper());
        @Override
        public void execute(Runnable command) {
            handler.post(command);
        }
    };


    public CovidDataRepository(Application application){
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        API = retrofitInstance.getAPI();
    }

    public void getCovidDataFromNetwork(){
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try{
                    List<ResponseItem> covidDataList = API.getCovidData().execute().body();
                    System.out.println(covidDataList.get(0).getCountry());
                    mainThread.execute(new Runnable() {
                        @Override
                        public void run() {
                            coListLiveData.setValue(covidDataList);
                        }
                    });
                }catch (IOException e) {
                    Log.e("error", e.getMessage());
                }
            }
        });
    }

    LiveData<List<ResponseItem>> getAllData(){
        if(coListLiveData.getValue() == null || coListLiveData.getValue().isEmpty()){
            getCovidDataFromNetwork();
        }
        return coListLiveData;
    }

}
