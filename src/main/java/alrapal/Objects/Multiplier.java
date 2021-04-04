package alrapal.Objects;

public class Multiplier {

    /** Attributes */
    private String name;
    private float value;
    private boolean activated;

    /** Constructor */
    public Multiplier(String name, float value){
        this.name = name;
        this.value = value;
        this.activated = true;
    }

    /** Getters **/
    public String getName(){return this.name;}
    public float getValue(){return this.value;}
    public boolean isActivated(){return this.activated;}

    /** Setters */
    public void setName(String name){this.name = name;}
    public void setValue(float value){this.value = value;}
    public void setActivated(Boolean activated){this.activated = activated;}

    /** Methods */
    public String toString(){
        return name + " <> " + value + "%";
    }
}
