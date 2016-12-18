package edu.unh.cs.cs619.bulletzone.util;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

/**
 * Created by simon on 10/1/14.
 */
public class GridWrapper {
    private int[][] grid;
    static private List<TankFeatures> tankFeatures;
    //private TankFeatures[] tankFeatures;
    private long timeStamp;

    public GridWrapper() {
        super();
    }

    /*public GridWrapper(int[][] grid) {
        this.grid = grid;
    }*/

    public GridWrapper(int[][] grid, List<TankFeatures> tankFeatures) {
        this.grid = grid;
        this.tankFeatures = tankFeatures;
    }

    public int[][] getGrid() {
        return this.grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<TankFeatures> getTankFeatures() {
        return tankFeatures;
    }

    public void setTankFeatures(List<TankFeatures> tankFeatures) {
        this.tankFeatures = tankFeatures;
    }
}
