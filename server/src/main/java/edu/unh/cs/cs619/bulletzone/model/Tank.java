package edu.unh.cs.cs619.bulletzone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

public class Tank extends FieldEntity {
    private static final String TAG = "Tank";

    private Person person;
    private boolean hasPersonInside;

    private Stack<FieldEntity> stackedFunctionality;

    private final long id;

    private final String ip;

    private long lastMoveTime;
    private int allowedMoveInterval;

    private long lastFireTime;
    private int allowedFireInterval;

    private int numberOfBullets;
    private int allowedNumberOfBullets;

    private int life;

    private Direction direction;

    public Tank(long id, Direction direction, String ip) {
        this.id = id;
        this.direction = direction;
        this.ip = ip;
        numberOfBullets = 0;
        allowedNumberOfBullets = 2;
        lastFireTime = 0;
        allowedFireInterval = 1500;
        lastMoveTime = 0;
        allowedMoveInterval = 500;
        stackedFunctionality = new Stack<FieldEntity>();
        //initPeriodicTankFunctionalityUpdate();
    }

    public void setPerson(Person person){
        this.person = person;
    }

    public Person getPerson(){
        return this.person;
    }

    public void setPersonInside(){
        hasPersonInside = true;
    }

    public void setPersonOutside(){
        hasPersonInside = false;
    }

    public boolean isPersonInside(){
        return hasPersonInside;
    }

    @Override
    public FieldEntity copy() {
        return new Tank(id, direction, ip);
    }

    @Override
    public void hit(int damage) {

        if (!stackedFunctionality.isEmpty()) {
            Iterator<FieldEntity> iter = stackedFunctionality.iterator();
            while (iter.hasNext()) {
                FieldEntity fe = iter.next();//stackedFunctionality.peek();
                if (fe instanceof ForceField) {
                    damage = damage / 2;
                    ((ForceField) fe).decreaseStrength(10);
                } else if (fe instanceof AntiGrav) {
                    damage = (int) (life * 0.05);
                    ((AntiGrav) fe).decreaseStrength(10);
                } else if (fe instanceof FusionReactor) {
                    ((FusionReactor) fe).decreaseStrength((int) (((FusionReactor) fe).getStrength() * 0.1));
                    //damage = (int)(life*0.1);
                    //((AntiGrav) fe).decreaseStrength(10);
                }
            }
        }

        life = life - damage;
        System.out.println("Tank life: " + id + " : " + life);
//		Log.d(TAG, "TankId: " + id + " hit -> life: " + life);

        if (life <= 0) {
//			Log.d(TAG, "Tank event");
            //eventBus.post(Tank.this);
            //eventBus.post(new Object());
        }
    }

    public long getLastMoveTime() {
        return lastMoveTime;
    }

    public void setLastMoveTime(long lastMoveTime, long delay) {
        long ffPenalty = 1;
        if (!stackedFunctionality.isEmpty()) {
            Iterator<FieldEntity> iter = stackedFunctionality.iterator();
            while(iter.hasNext()) {
                FieldEntity fe = iter.next();//stackedFunctionality.peek();
                System.out.println("fe instanceof AntiGrav: " + (fe instanceof AntiGrav) +
                        ", fe instanceof ForceField: " + (fe instanceof ForceField));
                if (fe instanceof AntiGrav)
                    ffPenalty = 2;
            }
        }
        this.lastMoveTime = lastMoveTime + (delay/ffPenalty);
    }

    public long getAllowedMoveInterval() {
        return allowedMoveInterval;
    }

    public void setAllowedMoveInterval(int allowedMoveInterval) {
        this.allowedMoveInterval = allowedMoveInterval;
    }

    public long getLastFireTime() {
        return lastFireTime;
    }

    public void setLastFireTime(long lastFireTime, long delay) {
        double ffPenalty = 1;
        if(!stackedFunctionality.isEmpty()){
            Iterator<FieldEntity> iter = stackedFunctionality.iterator();
            while(iter.hasNext()) {
                FieldEntity fe = iter.next();//stackedFunctionality.peek();
                if (fe instanceof ForceField)
                    ffPenalty = 2;
                else if (fe instanceof FusionReactor)
                    ffPenalty = 0.5;
            }
        }
        this.lastFireTime = ((int) (lastFireTime + (delay * ffPenalty)) % 1000000 );
    }

    public long getAllowedFireInterval() {
        return allowedFireInterval;
    }

    public void setAllowedFireInterval(int allowedFireInterval) {
        this.allowedFireInterval = allowedFireInterval;
    }

    public int getNumberOfBullets() {
        return numberOfBullets;
    }

    public void setNumberOfBullets(int numberOfBullets) {
        this.numberOfBullets = numberOfBullets;
    }

    public int getAllowedNumberOfBullets() {
        return allowedNumberOfBullets;
    }

    public void setAllowedNumberOfBullets(int allowedNumberOfBullets) {
        this.allowedNumberOfBullets = allowedNumberOfBullets;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @JsonIgnore
    public long getId() {
        return id;
    }

    @Override
    public int getIntValue() {
        return (int) (10000000 + 10000 * id + 10 * life + Direction
                .toByte(direction));
    }

    @Override
    public String toString() {
        return "T";
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public String getIp(){
        return ip;
    }

    public void addStackedFunctionality(FieldEntity fe){
        stackedFunctionality.push(fe);
    }

    public FieldEntity getTopFunctionality(){
        if(!stackedFunctionality.isEmpty())
            return stackedFunctionality.peek();
        return null;
    }

    public FieldEntity removeTopFunctionality(){
        if(!stackedFunctionality.isEmpty())
            return stackedFunctionality.pop();
        return null;
    }

    public List<Integer> getFeaturesList(){
        List<Integer> li = new ArrayList<Integer>();
        if(!stackedFunctionality.isEmpty()){
            Iterator<FieldEntity> iter = stackedFunctionality.iterator();
            while(iter.hasNext()) {
                FieldEntity fe = iter.next();//stackedFunctionality.peek();
                li.add(fe.getIntValue());
            }
        }
        return li;
    }

    /*Random rand;
    private int mPopUpInterval = 1000;
    private void initPeriodicTankFunctionalityUpdate(){
        Timer time = new Timer();
        TankFuncUpdateTask st = new TankFuncUpdateTask();
        time.schedule(st, 0, mPopUpInterval);
    }

    private class TankFuncUpdateTask extends TimerTask {
        // Add your task here
        public void run() {
            if(!stackedFunctionality.isEmpty()) {
                FieldEntity fe = stackedFunctionality.peek();
                if (fe instanceof ForceField) {
                    System.out.println("tank has a forcefield-------- "+((ForceField) fe).getStrength());
                            ((ForceField) fe).damageStrength(1);
                    if( ((ForceField) fe).getStrength()<=0)
                        stackedFunctionality.pop();
                }
            }
        }
    }*/
}
