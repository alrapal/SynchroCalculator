package alrapal.Objects;

import java.util.ArrayList;

public class Enemy {
    ////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////ATTRIBUTES////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////


    private float airRes;
    private float percentageAirRes;
    private float damageMultiplier;
    private ArrayList <ShieldAndEpic> shieldAndEpics;

    ////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////CONSTRUCTORS//////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////

    public Enemy()
    {   this.airRes = 0;
        this.percentageAirRes = 0;
        this.damageMultiplier = 1;
        this.shieldAndEpics = new ArrayList<>();}

    ////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////GETTERS & SETTERS/////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////

    public float getAirRes() {return airRes;}
    public void setAirRes(float airRes) {this.airRes = airRes;}
    public float getPercentageAirRes() {return percentageAirRes;}
    public void setPercentageAirRes(float percentageAirRes) {this.percentageAirRes = percentageAirRes;}
    public float getDamageMultiplier(){return damageMultiplier;}
    public void setDamageMultiplier(float damageMultiplier){this.damageMultiplier = damageMultiplier;}

    ////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////METHODS///////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////

    public void addMultiplier(float newMultiplier){
        this.damageMultiplier = this.damageMultiplier *newMultiplier;
    }

    public float getTotalRangedMultiplierMin(){
        if (shieldAndEpics.isEmpty()){
            return 1;
        }
        float multiplier = 1;
        for (ShieldAndEpic shieldAndEpic : shieldAndEpics){
            multiplier = multiplier * shieldAndEpic.calculateRangedMultiplierMin();
        }
        return multiplier;
    }

    public float getTotalMeleeMultiplierMin(){
        if (shieldAndEpics.isEmpty()){
            return 1;
        }
        float multiplier = 1;
        for (ShieldAndEpic shieldAndEpic : shieldAndEpics){
            multiplier = multiplier * shieldAndEpic.calculateMeleeMultiplierMin();
        }
        return multiplier;
    }

    public void addShieldOrEpic(ShieldAndEpic shieldAndEpic){
        this.shieldAndEpics.add(shieldAndEpic);
    }

    //TODO : get multiplier max

}
