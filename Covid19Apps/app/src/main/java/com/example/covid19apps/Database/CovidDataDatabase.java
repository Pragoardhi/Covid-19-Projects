package com.example.covid19apps.Database;

import android.content.Context;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {CovidData.class,BookmarkData.class}, version = 1, exportSchema = false)
public abstract class CovidDataDatabase extends RoomDatabase {
    public abstract CovidDataDao covidDataDao();
    public abstract BookmarkDataDao bookmarkDataDao();
    private static volatile CovidDataDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static CovidDataDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CovidDataDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CovidDataDatabase.class, "coviddata")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
