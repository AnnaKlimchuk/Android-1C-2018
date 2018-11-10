package com.example.anna.myapplication.presentation;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.anna.myapplication.R;

public class PersonListActivity extends AppCompatActivity {

    public static final String ARG_PERSON_ID = "personId";
    private FragmentManager fragmentManager;
    private Fragment fragment;
    private Fragment newFragment;
    private long personId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_list);

        if (savedInstanceState != null) {
            personId = savedInstanceState.getLong(ARG_PERSON_ID);
        } else {
            personId = -1;
        }

        fragmentManager = getSupportFragmentManager();
        fragment = fragmentManager.findFragmentById(R.id.fragmentContainer1);
        newFragment = new PersonListFragment();

        if (fragment == null) {
            fragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .add(R.id.fragmentContainer1, newFragment)
                    .commit();
        }

        if (personId != -1) {
            this.openFragment2(personId);
        } else {
            findViewById(R.id.fragmentContainer2).setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        personId = -1;

        if (findViewById(R.id.fragmentContainer2).getVisibility() == View.VISIBLE) {
            findViewById(R.id.fragmentContainer2).setVisibility(View.GONE);

            fragment = fragmentManager.findFragmentById(R.id.fragmentContainer1);
            newFragment = new PersonListFragment();

            if (fragment == null) {
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .add(R.id.fragmentContainer1, newFragment)
                        .commit();
            }

            findViewById(R.id.fragmentContainer1).setVisibility(View.VISIBLE);
        } else {
            Intent startActivity = new Intent(PersonListActivity.this, MainActivity.class);
            startActivity(startActivity);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(ARG_PERSON_ID, personId);
    }

    public void changeFragment2(long personId_) {
        personId = personId_;

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            findViewById(R.id.fragmentContainer1).setVisibility(View.GONE);
        }

        Bundle bundle = new Bundle();
        bundle.putLong(ARG_PERSON_ID, personId_);

        fragment = fragmentManager.findFragmentById(R.id.fragmentContainer2);
        newFragment = new PersonDetailFragment();
        newFragment.setArguments(bundle);

        if (fragment != null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer2, newFragment)
                    .commit();
            // TODO fragment отображается не сразу
            // сначала создается пустое activity
            // мб не пришили результаты asynktask?
        } else {
            fragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .add(R.id.fragmentContainer2, newFragment)
                    .commit();
        }

        findViewById(R.id.fragmentContainer2).setVisibility(View.VISIBLE);
    }

    public void openFragment2 (long personId_) {
        personId = personId_;

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            findViewById(R.id.fragmentContainer1).setVisibility(View.GONE);
        }

        Bundle bundle = new Bundle();
        bundle.putLong(ARG_PERSON_ID, personId_);

        fragment = fragmentManager.findFragmentById(R.id.fragmentContainer2);
        newFragment = new PersonDetailFragment();
        newFragment.setArguments(bundle);

        if (fragment == null) {
            fragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .add(R.id.fragmentContainer2, newFragment)
                    .commit();
        }

        findViewById(R.id.fragmentContainer2).setVisibility(View.VISIBLE);
    }
}
