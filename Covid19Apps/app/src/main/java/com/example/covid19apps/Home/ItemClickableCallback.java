package com.example.covid19apps.Home;

import android.view.View;

import com.example.covid19apps.Database.CovidData;

public interface ItemClickableCallback {
    void onClick(View view, CovidData covidDataAPI);
}
