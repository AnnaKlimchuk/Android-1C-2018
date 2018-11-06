package com.example.anna.myapplication.presentation;

import android.os.AsyncTask;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class CatAsyncTask extends AsyncTask<Void, Void, Void> {

    private final TextView infoTextView;

    public CatAsyncTask(TextView infoTextView) {
        this.infoTextView = infoTextView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        infoTextView.setText("Полез на крышу");
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        infoTextView.setText("Залез");
    }
}