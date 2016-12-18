package edu.unh.cs.cs619.bulletzone.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public final class PlayerDoesNotExistException extends Exception {

    PlayerDoesNotExistException(String name) {
        super(String.format("Player with name '%s' does not exist", name));
    }

}
