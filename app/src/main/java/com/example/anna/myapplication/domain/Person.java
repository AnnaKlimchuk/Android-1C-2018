package com.example.anna.myapplication.domain;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Person {

    public final static String NOT_SPECIFIED_STRING = "";
    private final static Integer NOT_SPECIFIED_INT = -1;
    private final static Integer NOT_SPECIFIED_LONG = -1;

    @PrimaryKey
    private long id;
    private String name;
    private String note;
    private int imageRes;
    private String imageLink;
    private String birthday;

    public Person(){
        this.id = NOT_SPECIFIED_LONG;
        this.name = NOT_SPECIFIED_STRING;
        this.note = NOT_SPECIFIED_STRING;
        this.imageRes = NOT_SPECIFIED_INT;
        this.imageLink = NOT_SPECIFIED_STRING;
        this.birthday = NOT_SPECIFIED_STRING;
    }

    public String getName() {
        return this.name;
    }
    public void setName(final String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }
    public void setNote(final String note){
        this.note = note;
    }

    public int getImageRes() {
        return imageRes;
    }
    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public String getImageLink() {
        return imageLink;
    }
    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        if (id == NOT_SPECIFIED_LONG) {
            return "Person{" + "id=?, name='" + name + "\'" + '}';
        } else {
            return "Person{" + "id=" + id + ", name='" + name + "\'" + '}';
        }
    }
}

