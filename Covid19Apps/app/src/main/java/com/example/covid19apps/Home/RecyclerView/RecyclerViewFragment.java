package com.example.covid19apps.Home.RecyclerView;

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

import com.example.covid19apps.Home.API.ResponseItem;
import com.example.covid19apps.R;
import com.google.gson.Gson;

public class RecyclerViewFragment extends Fragment {

    private CovidDataViewModel covidDataViewModel;
    private RecyclerView recyclerView;
    private CovidDataAdapter covidDataAdapter;
    private ProgressBar pb;

    private final ItemClickableCallback itemClickableCallback = new ItemClickableCallback() {
        @Override
        public void onClick(View view, ResponseItem responseItem) {
            Gson gson = new Gson();
            String userString = gson.toJson(responseItem);
            Toast.makeText(requireActivity(), userString, Toast.LENGTH_SHORT).show();
        }
    };

    public static RecyclerViewFragment newInstance() {
        return new RecyclerViewFragment();
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
        covidDataAdapter = new CovidDataAdapter(itemClickableCallback);
        recyclerView.setAdapter(covidDataAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        covidDataViewModel.getAllData().observe(getViewLifecycleOwner(), covidListLiveData -> {
            pb.setVisibility(View.INVISIBLE);
            if (covidListLiveData != null) {
                covidDataAdapter.submitList(covidListLiveData);
            }
        });
    }


}
