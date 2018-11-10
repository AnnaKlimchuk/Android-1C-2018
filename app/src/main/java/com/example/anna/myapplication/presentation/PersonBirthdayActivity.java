package com.example.anna.myapplication.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.anna.myapplication.R;

import java.util.Calendar;

public class PersonBirthdayActivity extends AppCompatActivity {

    public static final String BIRTHDAY = "birthday";
    private Button button;
    private DatePicker datePicker;
    private Calendar calendar;
    private String birthday = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_birthday);

        if (savedInstanceState != null) {
            int[] data = savedInstanceState.getIntArray(BIRTHDAY);
            birthday = data[2] + "." + (data[1] + 1) + "." + data[0];
        }

        calendar = Calendar.getInstance();
        Intent startActivity = new Intent(PersonBirthdayActivity.this, PersonListActivity.class);

        datePicker = findViewById(R.id.set_birthday_picker);
        datePicker.init(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                (DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) -> {
                    startActivity.putExtra(getString(R.string.person_birthday),
                                    dayOfMonth + "." + (monthOfYear + 1) + "." + year);
                });

        button = findViewById(R.id.set_birthday_button);
        button.setOnClickListener(view -> {
            if (!birthday.equals("")) {
                startActivity.putExtra(getString(R.string.person_birthday), birthday);
            }
            setResult(RESULT_OK, startActivity);
            finish();
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Integer year = datePicker.getYear();
        Integer month = datePicker.getMonth();
        Integer day = datePicker.getDayOfMonth();
        int[ ] data = { year, month, day };
        outState.putIntArray(BIRTHDAY, data);
    }
}
