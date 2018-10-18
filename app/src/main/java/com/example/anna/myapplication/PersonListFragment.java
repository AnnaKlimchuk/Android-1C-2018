package com.example.anna.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PersonListFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.person_list, container, false);
        if (savedInstanceState == null) {
            PersonStorage.setInitialData();
        }

        recyclerView = view.findViewById(R.id.persons_list);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new DataAdapter(getActivity(), PersonStorage.getPersonList(), personId -> {
            ((Main7Activity)getActivity()).openFragment2(personId);
        });
        recyclerView.setAdapter(adapter);

        return view;
    }
}