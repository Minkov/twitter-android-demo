package com.minkov.twitterapp.utils;

import android.app.DownloadManager;

import com.google.gson.Gson;
import com.minkov.twitterapp.models.Tweet;
import com.minkov.twitterapp.responses.TweetsResponse;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dminkov on 4/19/2016.
 */
public class HttpRequester {
    OkHttpClient client = new OkHttpClient();

    public String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public <T> T runGeneric(String url, Class<T> klass) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        String json = response.body().string();

        Gson gson = new Gson();

        T obj = (T) gson.fromJson(json, klass);
        return obj;
    }
}

