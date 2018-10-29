package com.example.anna.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CatAsyncTaskActivity extends AppCompatActivity {

    private TextView infoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cat_async_task);

        infoTextView = findViewById(R.id.textViewInfo);
    }

    public void onClick(View view) {
        CatAsyncTask catAsyncTask = new CatAsyncTask(infoTextView);
        catAsyncTask.execute();
    }
}
