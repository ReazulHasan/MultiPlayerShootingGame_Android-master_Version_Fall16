package edu.unh.cs.cs619.bulletzone.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public final class TankDoesNotExistException extends Exception {
    public TankDoesNotExistException(Long tankId) {
        super(String.format("Tank '%d' does not exist", tankId));
    }
}
