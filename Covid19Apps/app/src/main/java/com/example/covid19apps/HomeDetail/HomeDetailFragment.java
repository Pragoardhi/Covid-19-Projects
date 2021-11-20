package com.example.covid19apps.HomeDetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.covid19apps.Database.CovidData;
import com.example.covid19apps.Database.CovidDataDatabaseRepository;
import com.example.covid19apps.Database.CovidDataViewModel;
import com.example.covid19apps.R;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    public static HomeDetailFragment newInstance(CovidData countryCovidData){
        selectedCountryCovidData = countryCovidData;
        return new HomeDetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.country_detail, container, false);
        updated = view.findViewById(R.id.updated);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(selectedCountryCovidData.updated * 1000);
        Date date = cal.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        updated.setText("Last Update: "+strDate);
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
        return view;
    }

}
