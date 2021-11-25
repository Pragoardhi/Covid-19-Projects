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

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.secondActivityContainer, HomeRecyclerViewFragment.newInstance()).commitNow();
//        }

        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(v -> {
            SessionManagerUtil.getInstance().endUserSession(HomeActivity.this);
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

    }

}