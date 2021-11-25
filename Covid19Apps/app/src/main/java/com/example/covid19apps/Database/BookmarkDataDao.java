package com.example.covid19apps.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookmarkDataDao {
    @Query("SELECT * FROM bookmark_data ORDER BY COUNTRY")
    LiveData<List<BookmarkData>> getAllBookmarkData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(BookmarkData bookmarkData);

    @Delete
    void delete(CovidData covidData);

    @Query("SELECT * from bookmark_data where COUNTRY LIKE '%' || :country || '%'")
    LiveData<List<BookmarkData>> getBookmarkDataByCountry(String country);
}
