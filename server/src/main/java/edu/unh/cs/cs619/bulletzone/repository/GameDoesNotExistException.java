package edu.unh.cs.cs619.bulletzone.repository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public final class GameDoesNotExistException extends Exception {

    GameDoesNotExistException(int gameId) {
        super(String.format("Game with id '%s' does not exist", gameId));
    }

}
