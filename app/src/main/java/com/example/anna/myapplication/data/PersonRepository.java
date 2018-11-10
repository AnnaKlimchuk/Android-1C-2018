package com.example.anna.myapplication.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import com.example.anna.myapplication.domain.Person;

public class PersonRepository {

    private DatabaseHolder databaseHolder;

    public PersonRepository(@NonNull final DatabaseHolder databaseHolder) {
        this.databaseHolder = databaseHolder;
    }

    public void create(@NonNull final Person person) {
        try {
            SQLiteDatabase database = databaseHolder.openDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(PersonContract.NAME, person.getName());
            contentValues.put(PersonContract.NOTE, person.getNote());
            contentValues.put(PersonContract.IMAGE_RES, person.getImageRes());
            contentValues.put(PersonContract.IMAGE_LINK, person.getImageLink());
            contentValues.put(PersonContract.BIRTHDAY, person.getBirthday());

            database.insert(PersonContract.TABLE_NAME, null, contentValues);
        } finally {
            databaseHolder.closeDatabase();
        }
    }

    public Person getById(long personId) {
        Person person = null;
        Cursor cursor = null;
        try {
            SQLiteDatabase database = databaseHolder.openDatabase();

            cursor = database.query(
                    PersonContract.TABLE_NAME,
                    new String[]{
                            PersonContract._ID,
                            PersonContract.NAME,
                            PersonContract.NOTE,
                            PersonContract.IMAGE_RES,
                            PersonContract.IMAGE_LINK,
                            PersonContract.BIRTHDAY},
                    "_id = ?",
                    new String[] {personId + ""},
                    null,
                    null,
                    null
            );

            while (cursor.moveToNext()) {
                person = new Person();

                person.setId(cursor.getLong(cursor.getColumnIndex(PersonContract._ID)));
                person.setName(cursor.getString(cursor.getColumnIndex(PersonContract.NAME)));
                person.setNote(cursor.getString(cursor.getColumnIndex(PersonContract.NOTE)));
                person.setImageRes(cursor.getInt(cursor.getColumnIndex(PersonContract.IMAGE_RES)));
                person.setImageLink(cursor.getString(cursor.getColumnIndex(PersonContract.IMAGE_LINK)));
                person.setBirthday(cursor.getString(cursor.getColumnIndex(PersonContract.BIRTHDAY)));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            databaseHolder.closeDatabase();
        }

        return person;
    }

    public List<Person> loadAll() {
        List<Person> personList = new ArrayList<>();
        Cursor cursor = null;
        try {
            SQLiteDatabase database = databaseHolder.openDatabase();
            
            cursor = database.query(
                    PersonContract.TABLE_NAME,
                    new String[]{
                            PersonContract._ID,
                            PersonContract.NAME,
                            PersonContract.NOTE,
                            PersonContract.IMAGE_RES,
                            PersonContract.IMAGE_LINK,
                            PersonContract.BIRTHDAY},
                    null,
                    null,
                    null,
                    null,
                    null
            );

            while (cursor.moveToNext()) {
                Person person = new Person();
                
                person.setId(cursor.getLong(cursor.getColumnIndex(PersonContract._ID)));
                person.setName(cursor.getString(cursor.getColumnIndex(PersonContract.NAME)));
                person.setNote(cursor.getString(cursor.getColumnIndex(PersonContract.NOTE)));
                person.setImageRes(cursor.getInt(cursor.getColumnIndex(PersonContract.IMAGE_RES)));
                person.setImageLink(cursor.getString(cursor.getColumnIndex(PersonContract.IMAGE_LINK)));
                person.setBirthday(cursor.getString(cursor.getColumnIndex(PersonContract.BIRTHDAY)));

                personList.add(person);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            databaseHolder.closeDatabase();
        }

        return personList;
    }

    public void update(long personId, ContentValues updatedValues) {
        try {
            SQLiteDatabase database = databaseHolder.openDatabase();

            database.update(PersonContract.TABLE_NAME, updatedValues, "_id=?", new String[]{String.valueOf(personId)});
        } finally {
            databaseHolder.closeDatabase();
        }
    }
}