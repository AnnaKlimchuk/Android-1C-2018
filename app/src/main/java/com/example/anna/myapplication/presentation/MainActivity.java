package com.example.anna.myapplication.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.net.Uri;

import com.example.anna.myapplication.R;

public class MainActivity extends AppCompatActivity {

    private Button buttonEmail, buttonProfile, buttonAdditional, buttonTraining;
    private static final String EMAIL = "anna_klm@mail.ru";
    private static final String EMAIL_SUBJECT = "Hello";
    private static final String EMAIL_TEXT = "Welcome letter from %s";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonEmail = findViewById(R.id.send_email);
        buttonEmail.setOnClickListener(view -> {
            Intent sendEmail = new Intent(Intent.ACTION_SENDTO);

            sendEmail.setData(Uri.parse("mailto:")); // only email apps should handle this
            sendEmail.putExtra(Intent.EXTRA_EMAIL, new String[] {EMAIL});
            sendEmail.putExtra(Intent.EXTRA_SUBJECT, EMAIL_SUBJECT);
            sendEmail.putExtra(Intent.EXTRA_TEXT, String.format(EMAIL_TEXT,
                                                                getString(R.string.app_name)));
            if (sendEmail.resolveActivity(getPackageManager()) != null) {
                startActivity(sendEmail);
            }
        });

        buttonProfile = findViewById(R.id.open_profile);
        buttonProfile.setOnClickListener(view -> {
            Intent startActivity = new Intent(MainActivity.this, PersonListActivity.class);
            startActivity(startActivity);
        });

        buttonAdditional = findViewById(R.id.additional_thread);
        buttonAdditional.setOnClickListener(view -> {
            Intent startActivity = new Intent(MainActivity.this, ViewsActivity.class);
            startActivity(startActivity);
        });

        buttonTraining = findViewById(R.id.training_thread);
        buttonTraining.setOnClickListener(view -> {
            Intent startActivity = new Intent(MainActivity.this, TouchActivity.class);
            startActivity(startActivity);
        });
    }
}
