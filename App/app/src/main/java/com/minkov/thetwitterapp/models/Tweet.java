package com.minkov.thetwitterapp.models;

import java.io.Serializable;

/**
 * Created by dminkov on 4/22/2016.
 */
public class Tweet implements Serializable {
    private int id;
    private String text;
    private String author;

    public Tweet(int id, String text, String author){
        this.setId(id);
        this.setText(text);
        this.setAuthor(author);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
