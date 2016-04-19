package com.minkov.twitterapp.models;

/**
 * Created by dminkov on 4/14/2016.
 */
public class Tweet {

    private int id;
    private String text;

    public static Tweet build(int id, String text) {
        Tweet tweet = new Tweet();
        tweet.setId(id);
        tweet.setText(text);
        return tweet;
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
}
