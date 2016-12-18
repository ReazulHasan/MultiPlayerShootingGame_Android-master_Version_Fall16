package edu.unh.cs.cs619.bulletzone.rest;

import org.androidannotations.annotations.EBean;
import org.androidannotations.api.rest.RestErrorHandler;
import org.springframework.core.NestedRuntimeException;

/**
 * Created by simon on 10/3/14.
 */
//@EBean
public class BZRestErrorhandler implements RestErrorHandler {

    public BZRestErrorhandler(){
    }

    @Override
    public void onRestClientExceptionThrown(NestedRuntimeException e) {
        e.printStackTrace();
    }
}
