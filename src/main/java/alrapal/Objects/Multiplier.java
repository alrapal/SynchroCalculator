package alrapal.Objects;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Multiplier  {

    /** Attributes */
    private String name;
    private float value;
    private BooleanProperty activated;

    /** Constructor */
    public Multiplier(String name, float value){
        this.name = name;
        this.value = value;
        this.activated = new SimpleBooleanProperty(true);
    }

    /** Getters **/
    public String getName(){return this.name;}
    public float getValue(){return this.value;}
    public BooleanProperty activatedProperty() {return this.activated;}

    /** Setters */
    public void setName(String name){this.name = name;}
    public void setValue(float value){this.value = value;}


    /** Methods */
    public String toString(){
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.FRANCE);
        DecimalFormat decimalFormat = new DecimalFormat("#.#", symbols);
        return name + " <> " + decimalFormat.format(value) + "%";
    }

}
