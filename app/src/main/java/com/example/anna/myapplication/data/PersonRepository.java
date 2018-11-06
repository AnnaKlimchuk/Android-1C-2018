package com.example.anna.myapplication.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import android.content.Context;

import com.example.anna.myapplication.data.MySQLiteOpenHelper;
import com.example.anna.myapplication.domain.Person;

public class PersonRepository {

    private final MySQLiteOpenHelper SQLiteOpenHelper;
    private SQLiteDatabase SQLitedatabase;
    private int databaseOpenCloseBalance = 0;
    private ReentrantLock reentrantLock = new ReentrantLock();

    public PersonRepository(@NonNull final Context context){
        SQLiteOpenHelper = new MySQLiteOpenHelper(context, 1);
    }

    public void create(@NonNull final Person person) {
        try {
            SQLiteDatabase database = openDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(MySQLiteOpenHelper.Columns.NAME, person.getName());
            contentValues.put(MySQLiteOpenHelper.Columns.NOTE, person.getNote());
            contentValues.put(MySQLiteOpenHelper.Columns.IMAGE_RES, person.getImageRes());
            contentValues.put(MySQLiteOpenHelper.Columns.IMAGE_LINK, person.getImageLink());
            contentValues.put(MySQLiteOpenHelper.Columns.BIRTHDAY, person.getBirthday());

            database.insert(MySQLiteOpenHelper.TABLE_NAME, null, contentValues);
        } finally {
            closeDatabase();
        }
    }

    public Person getById(long personId) {
        Person person = null;
        Cursor cursor = null;
        try {
            SQLiteDatabase database = openDatabase();

            cursor = database.query(
                    MySQLiteOpenHelper.TABLE_NAME,
                    new String[]{
                            MySQLiteOpenHelper.Columns._ID,
                            MySQLiteOpenHelper.Columns.NAME,
                            MySQLiteOpenHelper.Columns.NOTE,
                            MySQLiteOpenHelper.Columns.IMAGE_RES,
                            MySQLiteOpenHelper.Columns.IMAGE_LINK,
                            MySQLiteOpenHelper.Columns.BIRTHDAY},
                    "_id = ?",
                    new String[] {personId + ""},
                    null,
                    null,
                    null
            );

            while (cursor.moveToNext()) {
                person = new Person();

                person.setId(cursor.getLong(cursor.getColumnIndex(MySQLiteOpenHelper.Columns._ID)));
                person.setName(cursor.getString(cursor.getColumnIndex(MySQLiteOpenHelper.Columns.NAME)));
                person.setNote(cursor.getString(cursor.getColumnIndex(MySQLiteOpenHelper.Columns.NOTE)));
                person.setImageRes(cursor.getInt(cursor.getColumnIndex(MySQLiteOpenHelper.Columns.IMAGE_RES)));
                person.setImageLink(cursor.getString(cursor.getColumnIndex(MySQLiteOpenHelper.Columns.IMAGE_LINK)));
                person.setBirthday(cursor.getString(cursor.getColumnIndex(MySQLiteOpenHelper.Columns.BIRTHDAY)));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            closeDatabase();
        }

        return person;
    }

    public List<Person> loadAll() {
        List<Person> personList = new ArrayList<>();
        Cursor cursor = null;
        try {
            SQLiteDatabase database = openDatabase();
            
            cursor = database.query(
                    MySQLiteOpenHelper.TABLE_NAME,
                    new String[]{
                            MySQLiteOpenHelper.Columns._ID,
                            MySQLiteOpenHelper.Columns.NAME,
                            MySQLiteOpenHelper.Columns.NOTE,
                            MySQLiteOpenHelper.Columns.IMAGE_RES,
                            MySQLiteOpenHelper.Columns.IMAGE_LINK,
                            MySQLiteOpenHelper.Columns.BIRTHDAY},
                    null,
                    null,
                    null,
                    null,
                    null
            );

            while (cursor.moveToNext()) {
                Person person = new Person();
                
                person.setId(cursor.getLong(cursor.getColumnIndex(MySQLiteOpenHelper.Columns._ID)));
                person.setName(cursor.getString(cursor.getColumnIndex(MySQLiteOpenHelper.Columns.NAME)));
                person.setNote(cursor.getString(cursor.getColumnIndex(MySQLiteOpenHelper.Columns.NOTE)));
                person.setImageRes(cursor.getInt(cursor.getColumnIndex(MySQLiteOpenHelper.Columns.IMAGE_RES)));
                person.setImageLink(cursor.getString(cursor.getColumnIndex(MySQLiteOpenHelper.Columns.IMAGE_LINK)));
                person.setBirthday(cursor.getString(cursor.getColumnIndex(MySQLiteOpenHelper.Columns.BIRTHDAY)));

                personList.add(person);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            closeDatabase();
        }

        return personList;
    }

    public void update(long personId, ContentValues updatedValues) {
        try {
            SQLiteDatabase database = openDatabase();

            database.update(MySQLiteOpenHelper.TABLE_NAME, updatedValues, "_id=?", new String[]{String.valueOf(personId)});
        } finally {
            closeDatabase();
        }
    }

    private SQLiteDatabase openDatabase() {
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

    private void closeDatabase() {
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