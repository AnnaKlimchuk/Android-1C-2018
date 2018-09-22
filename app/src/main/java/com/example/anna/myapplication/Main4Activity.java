package com.example.anna.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Main4Activity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    private static final String DESCRIPTION = "description";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        textView = findViewById(R.id.person_birthday);
        textView.setOnClickListener(view -> {
            Intent startActivity = new Intent(Main4Activity.this, Main5Activity.class);
            startActivityForResult(startActivity, 1);
        });

        if (savedInstanceState != null) {
            editText = findViewById(R.id.person_description);
            editText.setText(savedInstanceState.getString(DESCRIPTION));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        editText = findViewById(R.id.person_description);
        outState.putString(DESCRIPTION, editText.getText().toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        String birthday;

        if (resultCode == RESULT_OK) {
            textView = findViewById(R.id.person_birthday);
            birthday = intent.getStringExtra(getString(R.string.person_birthday));
            textView.setText(birthday);
        }
    }

}