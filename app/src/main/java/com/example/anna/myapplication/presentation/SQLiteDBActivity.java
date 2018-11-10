package com.example.anna.myapplication.presentation;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anna.myapplication.R;
import com.example.anna.myapplication.domain.Person;

import java.util.List;
import java.util.Random;

public class SQLiteDBActivity extends AppCompatActivity {

    private final static String[] NAMES = {
            "Judy Hopps, rabbit", "Nick Wilde, red fox",
            "Dawn Bellwether, sheep", "Benjamin Clawhauser, cheetah",
            "Gazelle, Thomson's gazelle", "Flash, three-toed sloth"};

    private final static Integer[] IMAGES_RES = {
            R.drawable.zootopia_rabbit, R.drawable.zootopia_fox,
            R.drawable.zootopia_sheep, R.drawable.zootopia_cheetah,
            R.drawable.zootopia_gazelle, R.drawable.zootopia_sloth};

    private static TextView textView;
    private static ImageView imageView;
    private Button createButton, loadButton, nextButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database);

        textView = findViewById(R.id.textView);
        textView.setText("SQLite DB");
        imageView = findViewById(R.id.person_image);

        createButton = findViewById(R.id.createButton);
        createButton.setOnClickListener(view -> {
            new CreatePerson().execute();
        });

        loadButton = findViewById(R.id.loadButton);
        loadButton.setOnClickListener(view -> {
            imageView.setVisibility(View.GONE);
            new LoadPersons().execute();
        });

        nextButton = findViewById(R.id.nextScreenButton);
        nextButton.setOnClickListener(view -> {
            Intent startActivity = new Intent(this, RoomDB.class);
            startActivity(startActivity);
        });

        backButton = findViewById(R.id.to_first_activity_button);
        backButton.setOnClickListener(view -> {
            Intent startActivity = new Intent(this, MainActivity.class);
            startActivity(startActivity);
        });
    }

    private static class CreatePerson extends AsyncTask<Void, Void, Pair<String, Integer>> {

        @Override
        protected Pair<String, Integer> doInBackground(final Void... voids) {
            Random rand = new Random();
            int number = rand.nextInt(NAMES.length);

            Person person = new Person();
            person.setName(NAMES[number]);
            person.setImageRes(IMAGES_RES[number]);

            MyApplication.getRepository().create(person);
            return new Pair<>(
                    person.toString()
                    + " was created.\nNow there are "
                    + MyApplication.getRepository().loadAll().size()
                    + " persons", person.getImageRes());
        }

        @Override
        protected void onPostExecute(final Pair<String, Integer> pair) {
            String s = pair.first;
            Integer personImageRes = pair.second;
            super.onPostExecute(pair);
            textView.setText(s);
            imageView.setImageResource(personImageRes);
            imageView.setVisibility(View.VISIBLE);
        }
    }

    private static class LoadPersons extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(final Void... voids) {
            List<Person> personList =  MyApplication.getRepository().loadAll();
            return "There are " + personList.size() + " persons:\n" + personList.toString();
        }

        @Override
        protected void onPostExecute(final String s) {
            super.onPostExecute(s);
            textView.setText(s);
        }
    }
}
