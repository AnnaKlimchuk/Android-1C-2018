package com.example.anna.myapplication.presentation;

import android.os.AsyncTask;

import com.example.anna.myapplication.R;
import com.example.anna.myapplication.domain.Person;

import java.util.concurrent.TimeUnit;

public class LoadMyDataAsyncTask extends AsyncTask<Void, Void, Void> {

    private final static String[] NAMES = {
            "Judy Hopps, rabbit", "Nick Wilde, red fox",
            "Dawn Bellwether, sheep", "Benjamin Clawhauser, cheetah",
            "Gazelle, Thomson's gazelle", "Flash, three-toed sloth"};

    private final static Integer[] IMAGES_RES = {
            R.drawable.zootopia_rabbit, R.drawable.zootopia_fox,
            R.drawable.zootopia_sheep, R.drawable.zootopia_cheetah,
            R.drawable.zootopia_gazelle, R.drawable.zootopia_sloth};

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        createPersons();
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        // TODO super.onPostExecute(result);
    }

    private void createPersons() {

        for (int i = 0; i < NAMES.length; ++i) {
            Person person = new Person();
            person.setName(NAMES[i]);
            person.setImageRes(IMAGES_RES[i]);

            // SQLite
            MyApplication.getRepository().create(person);
            // ROOM
            person.setId(i + 1);
            MyApplication.getPersonDao().insert(person);
        }
    }
}
