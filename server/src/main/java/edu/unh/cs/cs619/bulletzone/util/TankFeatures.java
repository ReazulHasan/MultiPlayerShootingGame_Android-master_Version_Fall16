package edu.unh.cs.cs619.bulletzone.util;

import java.util.List;

/**
 * Created by Reazul Hasan Russel on 10/31/2016.
 */
public class TankFeatures {
    Long tankID;
    List<Integer> features;

    public TankFeatures(Long tankID){
        this.tankID = tankID;
    }

    public List<Integer> getFeatures() {
        return features;
    }

    public void setFeatures(List<Integer> features) {
        this.features = features;
    }

    public Long getTankID() {
        return tankID;
    }

    public void setTankID(Long tankID) {
        this.tankID = tankID;
    }
}
