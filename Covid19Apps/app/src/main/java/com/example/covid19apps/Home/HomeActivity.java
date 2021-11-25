package com.example.covid19apps.Home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.covid19apps.Login.LoginActivity;
import com.example.covid19apps.R;
import com.example.covid19apps.Session.SessionActivity;
import com.example.covid19apps.Session.SessionManagerUtil;

public class HomeActivity extends SessionActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.secondActivityContainer, HomeRecyclerViewFragment.newInstance()).commitNow();
        }

    }

}