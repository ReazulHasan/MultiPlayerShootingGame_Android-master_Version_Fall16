package edu.unh.cs.cs619.bulletzone.model;

/**
 * Created by Reazul Hasan Russel on 10/27/2016.
 */
public class AntiGrav extends FieldEntity {

    private int destructValue, pos, strength;

    public AntiGrav(){
    }

    public AntiGrav(int pos){
        this.pos = pos;
        this.destructValue = 2002;
        this.strength = 50;
    }

    public int getStrength(){
        return this.strength;
    }

    public void decreaseStrength(int x){
        strength -= x;
    }

    @Override
    public FieldEntity copy() {
        return new AntiGrav();
    }

    @Override
    public int getIntValue() {
        return destructValue;
    }

    @Override
    public String toString() {
        return "A";
    }

    public int getPos(){
        return pos;
    }

}
