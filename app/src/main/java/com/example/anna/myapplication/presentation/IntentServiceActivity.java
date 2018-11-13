package com.example.anna.myapplication.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.anna.myapplication.R;

import java.io.FileInputStream;

public class IntentServiceActivity extends AppCompatActivity {

    private TextView DatabaseTextView;
    private Button DatabaseLoadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_service);

        DatabaseTextView = (TextView) findViewById(R.id.textView);

        DatabaseLoadButton = (Button) findViewById(R.id.loadDatabaseButton);
        DatabaseLoadButton.setOnClickListener(view -> {
            StringBuilder stringBuilder = new StringBuilder();
            try{
                FileInputStream fstream = openFileInput(MyApplication.getFileName());
                int i;
                while ((i = fstream.read())!= -1){
                    stringBuilder.append((char)i);
                }
                fstream.close();
                DatabaseTextView.setText(stringBuilder.toString());
            }catch (Exception e){
                DatabaseTextView.setText(getResources().getString(R.string.error_load_database));
            }

        });
    }
}
