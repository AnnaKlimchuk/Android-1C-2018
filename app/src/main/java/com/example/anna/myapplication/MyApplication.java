package com.example.anna.myapplication;

import android.app.Application;

public class MyApplication extends Application {
    @Override
    public final void onCreate() {
        super.onCreate();
        PersonStorage.setInitialData();
    }
}
