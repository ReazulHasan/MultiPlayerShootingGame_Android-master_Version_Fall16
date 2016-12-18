package edu.unh.cs.cs619.bulletzone.util;

import java.io.Serializable;

/**
 * Created by simon on 10/1/14.
 */
public class ResultWrapper<T> implements Serializable {
    private T result;

    public ResultWrapper() {
    }

    public ResultWrapper(T result) {
        this.result = result;
    }

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
