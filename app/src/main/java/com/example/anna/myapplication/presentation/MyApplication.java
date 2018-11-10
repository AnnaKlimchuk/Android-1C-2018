package com.example.anna.myapplication.presentation;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.anna.myapplication.data.AppDatabase;
import com.example.anna.myapplication.data.DatabaseHolder;
import com.example.anna.myapplication.data.PersonDao;
import com.example.anna.myapplication.data.PersonRepository;
import com.facebook.drawee.backends.pipeline.Fresco;

public class MyApplication extends Application {

    private static AppDatabase database;
    private static PersonRepository personRepository;
    private static DatabaseHolder databaseHolder;

    public static PersonDao getPersonDao(){
        return database.PersonDao();
    }
    public static PersonRepository getRepository(){
        return personRepository;
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
    }

    public static void setInitialData(){
        // TODO it is not right
        boolean useMyStorage = false;

        new SetDataIfNeededAsyncTask().execute(useMyStorage);
    }
}


