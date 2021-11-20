package com.example.covid19apps.Home.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.covid19apps.Home.CovidData;

import java.util.List;

@Dao
public interface CovidDataDao {
    @Query("select * from coviddata")
    LiveData<List<CovidData>> getAllData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CovidData covidData);

    @Delete
    void delete(CovidData covidData);
}
