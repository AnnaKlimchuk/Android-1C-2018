package com.example.anna.myapplication;

import android.os.AsyncTask;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

class CatAsyncTask extends AsyncTask<Void, Void, Void> {

    private final TextView infoTextView;

    CatAsyncTask(TextView infoTextView) {
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