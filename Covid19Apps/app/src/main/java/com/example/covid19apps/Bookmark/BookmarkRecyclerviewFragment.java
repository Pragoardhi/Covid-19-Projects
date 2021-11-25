package com.example.covid19apps.Bookmark;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid19apps.Database.BookmarkData;
import com.example.covid19apps.Database.CovidData;
import com.example.covid19apps.Database.CovidDataViewModel;
import com.example.covid19apps.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class BookmarkRecyclerviewFragment extends Fragment {

    private CovidDataViewModel covidDataViewModel;
    private RecyclerView recyclerView;
    private BookmarkRecyclerViewAdapter bookmarkRecyclerViewAdapter;
    private ProgressBar pb;
    private List<CovidData> covidDataList= new ArrayList<>();
    private final BookmarkItemClickableCallback bookmarkItemClickableCallback = (view, covidDataAPI) -> {
        Gson gson = new Gson();
        String userString = gson.toJson(covidDataAPI);
        Toast.makeText(requireActivity(), userString, Toast.LENGTH_SHORT).show();
    };

    public static BookmarkRecyclerviewFragment newInstance() {
        return new BookmarkRecyclerviewFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        covidDataViewModel = new ViewModelProvider(requireActivity()).get(CovidDataViewModel.class);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bookmark_recyclerview_container, container, false);
        pb = view.findViewById(R.id.rv_pb_bookmark);
        recyclerView = view.findViewById(R.id.roomRecyclerViewBookMark);
        bookmarkRecyclerViewAdapter = new BookmarkRecyclerViewAdapter(bookmarkItemClickableCallback);
        recyclerView.setAdapter(bookmarkRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pb.setVisibility(View.VISIBLE);
        resetData();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.navbar,menu);
        super.onCreateOptionsMenu(menu, inflater);
        SearchManager sm = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView sv = (SearchView) menu.findItem(R.id.search).getActionView();
        sv.setSearchableInfo(sm.getSearchableInfo(getActivity().getComponentName()));
        sv.setIconifiedByDefault(true);
        sv.setMaxWidth(Integer.MAX_VALUE);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return true;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)){
                    resetData();
                    return true;
                }else {
                    getResults(s);
                }
                return false;
            }
        });
    }

    private void getResults(String s) {
        String queryText = "%" + s + "%";
        covidDataViewModel.getBookmarkDataByCountry(queryText).observe(getViewLifecycleOwner(), bookmarkData -> {
            if (bookmarkData == null) return;
            bookmarkRecyclerViewAdapter.submitList(bookmarkData);
            bookmarkRecyclerViewAdapter.notifyDataSetChanged();
        });
    }

    private void resetData() {

        covidDataViewModel.getAllBookmarkData().observe(getViewLifecycleOwner(), covidListLiveData -> {
            if (covidListLiveData != null) {
                bookmarkRecyclerViewAdapter.submitList(covidListLiveData);
                bookmarkRecyclerViewAdapter.notifyDataSetChanged();
                if(!bookmarkRecyclerViewAdapter.getCurrentList().isEmpty()){
                    pb.setVisibility(View.INVISIBLE);
                }
                else{
                    pb.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

}
