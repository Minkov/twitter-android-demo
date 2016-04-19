package com.minkov.twitterapp.dataServices;

import android.os.AsyncTask;

import com.annimon.stream.function.Function;
import com.google.gson.Gson;
import com.minkov.twitterapp.models.Tweet;
import com.minkov.twitterapp.responses.TweetsResponse;
import com.minkov.twitterapp.utils.HttpRequester;

import java.io.IOException;
import java.util.List;

/**
 * Created by dminkov on 4/19/2016.
 */
public class TweetsDataService {
    private AsyncTask<Void, Void, Void> getAll;
    private HttpRequester requester;
    private String url = "http://192.168.201.163:3001/api/tweets";

    public TweetsDataService(Function<List<Tweet>, Void> getAllFinished) {
        this.requester = new HttpRequester();
        this.getAll = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                String json = null;
                try {
                    json = requester.run(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Gson gson = new Gson();
                List<Tweet> tweets = gson.fromJson(json, TweetsResponse.class).getResult();
                getAllFinished.apply(tweets);
                return null;
            }

        };
    }

    public AsyncTask<Void, Void, Void> getGetAll() {
        return getAll;
    }
}
