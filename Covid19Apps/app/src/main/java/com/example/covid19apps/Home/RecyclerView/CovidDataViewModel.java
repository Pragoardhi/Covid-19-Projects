package com.example.covid19apps.Home.RecyclerView;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.covid19apps.Home.ResponseItem;

import java.util.List;

public class CovidDataViewModel extends AndroidViewModel {
    private CovidDataRepository covidDataRepository;
    private final LiveData<List<ResponseItem>> covidListLiveData;

    public CovidDataViewModel(@NonNull Application application) {
        super(application);
        covidDataRepository = new CovidDataRepository(application);
        covidListLiveData = covidDataRepository.getAllData();
    }

    public LiveData<List<ResponseItem>> getAllData(){
        return covidListLiveData;
    }

}
