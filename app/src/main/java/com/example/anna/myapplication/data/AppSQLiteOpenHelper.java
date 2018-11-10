package com.example.anna.myapplication.data;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.content.Context;

public class AppSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "myPersonsDB.db";

    public AppSQLiteOpenHelper(@Nullable final Context context, final int dbVersion){
        super(context, DATABASE_NAME, null, dbVersion);
    }

    @Override
    public void onCreate(@NonNull final SQLiteDatabase dataBase){
        dataBase.execSQL(
                "CREATE TABLE " + PersonContract.TABLE_NAME
                        + " ( "
                        + PersonContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + PersonContract.NAME + " TEXT NOT NULL,"
                        + PersonContract.NOTE + " TEXT,"
                        + PersonContract.IMAGE_RES + " INTEGER,"
                        + PersonContract.IMAGE_LINK + " TEXT,"
                        + PersonContract.BIRTHDAY + " TEXT"
                        + " );"
        );
    }

    @Override
    public void onUpgrade(final SQLiteDatabase dataBase, final int oldVersion, final int newVersion) {
        dataBase.execSQL("DROP TABLE IF EXISTS " + PersonContract.TABLE_NAME);
        onCreate(dataBase);
    }
}
