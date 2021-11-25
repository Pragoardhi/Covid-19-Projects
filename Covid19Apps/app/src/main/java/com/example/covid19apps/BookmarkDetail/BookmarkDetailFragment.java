package com.example.covid19apps.BookmarkDetail;

import android.os.Bundle;
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
import com.example.covid19apps.Database.CovidDataViewModel;
import com.example.covid19apps.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookmarkDetailFragment extends Fragment {
    private static BookmarkData selectedCountryCovidData;
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
    public static BookmarkDetailFragment newInstance(BookmarkData countryCovidData){
        selectedCountryCovidData = countryCovidData;
        return new BookmarkDetailFragment();
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
        View view = inflater.inflate(R.layout.bookmark_country_detail, container, false);
        updated = view.findViewById(R.id.updatedBookmark);
        Date date = new Date(selectedCountryCovidData.updated);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        updated.setText(strDate);
        country = view.findViewById(R.id.countryBookmark);
        country.setText(selectedCountryCovidData.country.toUpperCase());
        cases = view.findViewById(R.id.casesBookmark);
        cases.setText(selectedCountryCovidData.cases+"");
        todayCases = view.findViewById(R.id.todayCasesBookmark);
        todayCases.setText("+"+selectedCountryCovidData.todayCases+"");
        active = view.findViewById(R.id.activeBookmark);
        active.setText(selectedCountryCovidData.active+"");
        critical = view.findViewById(R.id.criticalBookmark);
        critical.setText("+"+selectedCountryCovidData.critical+"");
        recovered = view.findViewById(R.id.recoveredBookmark);
        recovered.setText(selectedCountryCovidData.recovered+"");
        todayRecovered = view.findViewById(R.id.todayRecoveredBookmark);
        todayRecovered.setText("+"+selectedCountryCovidData.todayRecovered+"");
        death = view.findViewById(R.id.deathBookmark);
        death.setText(selectedCountryCovidData.death+"");
        todayDeath = view.findViewById(R.id.todayDeathBookmark);
        todayDeath.setText("+"+selectedCountryCovidData.todayDeath+"");

        countryId = selectedCountryCovidData.id;

        addBookmark = view.findViewById(R.id.deleteBookmark);
        addBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),selectedCountryCovidData.country+"telah dihapus dari bookmark",Toast.LENGTH_SHORT).show();
//                BookmarkData bookmarkData = new BookmarkData();
//
//                bookmarkData.id = selectedCountryCovidData.id;
//                bookmarkData.updated = selectedCountryCovidData.updated;
//                bookmarkData.continent = selectedCountryCovidData.continent;
//                bookmarkData.country = selectedCountryCovidData.country;
//                bookmarkData.countryFlag = selectedCountryCovidData.countryFlag;
//                bookmarkData.cases = selectedCountryCovidData.cases;
//                bookmarkData.todayCases = selectedCountryCovidData.todayCases;
//                bookmarkData.death = selectedCountryCovidData.death;
//                bookmarkData.todayDeath = selectedCountryCovidData.todayDeath;
//                bookmarkData.recovered = selectedCountryCovidData.recovered;
//                bookmarkData.todayRecovered = selectedCountryCovidData.todayRecovered;
//                bookmarkData.active = selectedCountryCovidData.active;
//                bookmarkData.critical = selectedCountryCovidData.critical;
//                covidDataViewModel.insert(bookmarkData);
            }
        });
        return view;
    }
}
