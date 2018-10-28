package com.example.anna.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.LongSparseArray;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.concurrent.TimeUnit;

public class PersonListFragment extends Fragment {

    private static DataAdapter adapter;
    private LoadDataAsyncTask task;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.person_list_fragment, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.persons_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new DataAdapter(personId ->
                ((PersonListActivity) getActivity()).openFragment2(personId));
        recyclerView.setAdapter(adapter);
        loadPersons();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        task.cancel(true);
    }

    private void loadPersons() {
        task = new LoadDataAsyncTask();
        task.execute();
    }

    static class LoadDataAsyncTask extends AsyncTask<Void, Void, LongSparseArray<Person>> {
        @Override
        protected LongSparseArray<Person> doInBackground(Void... voids) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return PersonStorage.getPersonList();
        }

        @Override
        protected void onPostExecute(LongSparseArray<Person> personLongSparseArray) {
            adapter.setPersons(personLongSparseArray);
        }
    }
}