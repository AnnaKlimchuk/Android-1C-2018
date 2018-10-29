package com.example.anna.myapplication;

public class Person {
    private long id;
    private String name;
    private String note;
    private int imageRes;
    private String birthday;

    Person(final long id, final String name, final String note, final int imageRes, final String birthday){
        this.id = id;
        this.name = name;
        this.note = note;
        this.imageRes = imageRes;
        this.birthday = birthday;
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
}
