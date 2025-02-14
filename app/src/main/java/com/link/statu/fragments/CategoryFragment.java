package com.link.statu.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.link.statu.MainApplication;
import com.link.statu.adapters.CategoryAdapter;
import com.link.statu.data_source.DataService;
import com.link.statu.models.CategoryPOJO;
import com.link.statu.R;

import java.util.ArrayList;

public class CategoryFragment extends Fragment {


    public CategoryFragment() {
        // Required empty public constructor
    }

    private View view;
    private ArrayList<CategoryPOJO> list;
    private CategoryAdapter adapter;
    private DataService dataService;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_reycler, container, false);
        init();
        return view;
    }

    private void init() {
        dataService = MainApplication.getDataService(requireActivity().getApplication());
        RecyclerView wallsRecycler = view.findViewById(R.id.recyclerView);
        wallsRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        list = new ArrayList<>();
        adapter = new CategoryAdapter(getContext(), list);
        wallsRecycler.setAdapter(adapter);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setFragment(String query){
        list.clear();
        list.addAll(dataService.getCategories(query));
        adapter.notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void focus(){
        adapter.notifyDataSetChanged();
    }

}