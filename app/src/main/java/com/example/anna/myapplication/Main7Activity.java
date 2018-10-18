package com.example.anna.myapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main7Activity extends AppCompatActivity {

    public static final String ARG_PERSON_ID = "personId";
    private FragmentManager fragmentManager;
    private Fragment fragment;
    private Fragment new_fragment;
    private Bundle bundle;
    private long personId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        if (savedInstanceState != null) {
            personId = savedInstanceState.getLong(ARG_PERSON_ID);
        } else {
            personId = -1;
        }

        fragmentManager = getSupportFragmentManager();
        fragment = fragmentManager.findFragmentById(R.id.fragmentContainer1);
        new_fragment = new PersonListFragment();

        int count = fragmentManager.getBackStackEntryCount();
        for (int i = 0; i < count; ++i) {
            fragmentManager.popBackStack();
        }
        fragmentManager.beginTransaction()
                .add(R.id.fragmentContainer1, new_fragment)
                .commit();

        if (personId != -1) {
            this.openFragment2(personId);
        }
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            personId = -1;
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(ARG_PERSON_ID, personId);
    }

    public void openFragment2 (long personId_) {
        personId = personId_;

        boolean isPortrait = true;
        int id = R.id.fragmentContainer1;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            isPortrait = false;
            id = R.id.fragmentContainer2;
        }

        if (isPortrait) {
            findViewById(R.id.fragmentContainer2).setVisibility(View.GONE);
            fragment = fragmentManager.findFragmentById(R.id.fragmentContainer1);
        } else {
            findViewById(R.id.fragmentContainer2).setVisibility(View.VISIBLE);
            fragment = fragmentManager.findFragmentById(R.id.fragmentContainer2);
        }

        bundle = new Bundle();
        bundle.putLong(ARG_PERSON_ID, personId_);

        new_fragment = new Fragment2();
        new_fragment.setArguments(bundle);

        if (fragment != null) {
            if (isPortrait) {
                fragmentManager.beginTransaction()
                        .hide(fragment)
                        .commit();
            }
            fragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(id, new_fragment)
                    .commit();
        } else {
            fragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .add(id, new_fragment)
                    .commit();
        }
    }
}
