package com.minkov.twitterapp.utils;

import java.io.IOException;

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
}

