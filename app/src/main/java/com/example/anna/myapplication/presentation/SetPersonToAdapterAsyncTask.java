package com.example.anna.myapplication.presentation;

import android.os.AsyncTask;

import com.example.anna.myapplication.presentation.DataAdapter;
import com.example.anna.myapplication.presentation.MyApplication;

public class SetPersonToAdapterAsyncTask extends AsyncTask<DataAdapter, Void, Void> {

    @Override
    protected Void doInBackground(DataAdapter ... adapters){
        DataAdapter adapter = adapters[0];
        // ROOM
        adapter.setPersons(MyApplication.getPersonDao().loadAll());
        // SQLite
        // adapter.setPersons(MyApplication.personRepository.loadAll());
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
    }
}
