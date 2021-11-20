package com.example.covid19apps.Home;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.covid19apps.Home.API.CovidDataAPI;
//import com.example.covid19apps.Home.Database.CovidDataDao;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CovidDataRepository {
    private MyAPIEndPointInterFace API;
    private MutableLiveData<List<CovidDataAPI>> coListLiveData = new MutableLiveData<>();

    private List<CovidDataAPI> listFromApi;

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
                    List<CovidDataAPI> covidDataAPIList = API.getCovidData().execute().body();
                    mainThread.execute(new Runnable() {
                        @Override
                        public void run() {
                            coListLiveData.setValue(covidDataAPIList);
                        }
                    });
                }catch (IOException e) {
                    Log.e("error", e.getMessage());
                }
            }
        });
    }

    LiveData<List<CovidDataAPI>> getAllData(){
        if(coListLiveData.getValue() == null || coListLiveData.getValue().isEmpty()){
            getCovidDataFromNetwork();
        }
        return coListLiveData;
    }

}
