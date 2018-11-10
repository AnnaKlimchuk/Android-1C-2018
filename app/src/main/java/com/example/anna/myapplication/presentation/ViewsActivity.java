package com.example.anna.myapplication.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

import com.example.anna.myapplication.R;

public class ViewsActivity extends AppCompatActivity {

    private Button button, backButton;
    private EditText editText;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.views);

        editText = findViewById(R.id.edited_text);
        name = getString(R.string.person_name);

        button = findViewById(R.id.next_move_button);
        button.setOnClickListener(view -> {
            Intent startActivity = new Intent(ViewsActivity.this, ImageButtonActivity.class);
            startActivity.putExtra(name, editText.getText().toString());
            startActivity(startActivity);
        });

        backButton = findViewById(R.id.to_first_activity_button);
        backButton.setOnClickListener(view -> {
            Intent startActivity = new Intent(this, MainActivity.class);
            startActivity(startActivity);
        });
    }
}
