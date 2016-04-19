package com.minkov.twitterapp.responses;

import com.minkov.twitterapp.models.Tweet;

import java.util.List;

/**
 * Created by dminkov on 4/19/2016.
 */
public class TweetsResponse {
    private List<Tweet> result;

    public List<Tweet> getResult() {
        return result;
    }

    public void setResult(List<Tweet> result) {
        this.result = result;
    }
}
