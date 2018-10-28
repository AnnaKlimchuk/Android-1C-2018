package com.example.anna.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

public class PersonBirthdayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_birthday);

        Calendar today = Calendar.getInstance();
        Intent startActivity = new Intent(PersonBirthdayActivity.this, PersonListActivity.class);

        DatePicker datePicker = findViewById(R.id.set_birthday_picker);
        datePicker.init(today.get(Calendar.YEAR),
                today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH), (DatePicker view,
                                                   int year,
                                                   int monthOfYear,
                                                   int dayOfMonth) ->
                        startActivity.putExtra(getString(R.string.person_birthday),
                                dayOfMonth + "." + (monthOfYear + 1) + "." + year)
        );

        Button button = findViewById(R.id.set_birthday_button);
        button.setOnClickListener(view -> {
            setResult(RESULT_OK, startActivity);
            finish();
        });
    }
}
