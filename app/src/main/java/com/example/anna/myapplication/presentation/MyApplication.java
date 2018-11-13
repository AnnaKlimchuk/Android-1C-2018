package com.example.anna.myapplication.presentation;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Intent;

import com.example.anna.myapplication.LoadDatabaseIntentService;
import com.example.anna.myapplication.data.AppDatabase;
import com.example.anna.myapplication.data.DatabaseHolder;
import com.example.anna.myapplication.data.PersonDao;
import com.example.anna.myapplication.data.PersonRepository;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.Date;

public class MyApplication extends Application {

    private static AppDatabase database;
    private static PersonRepository personRepository;
    private static DatabaseHolder databaseHolder;
    private static String fileName;

    public static PersonDao getPersonDao(){
        return database.PersonDao();
    }
    public static PersonRepository getRepository(){
        return personRepository;
    }
    public static String getFileName(){
        return fileName;
    }

    private String getFileNameOnCurrentTime(){
        Date currentDate = new Date();
        long millisFromStart = currentDate.getTime();
        return "backup-" + millisFromStart;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);

        // SQLite
        databaseHolder = new DatabaseHolder(this);
        personRepository = new PersonRepository(databaseHolder);
        // ROOM
        database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .build();

        setInitialData();

        // Запускаем свой IntentService
        Intent intentMyIntentService = new Intent(this, LoadDatabaseIntentService.class);
        fileName = getFileNameOnCurrentTime();
        startService(intentMyIntentService.putExtra("fileName", fileName));
    }

    public static void setInitialData(){
        // TODO it is not right
        boolean useMyStorage = false;

        new SetDataIfNeededAsyncTask().execute(useMyStorage);
    }
}


