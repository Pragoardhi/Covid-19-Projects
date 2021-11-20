package com.example.covid19apps.HomeDetail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.covid19apps.Database.CovidData;
import com.example.covid19apps.Home.HomeActivity;
import com.example.covid19apps.R;
import com.example.covid19apps.Session.SessionActivity;

public class HomeDetailActivity extends SessionActivity {

    private CovidData countryCovidData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_detail);

        Intent intent = getIntent();
        countryCovidData = (CovidData) intent.getSerializableExtra("selectedCountry");

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.homeDetailContainer, HomeDetailFragment.newInstance(countryCovidData)).commitNow();
        }
    }
}