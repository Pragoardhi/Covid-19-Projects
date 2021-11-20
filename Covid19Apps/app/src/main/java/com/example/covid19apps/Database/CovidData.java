package com.example.covid19apps.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
public class CovidData implements Serializable {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "ID")
    public int id;

    @ColumnInfo(name = "UPDATED")
    public long updated;

    @ColumnInfo(name = "COUNTRY")
    public String country;

    @ColumnInfo(name = "CONTINENT")
    public String continent;

    @ColumnInfo(name = "COUNTRY_FLAG")
    public String countryFlag;

    @ColumnInfo(name = "CASES")
    public int cases;

    @ColumnInfo(name = "TODAY_CASES")
    public int todayCases;

    @ColumnInfo(name = "DEATH")
    public int death;

    @ColumnInfo(name = "TODAY_DEATH")
    public int todayDeath;

    @ColumnInfo(name = "RECOVERED")
    public int recovered;

    @ColumnInfo(name = "TODAY_RECOVERED")
    public int todayRecovered;

}
