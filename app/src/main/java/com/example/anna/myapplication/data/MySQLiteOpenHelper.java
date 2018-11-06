package com.example.anna.myapplication.data;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.content.Context;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "myPersonsDB.db";
    public static final String TABLE_NAME = "personsTable";

    public MySQLiteOpenHelper(@Nullable final Context context, final int dbVersion){
        super(context, DATABASE_NAME, null, dbVersion);
    }

    public interface Columns extends BaseColumns {
        String NAME = "name";
        String NOTE = "note";
        String IMAGE_RES = "imageRes";
        String IMAGE_LINK = "imageLink";
        String BIRTHDAY = "birthday";
    }

    @Override
    public void onCreate(@NonNull final SQLiteDatabase dataBase){
        dataBase.execSQL(
                "CREATE TABLE " + TABLE_NAME
                        + " ( "
                        + Columns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + Columns.NAME + " TEXT NOT NULL,"
                        + Columns.NOTE + " TEXT,"
                        + Columns.IMAGE_RES + " INTEGER,"
                        + Columns.IMAGE_LINK + " TEXT,"
                        + Columns.BIRTHDAY + " TEXT"
                        + " );"
        );
    }

    @Override
    public void onUpgrade(final SQLiteDatabase dataBase, final int oldVersion, final int newVersion) {
        dataBase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(dataBase);
    }
}
