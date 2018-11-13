package com.example.anna.myapplication.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.anna.myapplication.R;

import java.io.FileInputStream;

public class IntentServiceActivity extends AppCompatActivity {

    private TextView mInfoTextView;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_service);

        mInfoTextView = (TextView) findViewById(R.id.textView);

        mButton = (Button) findViewById(R.id.loadDatabaseButton);
        mButton.setOnClickListener(view -> {
            StringBuilder stringBuilder = new StringBuilder();
            try{
                // todo catch
                FileInputStream fstream = openFileInput(MyApplication.getFileName());
                int i;
                while ((i = fstream.read())!= -1){
                    stringBuilder.append((char)i);
                }
                fstream.close();
            }catch (Exception e){}
            mInfoTextView.setText(stringBuilder.toString());
        });
    }
}
