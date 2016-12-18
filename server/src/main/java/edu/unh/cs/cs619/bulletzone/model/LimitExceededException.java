package edu.unh.cs.cs619.bulletzone.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public final class LimitExceededException extends Exception {
    LimitExceededException(String msg) {
        super(msg);
    }
}
