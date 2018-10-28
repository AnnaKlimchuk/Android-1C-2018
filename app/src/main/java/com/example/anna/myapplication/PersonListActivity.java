package com.example.anna.myapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PersonListActivity extends AppCompatActivity {

    private static final String ARG_PERSON_ID = "personId";
    private FragmentManager fragmentManager;
    private Fragment fragment;
    private Fragment new_fragment;
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
        new_fragment = new PersonListFragment();

        if (fragment != null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer1, new_fragment)
                    .commit();
        } else {
            fragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .add(R.id.fragmentContainer1, new_fragment)
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
            personId = -1;
            findViewById(R.id.fragmentContainer2).setVisibility(View.GONE);
            findViewById(R.id.fragmentContainer1).setVisibility(View.VISIBLE);

            fragment = fragmentManager.findFragmentById(R.id.fragmentContainer1);
            new_fragment = new PersonListFragment();

            if (fragment != null) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer1, new_fragment)
                        .commit();
            } else {
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .add(R.id.fragmentContainer1, new_fragment)
                        .commit();
            }
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

    protected void openFragment2 (long personId_) {
        personId = personId_;

        findViewById(R.id.fragmentContainer2).setVisibility(View.VISIBLE);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            findViewById(R.id.fragmentContainer1).setVisibility(View.GONE);
        }

        Bundle bundle = new Bundle();
        bundle.putLong(ARG_PERSON_ID, personId_);

        new_fragment = new PersonDetailFragment();
        new_fragment.setArguments(bundle);
        fragment = fragmentManager.findFragmentById(R.id.fragmentContainer2);

        if (fragment != null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer2, new_fragment)
                    .commit();
        } else {
            fragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .add(R.id.fragmentContainer2, new_fragment)
                    .commit();
        }
    }
}
