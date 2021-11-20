package com.example.covid19apps.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid19apps.Database.CovidDataViewModel;
import com.example.covid19apps.R;
import com.google.gson.Gson;

public class HomeRecyclerViewFragment extends Fragment {

    private CovidDataViewModel covidDataViewModel;
    private RecyclerView recyclerView;
    private HomeRecyclerViewAdapter homeRecyclerViewAdapter;
    private ProgressBar pb;

    private final ItemClickableCallback itemClickableCallback = (view, covidDataAPI) -> {
        Gson gson = new Gson();
        String userString = gson.toJson(covidDataAPI);
        Toast.makeText(requireActivity(), userString, Toast.LENGTH_SHORT).show();
    };

    public static HomeRecyclerViewFragment newInstance() {
        return new HomeRecyclerViewFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        covidDataViewModel = new ViewModelProvider(requireActivity()).get(CovidDataViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview_container, container, false);
        pb = view.findViewById(R.id.rv_pb);
        recyclerView = view.findViewById(R.id.roomRecyclerView);
        homeRecyclerViewAdapter = new HomeRecyclerViewAdapter(itemClickableCallback);
        recyclerView.setAdapter(homeRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        covidDataViewModel.getAllData().observe(getViewLifecycleOwner(), covidListLiveData -> {
            if (covidListLiveData != null) {
                homeRecyclerViewAdapter.submitList(covidListLiveData);
                if(!homeRecyclerViewAdapter.getCurrentList().isEmpty()){
                    pb.setVisibility(View.INVISIBLE);
                }
            }
        });
    }


}
