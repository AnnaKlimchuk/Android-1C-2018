package com.example.anna.myapplication;

import android.support.v4.util.LongSparseArray;

public class PersonStorage {
    private static final LongSparseArray<Person> persons = new LongSparseArray<>();

    public static LongSparseArray<Person> getPersonList() {
        return persons;
    }

    public static Person getPersonById(final long id) {
        return persons.get(id);
    }

    private static void addPerson(final String name, final int imageRes){
        long id = persons.size();
        persons.put(id, new Person(id, name, "", imageRes, "press to set birthday"));
    }

    // TODO делать set
    public static void setInitialData(){
        addPerson("Judy Hopps, rabbit", R.drawable.zootopia_rabbit);
        addPerson("Nick Wilde, red fox", R.drawable.zootopia_fox);
        addPerson( "Dawn Bellwether, sheep", R.drawable.zootopia_sheep);
        addPerson("Benjamin Clawhauser, cheetah", R.drawable.zootopia_cheetah);
        addPerson("Gazelle, Thomson's gazelle", R.drawable.zootopia_gazelle);
        addPerson("Flash, three-toed sloth", R.drawable.zootopia_sloth);
    }
}
