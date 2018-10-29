package com.example.anna.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

public class ViewsActivity extends AppCompatActivity {

    private EditText editText;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.views);

        Button button = findViewById(R.id.next_move_button);
        Button backButton = findViewById(R.id.to_first_activity_button);
        editText = findViewById(R.id.edited_text);
        name = getString(R.string.person_name);

        button.setOnClickListener(view -> {
            Intent startActivity = new Intent(ViewsActivity.this, ImageButtonActivity.class);
            startActivity.putExtra(name, editText.getText().toString());
            startActivity(startActivity);
        });

        backButton.setOnClickListener(view -> {
            Intent startActivity = new Intent(this, MainActivity.class);
            startActivity(startActivity);
        });
    }

}
