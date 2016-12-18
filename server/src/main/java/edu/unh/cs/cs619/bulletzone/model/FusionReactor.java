package edu.unh.cs.cs619.bulletzone.model;

/**
 * Created by Reazul Hasan Russel on 10/27/2016.
 */
public class FusionReactor  extends FieldEntity {

    private int destructValue, pos, strength;

    public FusionReactor(){
    }

    public FusionReactor(int pos){
        this.pos = pos;
        this.destructValue = 2003;
        this.strength = 100;
    }

    public int getStrength(){
        return this.strength;
    }

    public void decreaseStrength(int x){
        strength -= x;
    }

    @Override
    public FieldEntity copy() {
        return new FusionReactor();
    }

    @Override
    public int getIntValue() {
        return destructValue;
    }

    @Override
    public String toString() {
        return "R";
    }

    public int getPos(){
        return pos;
    }

}
