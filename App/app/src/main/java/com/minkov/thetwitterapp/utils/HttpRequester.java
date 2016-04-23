package com.minkov.thetwitterapp.utils;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

/**
 * Created by minkov on 4/23/16.
 */
public class HttpRequester {
    public static final MediaType MEDIA_TYPE_JSON
            = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient client;
    private static HttpRequester instance;

    static {
        instance = new HttpRequester();
    }

    public static HttpRequester getHttpRequester(){
        return instance;
    }

    private HttpRequester(){
        this.client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    public <T> T get(String url, Class<T> klass) throws IOException {
        Request request = new Request.Builder()
                    .url(url)
                    .build();

        Response response = client.newCall(request).execute();
        String json = response.body().string();
        Gson gson = new Gson();
        T result = gson.fromJson(json, klass);
        return result;
    }

    public <T> T post(String url, Object data, Class<T> klass) throws IOException {
        Gson gson = new Gson();
        String jsonData = gson.toJson(data);

        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON,
                    jsonData);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        String json = response.body().string();
        T result = gson.fromJson(json, klass);
        return result;
    }
}
