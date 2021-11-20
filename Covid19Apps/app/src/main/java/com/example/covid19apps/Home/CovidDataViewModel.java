package com.example.covid19apps.Home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CovidDataViewModel extends AndroidViewModel {
//    private CovidDataRepository covidDataRepository;
    private CovidDataDatabaseRepository covidDataDatabaseRepository;
//    private final LiveData<List<ResponseItem>> covidListLiveData;
    private final LiveData<List<CovidData>> covidListLiveData;
    public CovidDataViewModel(@NonNull Application application) {
        super(application);
        covidDataDatabaseRepository = new CovidDataDatabaseRepository(application);
        covidListLiveData = covidDataDatabaseRepository.getAllData();
//        covidDataRepository = new CovidDataRepository(application);
//        covidListLiveData = covidDataRepository.getAllData();
    }

//    public LiveData<List<ResponseItem>> getAllData(){
    public LiveData<List<CovidData>> getAllData(){
        return covidListLiveData;
    }

}
