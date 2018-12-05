package com.example.anna.myapplication.data;

import java.io.FileOutputStream;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.anna.myapplication.presentation.MyApplication;

public class LoadDatabaseIntentService extends IntentService {

    public LoadDatabaseIntentService() {
        super("LoadDatabaseIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            String databaseBackup = MyApplication.getPersonDao().loadAll().toString();
            String fileName = intent.getStringExtra("fileName");
            try(FileOutputStream fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            ) {
                fileOutputStream.write(databaseBackup.getBytes());
            } catch (Exception e) {
                Toast toast = Toast.makeText(this, "An error occurred while writing database", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }
}