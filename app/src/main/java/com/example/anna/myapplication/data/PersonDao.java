package com.example.anna.myapplication.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.anna.myapplication.domain.Person;

import java.util.List;

@Dao
public interface PersonDao {

    // Добавление Person в бд
    @Insert
    void insert(Person person);

    // Удаление Person из бд
    @Delete
    void delete(Person person);

    // Обновление Person из бд
    @Update
    void update(Person person);

    // Получение всех Person из бд
    // table name = class name
    @Query("SELECT * FROM person")
    List<Person> loadAll();

    // Получение Person по заданному id
    // table name = class name
    @Query("SELECT * FROM person WHERE id = :id")
    Person getById(long id);
}