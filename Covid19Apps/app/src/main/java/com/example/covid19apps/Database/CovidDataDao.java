package com.example.covid19apps.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CovidDataDao {
    @Query("SELECT * FROM covid_data ORDER BY COUNTRY")
    LiveData<List<CovidData>> getAllData();

    @Query("DELETE FROM covid_data")
    public void deleteTable();

    @Query("SELECT * FROM covid_data WHERE ID = :id")
    LiveData<List<CovidData>> getData(int id);

    @Query("SELECT * from covid_data where COUNTRY LIKE '%' || :country || '%'")
    LiveData<List<CovidData>> getCovidDataByCountry(String country);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CovidData covidData);

    @Delete
    void delete(CovidData covidData);


}
