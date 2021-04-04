package alrapal.Objects;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Enemy {
    ////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////ATTRIBUTES////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////

    private float airRes;
    private float percentageAirRes;
    private float damageMultiplier;
    private Map<String, ShieldAndEpic> shieldAndEpics;

    ////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////CONSTRUCTORS//////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////

    public Enemy()
    {   this.airRes = 0;
        this.percentageAirRes = 0;
        this.damageMultiplier = 1;
        this.shieldAndEpics = new HashMap<>();}

    ////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////GETTERS & SETTERS/////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////

    public float getAirRes() {return airRes;}
    public void setAirRes(float airRes) {this.airRes = airRes;}
    public float getPercentageAirRes() {return percentageAirRes;}
    public void setPercentageAirRes(float percentageAirRes) {this.percentageAirRes = percentageAirRes;}
    public float getDamageMultiplier(){return damageMultiplier;}
    public void setDamageMultiplier(float damageMultiplier){this.damageMultiplier = damageMultiplier;}
    public Map<String, ShieldAndEpic> getShieldAndEpics() {return shieldAndEpics;}
    public void setShieldAndEpics(Map<String, ShieldAndEpic> shieldAndEpics) {this.shieldAndEpics = shieldAndEpics; }

    ////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////METHODS///////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////

    public void addMultiplier(float newMultiplier){
        this.damageMultiplier = this.damageMultiplier * (newMultiplier/100);
    }

    public void removeMultiplier(float multiplier){
        this.damageMultiplier = this.damageMultiplier / (multiplier/100);
    }

    public float getTotalRangedMultiplierMin(){
        if (shieldAndEpics.isEmpty()){
            return 1;
        }
        float multiplier = 1;
        for (ShieldAndEpic shieldAndEpic : shieldAndEpics.values()) {
            multiplier = multiplier * shieldAndEpic.calculateRangedMultiplierMin();
        }

        return multiplier;
    }

    public float getTotalRangedMultiplierMax(){
        if (shieldAndEpics.isEmpty()){
            return 1;
        }
        float multiplier = 1;
        for (ShieldAndEpic shieldAndEpic : shieldAndEpics.values()) {
            multiplier = multiplier * shieldAndEpic.calculateRangedMultiplierMax();
        }
        return multiplier;
    }

    public float getTotalMeleeMultiplierMin(){
        if (shieldAndEpics.isEmpty()){
            return 1;
        }
        float multiplier = 1;
        for (ShieldAndEpic shieldAndEpic : shieldAndEpics.values()) {
            multiplier = multiplier * shieldAndEpic.calculateMeleeMultiplierMin();
        }
        return multiplier;
    }

    public float getTotalMeleeMultiplierMax(){
        if (shieldAndEpics.isEmpty()){
            return 1;
        }
        float multiplier = 1;
        for (ShieldAndEpic shieldAndEpic : shieldAndEpics.values()) {
            multiplier = multiplier * shieldAndEpic.calculateMeleeMultiplierMax();
        }
        return multiplier;
    }



}
