package com.minkov.thetwitterapp.responses;

/**
 * Created by minkov on 4/23/16.
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
