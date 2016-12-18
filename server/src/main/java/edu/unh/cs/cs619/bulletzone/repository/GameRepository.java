package edu.unh.cs.cs619.bulletzone.repository;

import java.util.List;

import edu.unh.cs.cs619.bulletzone.model.Direction;
import edu.unh.cs.cs619.bulletzone.model.IllegalTransitionException;
import edu.unh.cs.cs619.bulletzone.model.LimitExceededException;
import edu.unh.cs.cs619.bulletzone.model.Tank;
import edu.unh.cs.cs619.bulletzone.model.TankDoesNotExistException;
import edu.unh.cs.cs619.bulletzone.util.TankFeatures;

public interface GameRepository {

    Tank join(String ip);

    int[][] getGrid();

    List<TankFeatures> getTankFeatures();

    boolean turn(long tankId, Direction direction)
            throws TankDoesNotExistException, IllegalTransitionException, LimitExceededException;

    boolean move(long tankId, Direction direction)
            throws TankDoesNotExistException, IllegalTransitionException, LimitExceededException;

    boolean fire(long tankId, int strength)
            throws TankDoesNotExistException, LimitExceededException;

    boolean ejectFeature(long tankId)
            throws TankDoesNotExistException;

    boolean ejectPerson(long tankId)
            throws TankDoesNotExistException;

    void leave(long tankId)
            throws TankDoesNotExistException;
}
