package com.example.covid19apps.BookmarkDetail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.covid19apps.Database.BookmarkData;
import com.example.covid19apps.Database.CovidData;
import com.example.covid19apps.HomeDetail.HomeDetailFragment;
import com.example.covid19apps.R;

public class BookmarkDetailActivity extends AppCompatActivity {

    private BookmarkData bookmarkCountryData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark_detail2);
        Intent intent = getIntent();
        bookmarkCountryData = (BookmarkData) intent.getSerializableExtra("selectedBookmarkCountry");

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.bookmarkDetailContainer, BookmarkDetailFragment.newInstance(bookmarkCountryData)).commitNow();
        }
    }
}