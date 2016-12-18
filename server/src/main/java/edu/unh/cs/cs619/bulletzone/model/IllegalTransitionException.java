package edu.unh.cs.cs619.bulletzone.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// TODO 2: CONFLICT status code
@ResponseStatus(HttpStatus.CONFLICT)
public final class IllegalTransitionException extends Exception {

    IllegalTransitionException(Long gameId, Long tankId, Direction from, Direction to) {
        super(String.format("It is illegal to turn tank '%d' in game '%d' from '%s' to '%s'",
                tankId, gameId, from, to));
    }

}
