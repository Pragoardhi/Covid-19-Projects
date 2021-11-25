package com.example.covid19apps.Bookmark;

import android.view.View;

import com.example.covid19apps.Database.CovidData;

public interface BookmarkItemClickableCallback {
    void onClick(View view, CovidData covidDataAPI);
}
