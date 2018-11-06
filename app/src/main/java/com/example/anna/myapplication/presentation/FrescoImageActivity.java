package com.example.anna.myapplication.presentation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.anna.myapplication.R;
import com.facebook.drawee.view.SimpleDraweeView;

public class FrescoImageActivity extends AppCompatActivity {

    private final static String IMAGE_URL = "https://pbs.twimg.com/media/C-wgGVUXUAETvjo.jpg";
    private Button backButton, nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fresco_image);

        SimpleDraweeView draweeView = (SimpleDraweeView)findViewById(R.id.image);
        Uri uri = Uri.parse(IMAGE_URL);
        draweeView.setImageURI(uri);

        nextButton = findViewById(R.id.nextScreenButton);
        nextButton.setOnClickListener(view -> {
            Intent startActivity = new Intent(this, SQLiteDBActivity.class);
            startActivity(startActivity);
        });

        backButton = findViewById(R.id.to_first_activity_button);
        backButton.setOnClickListener(view -> {
            Intent startActivity = new Intent(this, MainActivity.class);
            startActivity(startActivity);
        });
    }
}