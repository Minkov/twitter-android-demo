package com.minkov.twitterapp.models;

/**
 * Created by dminkov on 21-Apr-16.
 */
public class Author {
    private int id;
    private String name;


    public Author(int id, String name){
        this.setId(id);
        this.setName(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
