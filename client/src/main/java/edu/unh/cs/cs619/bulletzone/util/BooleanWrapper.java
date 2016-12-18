package edu.unh.cs.cs619.bulletzone.util;

/**
 * Created by simon on 10/1/14.
 */
public class BooleanWrapper {
    private boolean result;
    private String name;

    public BooleanWrapper() {

    }

    public BooleanWrapper(String name) {
        this.name = name;
    }

    public BooleanWrapper(boolean result) {
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
