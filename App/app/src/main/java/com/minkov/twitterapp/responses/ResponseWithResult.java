package com.minkov.twitterapp.responses;

/**
 * Created by dminkov on 4/20/2016.
 */
public class ResponseWithResult<T> {
    private T result;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}

