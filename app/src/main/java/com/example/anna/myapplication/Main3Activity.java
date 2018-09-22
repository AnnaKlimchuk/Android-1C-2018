package com.example.anna.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

public class Main3Activity extends AppCompatActivity {

    Button button, backButton;
    EditText editText;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        button = findViewById(R.id.next_move_button);
        backButton = findViewById(R.id.to_first_activity_button);
        editText = findViewById(R.id.edited_text);
        name = getString(R.string.person_name);

        button.setOnClickListener(view -> {
            Intent startActivity = new Intent(Main3Activity.this, Main2Activity.class);
            startActivity.putExtra(name, editText.getText().toString());
            startActivity(startActivity);
        });

        backButton.setOnClickListener(view -> {
            Intent startActivity = new Intent(this, MainActivity.class);
            startActivity(startActivity);
        });
    }

}
