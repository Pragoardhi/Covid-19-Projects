package com.example.covid19apps.Database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CovidDataViewModel extends AndroidViewModel {
    private CovidDataDatabaseRepository covidDataDatabaseRepository;
//    private BookmarkDataDatabaseRepository bookmarkDataDatabaseRepository;
    private final LiveData<List<CovidData>> covidListLiveData;
//    private final LiveData<List<CovidData>> bookmarkCovidListLiveData;
    public CovidDataViewModel(@NonNull Application application) {
        super(application);
        covidDataDatabaseRepository = new CovidDataDatabaseRepository(application);
//        bookmarkDataDatabaseRepository = new BookmarkDataDatabaseRepository(application);
        covidListLiveData = covidDataDatabaseRepository.getAllData();
//        bookmarkCovidListLiveData = covidDataDatabaseRepository.getAllBookmarkData();
//        bookmarcovidListLiveData = bookmarkDataDatabaseRepository.getAllBookmarkData();

    }

    public LiveData<List<CovidData>> getAllData(){
        return covidListLiveData;
    }

    public LiveData<List<CovidData>> getCovidDataByCountry(String title){
        return covidDataDatabaseRepository.getCovidDataByCountry(title);
    }

    public LiveData<List<BookmarkData>> getAllBookmarkData() {
        return covidDataDatabaseRepository.getAllBookmarkData();
    }

    public LiveData<List<BookmarkData>> getBookmarkDataByCountry(String title){
        return covidDataDatabaseRepository.getBookmarkDataByCountry(title);
    }

    public void insert(BookmarkData selectedCountryCovidData) {
        covidDataDatabaseRepository.insertBookmark(selectedCountryCovidData);
    }

    public void deleteBookmark(int id) {
        covidDataDatabaseRepository.deleteBookmark(id);
    }
}
