package com.example.anna.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

public class ImageButtonActivity extends AppCompatActivity {

    ImageButton imageButton;
    String name;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_button);

        imageButton = findViewById(R.id.moving_button);
        imageButton.setOnClickListener(view -> {
            Intent startActivity = new Intent(this, CatAsyncTaskActivity.class);
            startActivity(startActivity);
        });

        name = getIntent().getStringExtra(getString(R.string.person_name));
        toast = Toast.makeText(this, "Now you see me, " + ((name.trim().isEmpty()) ? "somebody" : name), Toast.LENGTH_LONG);
        toast.show();
    }
}