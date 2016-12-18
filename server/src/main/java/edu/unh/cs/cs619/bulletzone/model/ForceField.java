package edu.unh.cs.cs619.bulletzone.model;

/**
 * Created by Reazul Hasan Russel on 10/26/2016.
 */
public class ForceField extends FieldEntity  {
    private int destructValue, pos, strength, maxStrength;

    public ForceField(){
    }

    public ForceField(int pos){
        this.pos = pos;
        this.destructValue = 3000;
        this.strength = 50;
        this.maxStrength = 100;
    }

    public int getStrength(){
        return this.strength;
    }

    public int getMaxStrength(){
        return maxStrength;
    }

    public void decreaseStrength(int x){
        strength -= x;
    }

    @Override
    public FieldEntity copy() {
        return new ForceField();
    }

    @Override
    public int getIntValue() {
        return destructValue;
    }

    @Override
    public String toString() {
        return "F";
    }

    public int getPos(){
        return pos;
    }
}
