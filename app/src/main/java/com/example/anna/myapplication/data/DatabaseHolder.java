package com.example.anna.myapplication.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.concurrent.locks.ReentrantLock;

public class DatabaseHolder {

    private final AppSQLiteOpenHelper SQLiteOpenHelper;
    private SQLiteDatabase SQLitedatabase;
    private int databaseOpenCloseBalance = 0;
    private ReentrantLock reentrantLock = new ReentrantLock();

    public DatabaseHolder(@NonNull final Context context){
        SQLiteOpenHelper = new AppSQLiteOpenHelper(context, 1);
    }

    public SQLiteDatabase openDatabase() {
        try {
            reentrantLock.lock();
            if (databaseOpenCloseBalance == 0) {
                SQLitedatabase = SQLiteOpenHelper.getWritableDatabase();
            }

            ++databaseOpenCloseBalance;

            return SQLitedatabase;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void closeDatabase() {
        try {
            reentrantLock.lock();
            --databaseOpenCloseBalance;

            if (databaseOpenCloseBalance == 0) {
                SQLitedatabase.close();
                SQLitedatabase = null;
            }
        } finally {
            reentrantLock.unlock();
        }
    }
}
