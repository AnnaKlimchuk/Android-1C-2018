package com.example.anna.myapplication.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.anna.myapplication.R;

public class CatAsyncTaskActivity extends AppCompatActivity {

    private TextView infoTextView;
    private Button nextButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cat_async_task);

        infoTextView = findViewById(R.id.textViewInfo);

        nextButton = findViewById(R.id.nextScreenButton);
        nextButton.setOnClickListener(view -> {
            Intent startActivity = new Intent(this, FrescoImageActivity.class);
            startActivity(startActivity);
        });

        backButton = findViewById(R.id.to_first_activity_button);
        backButton.setOnClickListener(view -> {
            Intent startActivity = new Intent(this, MainActivity.class);
            startActivity(startActivity);
        });
    }

    public void onClick(View view) {
        CatAsyncTask catAsyncTask = new CatAsyncTask(infoTextView);
        catAsyncTask.execute();
    }
}
