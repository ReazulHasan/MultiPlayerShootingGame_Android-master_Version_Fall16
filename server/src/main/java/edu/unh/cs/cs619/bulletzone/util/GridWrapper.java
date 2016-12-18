package edu.unh.cs.cs619.bulletzone.util;

import java.util.List;

import edu.unh.cs.cs619.bulletzone.model.FieldEntity;

/**
 * Created by simon on 10/1/14.
 */
public class GridWrapper {
    private int[][] grid;
    private List<TankFeatures> tankFeatures;
    private long timeStamp;

    public GridWrapper() {
    }

    public GridWrapper(int[][] grid, List<TankFeatures> tankFeatures) {
        this.grid = grid;
        this.tankFeatures = tankFeatures;
        this.timeStamp = System.currentTimeMillis();
    }

    public List<TankFeatures> getTankFeatures() {
        return tankFeatures;
    }

    public void setTankFeatures(List<TankFeatures> tankFeatures) {
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
}
