package com.example.anna.myapplication;

import java.io.FileOutputStream;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.example.anna.myapplication.presentation.MyApplication;

public class LoadDatabaseIntentService extends IntentService {

    private FileOutputStream fileOutputStream = null;

    public LoadDatabaseIntentService() {
        super("LoadDatabaseIntentService");
    }

    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String databaseBackup = MyApplication.getPersonDao().loadAll().toString();
        String fileName = intent.getStringExtra("fileName");
        // todo catch
        try{
            fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            fileOutputStream.write(databaseBackup.getBytes());
            fileOutputStream.close();
        } catch (Exception e){}
    }
}