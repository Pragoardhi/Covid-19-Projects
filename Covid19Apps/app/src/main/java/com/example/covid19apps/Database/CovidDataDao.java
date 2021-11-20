package com.example.covid19apps.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CovidDataDao {
    @Query("SELECT * FROM coviddata ORDER BY COUNTRY")
    LiveData<List<CovidData>> getAllData();

    @Query("DELETE FROM coviddata")
    public void deleteTable();

    @Query("SELECT * FROM coviddata WHERE ID = :id")
    LiveData<List<CovidData>> getData(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CovidData covidData);

    @Delete
    void delete(CovidData covidData);
}
