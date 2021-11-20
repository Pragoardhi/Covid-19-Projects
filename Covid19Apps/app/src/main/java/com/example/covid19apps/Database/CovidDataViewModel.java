package com.example.covid19apps.Database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CovidDataViewModel extends AndroidViewModel {
    private CovidDataDatabaseRepository covidDataDatabaseRepository;
    private final LiveData<List<CovidData>> covidListLiveData;

    public CovidDataViewModel(@NonNull Application application) {
        super(application);
        covidDataDatabaseRepository = new CovidDataDatabaseRepository(application);
        covidListLiveData = covidDataDatabaseRepository.getAllData();
    }

    public LiveData<List<CovidData>> getAllData(){
        return covidListLiveData;
    }


}
