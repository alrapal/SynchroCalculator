package alrapal.Objects;

public class ShieldAndEpic {
    ////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////ATTRIBUTES////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////
    private String name;
    private int meleeMultiplierMin;
    private int meleeMultiplierMax;
    private int rangedMultiplierMin;
    private int rangedMultiplierMax;
    ////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////CONSTRUCTORS//////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////
    public ShieldAndEpic(String name, int meleeMultiplierMin, int meleeMultiplierMax, int rangedMultiplierMin, int rangedMultiplierMax){
        this.name = name;
        this.meleeMultiplierMin = meleeMultiplierMin;
        this.rangedMultiplierMin = rangedMultiplierMin;
        this.meleeMultiplierMax = meleeMultiplierMax;
        this.rangedMultiplierMax = rangedMultiplierMax;
    }
    ////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////GETTERS & SETTERS/////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getMeleeMultiplierMin() {return meleeMultiplierMin;}
    public void setMeleeMultiplierMin(int meleeMultiplierMin) {this.meleeMultiplierMin = meleeMultiplierMin;}
    public int getRangedMultiplierMin() {return rangedMultiplierMin;}
    public void setRangedMultiplierMin(int rangedMultiplierMin) {this.rangedMultiplierMin = rangedMultiplierMin;}
    public int getMeleeMultiplierMax() {return meleeMultiplierMax;}
    public void setMeleeMultiplierMax(int meleeMultiplierMax) {this.meleeMultiplierMax = meleeMultiplierMax;}
    public int getRangedMultiplierMax() {return rangedMultiplierMax;}
    public void setRangedMultiplierMax(int rangedMultiplierMax) {this.rangedMultiplierMax = rangedMultiplierMax;}

    ////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////METHODS///////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////

    public float calculateRangedMultiplierMin(){
        return (this.rangedMultiplierMin /100 ) + 1;}

    public float calculateRangedMultiplierMax(){
        return (this.rangedMultiplierMax /100 ) + 1;}

    public float calculateMeleeMultiplierMin(){
        return (this.meleeMultiplierMin /100 ) + 1;}

    public float calculateMeleeMultiplierMax(){
        return (this.meleeMultiplierMax /100 ) + 1;}
}
