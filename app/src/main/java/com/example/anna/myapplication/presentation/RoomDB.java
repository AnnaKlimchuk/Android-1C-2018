package com.example.anna.myapplication.presentation;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.anna.myapplication.R;
import com.example.anna.myapplication.domain.Person;

import java.util.List;

public class RoomDB extends AppCompatActivity {

    private static TextView textView;
    private Button createButton, loadButton, nextButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database);

        textView = findViewById(R.id.textView);
        textView.setText("ROOM DB");

        createButton = findViewById(R.id.createButton);
        createButton.setOnClickListener(view -> {
            new CreatePerson().execute();
        });

        loadButton = findViewById(R.id.loadButton);
        loadButton.setOnClickListener(view -> {
            new LoadPersons().execute();
        });

        nextButton = findViewById(R.id.nextScreenButton);
        nextButton.setOnClickListener(view -> {
            Intent startActivity = new Intent(this, IntentServiceActivity.class);
            startActivity(startActivity);
        });

        backButton = findViewById(R.id.to_first_activity_button);
        backButton.setOnClickListener(view -> {
            Intent startActivity = new Intent(this, MainActivity.class);
            startActivity(startActivity);
        });
    }

    private static class CreatePerson extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(final Void... voids) {
            Integer size = MyApplication.getPersonDao().loadAll().size();
            size++;

            Person person = new Person();
            person.setName("Nick Wilde, red fox");
            person.setImageRes(R.drawable.zootopia_fox);
            person.setId(size);

            MyApplication.getPersonDao().insert(person);
            return person.toString() + " was created.\nNow there are " + size + " persons";
        }

        @Override
        protected void onPostExecute(final String s) {
            super.onPostExecute(s);
            textView.setText(s);
        }
    }

    private static class LoadPersons extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(final Void... voids) {
            List<Person> person_list = MyApplication.getPersonDao().loadAll();
            return "There are " + person_list.size() + " persons:\n" + person_list.toString();
        }

        @Override
        protected void onPostExecute(final String s) {
            super.onPostExecute(s);
            textView.setText(s);
        }
    }

}
