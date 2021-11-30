package com.example.covid19apps.HomeDetail;

import android.app.NativeActivity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.covid19apps.Database.BookmarkData;
import com.example.covid19apps.Database.CovidData;
import com.example.covid19apps.Database.CovidDataViewModel;
import com.example.covid19apps.Home.HomeActivity;
import com.example.covid19apps.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeDetailFragment extends Fragment {

    private static CovidData selectedCountryCovidData;
    private CovidDataViewModel covidDataViewModel;


    TextView updated;
    TextView country;
    TextView cases;
    TextView todayCases;
    TextView active;
    TextView critical;
    TextView recovered;
    TextView todayRecovered;
    TextView death;
    TextView todayDeath;

    Integer countryId;
    FloatingActionButton addBookmark;
    public static HomeDetailFragment newInstance(CovidData countryCovidData){
        selectedCountryCovidData = countryCovidData;
        return new HomeDetailFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        covidDataViewModel = new ViewModelProvider(requireActivity()).get(CovidDataViewModel.class);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.country_detail, container, false);
        updated = view.findViewById(R.id.updated);
        Date date = new Date(selectedCountryCovidData.updated);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        updated.setText(strDate);
        country = view.findViewById(R.id.country);
        country.setText(selectedCountryCovidData.country.toUpperCase());
        cases = view.findViewById(R.id.cases);
        cases.setText(selectedCountryCovidData.cases+"");
        todayCases = view.findViewById(R.id.todayCases);
        todayCases.setText("+"+selectedCountryCovidData.todayCases+"");
        active = view.findViewById(R.id.active);
        active.setText(selectedCountryCovidData.active+"");
        critical = view.findViewById(R.id.critical);
        critical.setText("+"+selectedCountryCovidData.critical+"");
        recovered = view.findViewById(R.id.recovered);
        recovered.setText(selectedCountryCovidData.recovered+"");
        todayRecovered = view.findViewById(R.id.todayRecovered);
        todayRecovered.setText("+"+selectedCountryCovidData.todayRecovered+"");
        death = view.findViewById(R.id.death);
        death.setText(selectedCountryCovidData.death+"");
        todayDeath = view.findViewById(R.id.todayDeath);
        todayDeath.setText("+"+selectedCountryCovidData.todayDeath+"");

        countryId = selectedCountryCovidData.id;

        addBookmark = view.findViewById(R.id.addBookmark);
        covidDataViewModel.getBookmarkDataByCountry(selectedCountryCovidData.country).observe(getViewLifecycleOwner(), bookmarkData -> {
            if (bookmarkData == null || bookmarkData.isEmpty()){
                addBookmark.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(),"Menambahkan ke bookmark",Toast.LENGTH_SHORT).show();
                        addBookmark.setImageResource(R.drawable.ic_baseline_check_24);
                        addBookmark.setEnabled(false);
                        addBookmark.setBackgroundTintList(ColorStateList.valueOf(-11751600));
                        BookmarkData bookmarkData = new BookmarkData();
                        bookmarkData.id = selectedCountryCovidData.id;
                        bookmarkData.updated = selectedCountryCovidData.updated;
                        bookmarkData.continent = selectedCountryCovidData.continent;
                        bookmarkData.country = selectedCountryCovidData.country;
                        bookmarkData.countryFlag = selectedCountryCovidData.countryFlag;
                        bookmarkData.cases = selectedCountryCovidData.cases;
                        bookmarkData.todayCases = selectedCountryCovidData.todayCases;
                        bookmarkData.death = selectedCountryCovidData.death;
                        bookmarkData.todayDeath = selectedCountryCovidData.todayDeath;
                        bookmarkData.recovered = selectedCountryCovidData.recovered;
                        bookmarkData.todayRecovered = selectedCountryCovidData.todayRecovered;
                        bookmarkData.active = selectedCountryCovidData.active;
                        bookmarkData.critical = selectedCountryCovidData.critical;
                        covidDataViewModel.insert(bookmarkData);
                    }
                });
            }else{
                addBookmark.setImageResource(R.drawable.ic_baseline_check_24);
            }

        });

        return view;
    }


}
