package com.example.covid19apps.HomeDetail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.covid19apps.R;

public class HomeDetailActivity extends AppCompatActivity {

    private int countryId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_detail);

        Intent intent = getIntent();
        countryId = (int) intent.getSerializableExtra("selectedCountry");
    }
}