package com.example.covid19apps.Home.RecyclerView;

import android.view.View;

import com.example.covid19apps.Home.ResponseItem;

public interface ItemClickableCallback {
    void onClick(View view, ResponseItem covidData);
}
