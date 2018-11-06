package com.example.anna.myapplication.presentation;

import android.os.AsyncTask;

import com.example.anna.myapplication.domain.Person;
import com.example.anna.myapplication.presentation.LoadMyDataAsyncTask;
import com.example.anna.myapplication.presentation.MyApplication;
import com.example.anna.myapplication.presentation.ParseDataFromInternetAsyncTask;

import java.util.List;

public class SetDataIfNeededAsyncTask extends AsyncTask<Boolean, Void, List<Person>> {

    @Override
    protected List<Person> doInBackground(Boolean ... booleans) {
        List<Person> personList = MyApplication.getPersonDao().loadAll();
        boolean useMyStorage = booleans[0];

        if (personList.size() == 0) {
            if (useMyStorage) {
                new LoadMyDataAsyncTask().execute();
            } else {
                new ParseDataFromInternetAsyncTask().execute();
            }
        }
        return personList;
    }

    @Override
    protected void onPostExecute(List<Person> personList) {
        super.onPostExecute(personList);
    }
}
