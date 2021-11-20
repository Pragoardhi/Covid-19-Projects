package com.example.covid19apps.Database;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.covid19apps.API.CovidDataAPI;
import com.example.covid19apps.API.MyAPIEndPointInterFace;
import com.example.covid19apps.API.RetrofitInstance;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CovidDataDatabaseRepository {


    private MyAPIEndPointInterFace API;
    private CovidDataDao covidDataDao;
    private LiveData<List<CovidData>> covidListLiveData;

    private final ExecutorService networkExecutor =
            Executors.newFixedThreadPool(4);
    private final Executor mainThread = new Executor() {
        private Handler handler = new Handler(Looper.getMainLooper());
        @Override
        public void execute(Runnable command) {
            handler.post(command);
        }
    };


    public CovidDataDatabaseRepository(Application application){
        CovidDataDatabase database = CovidDataDatabase.getDatabase(application);
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        API = retrofitInstance.getAPI();
        covidDataDao = database.covidDataDao();
        covidListLiveData = covidDataDao.getAllData();
    }

    LiveData<List<CovidData>> getAllData(){
        if(covidListLiveData.getValue() == null){
            getCovidDataFromNetwork();
        }
        return covidListLiveData;
    }

    void insert(CovidData covidData){
        CovidDataDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                covidDataDao.insert(covidData);
            }
        });
    }

    void delete(CovidData covidData){
        CovidDataDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                covidDataDao.delete(covidData);
            }
        });
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
                            for(int i =0; i<covidDataAPIList.size();i++){
                                CovidData covidData = new CovidData();
                                covidData.id = covidDataAPIList.get(i).getCountryInfo().getId();
                                covidData.updated = covidDataAPIList.get(i).getUpdated();
                                covidData.country = covidDataAPIList.get(i).getCountry();
                                covidData.countryFlag = covidDataAPIList.get(i).getCountryInfo().getFlag();
                                covidData.continent = covidDataAPIList.get(i).getContinent();
                                covidData.cases = covidDataAPIList.get(i).getCases();
                                covidData.todayCases = covidDataAPIList.get(i).getTodayCases();
                                covidData.death = covidDataAPIList.get(i).getDeaths();
                                covidData.todayDeath = covidDataAPIList.get(i).getTodayDeaths();
                                covidData.recovered = covidDataAPIList.get(i).getRecovered();
                                covidData.todayRecovered = covidDataAPIList.get(i).getTodayRecovered();
                                insert(covidData);
                            }
                        }
                    });
                }catch (IOException e) {
                    Log.e("error", e.getMessage());
                }
            }
        });
    }

}
