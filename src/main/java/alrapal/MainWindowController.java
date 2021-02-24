package alrapal;
import alrapal.Objects.*;
import alrapal.Exceptions.InvalidBoostException;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;

import java.util.*;


public class MainWindowController {

    ////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////ATTRIBUTES////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////
    public static final String EOL = System.lineSeparator();
    private Synchro synchro = new Synchro();
    private Enemy enemy = new Enemy();
    private Map <String , ShieldAndEpic>  allShieldsAndEpics;
    private String [] suggestions = {"Hey", "Hello", "Hello World"};


    public void initialize(){
        TextFields.bindAutoCompletion(shieldAndEpicInput,suggestions);
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////JAVAFX ELEMENTS///////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////

    public Button reset;
    public Label rangedDamages;
    public Label meleeDamages;
    public Parent mainRoot;
    public TextField boost;
    public Label infoLabel;
    public Button calculate;
    public TextField resFixes;
    public RadioButton keepOnTop;
    public TextField resPercentage;
    public RadioButton synchroLvl1;
    public RadioButton synchroLvl2;
    public RadioButton synchroLvl3;
    public TextField damageMultiplierNameInput;
    public TextField damageMultiplierValueInput;
    public Label damageMultiplierOutput;
    public CustomTextField shieldAndEpicInput;



    //////////////////////////////////////SPELL BUTTONS ////////////////////////////////


    public ToggleButton teleportationButton;
    public ToggleButton RSbutton;
    public ToggleButton frappeButton;
    public ToggleButton engrenageButton;
    public ToggleButton gelureButton;
    public ToggleButton perturbationButton;
    public ToggleButton poussiereButton;
    public ToggleButton souvenirButton;
    public ToggleButton paradoxeButton;
    public ToggleButton failleButton;
    public ToggleButton distortionButton;
    public ToggleButton penduleButton;
    public ToggleButton raulebackButton;
    public ToggleButton instabiliteButton;
    public ToggleButton bouclierButton;
    public ToggleButton desychroButton;
    public ToggleButton fuiteButton;
    public ToggleButton premoButton;
    public ToggleButton remboButton;
    public ToggleButton renvoiButton;

    ////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////DIRECT INPUTS/////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////

    public void updateDamageMultiplier(ActionEvent actionEvent){
        String name = damageMultiplierNameInput.getText();
        String valueStr = damageMultiplierValueInput.getText();
        try {
            float newMultiplicator = Float.valueOf(valueStr)/100;
            enemy.addMultiplicator(newMultiplicator);
            updateMultiplicatorOutput(name, valueStr);
        }catch (NumberFormatException numberFormatException){
            infoLabel.setText("Format du multiplicateur incorrect. Entrez un nombre");
        }
    }

    private void updateMultiplicatorOutput(String name, String value){
        String currentMultiplicators = damageMultiplierOutput.getText();
        String newMultiplicator = name + " - " + value +"%" + EOL;
        damageMultiplierOutput.setText(currentMultiplicators + newMultiplicator);
    }

    public String calculateDamages() {
        float boost = synchro.getBoost();
        float baseDamage = synchro.getBaseDamage();
        float airRes = enemy.getAirRes();
        float percentageAirRes = enemy.getPercentageAirRes();
        float multiplicator = enemy.getDamageMultiplicator();
        float totalDamage = (baseDamage - airRes) * (1-(percentageAirRes/100)) * multiplicator * ((boost/100)-1);
        int roundedTotalDamage = Math.round(totalDamage);
        if (roundedTotalDamage < 0){
            return "0";
        }else{
            return String.valueOf(roundedTotalDamage);
        }
    }

    public void handleResFixInput(ActionEvent actionEvent) {
        resFixes.textProperty().addListener((observable, oldValue, newValue) -> {
            resFixes.setText(newValue);
        });
    }

    public void handleResPercentageInput(ActionEvent actionEvent) {
        resFixes.textProperty().addListener((observable, oldValue, newValue) -> {
            resFixes.setText(newValue);
        });
    }

    public void handleBoostInput(ActionEvent actionEvent) {
        resFixes.textProperty().addListener((observable, oldValue, newValue) -> {
            resFixes.setText(newValue);
        });
    }


    ////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////INPUT BY SELECTION////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////

    public void synchroIsLvl3(ActionEvent actionEvent) {
        synchro.setBaseDamage(368);
    }

    public void synchroIsLvl2(ActionEvent actionEvent) {
        synchro.setBaseDamage(288);
    }

    public void synchroIsLvl1(ActionEvent actionEvent) {
        synchro.setBaseDamage(152);
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////SETTINGS AND OPTIONS//////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////

    public void keepOnTop(MouseEvent mouseEvent) {
        Stage stage = (Stage) mainRoot.getScene().getWindow();
        if (keepOnTop.isSelected()){
            stage.setAlwaysOnTop(true);
        }else {
            stage.setAlwaysOnTop(false);
        }
    }

    public void resetValues(ActionEvent actionEvent) {
        enemy = new Enemy();
        synchro = new Synchro();
        boost.setText("0");
        resFixes.setText("0");
        resPercentage.setText("0");
        rangedDamages.setText("0");
        infoLabel.setText("");
        resetSpellButtons();
        synchroLvl3.setSelected(true);
        resetMultiplicator();
    }

    public void resetMultiplicator(){
        damageMultiplierOutput.setText("");
        damageMultiplierNameInput.setText("");
        damageMultiplierValueInput.setText("");
    }

    public void resetSpellButtons(){
        teleportationButton.setSelected(false);
        RSbutton.setSelected(false);
        frappeButton.setSelected(false);
        engrenageButton.setSelected(false);
        gelureButton.setSelected(false);
        perturbationButton.setSelected(false);
        poussiereButton.setSelected(false);
        souvenirButton.setSelected(false);
        paradoxeButton.setSelected(false);
        failleButton.setSelected(false);
        distortionButton.setSelected(false);
        penduleButton.setSelected(false);
        raulebackButton.setSelected(false);
        instabiliteButton.setSelected(false);
        bouclierButton.setSelected(false);
        desychroButton.setSelected(false);
        fuiteButton.setSelected(false);
        premoButton.setSelected(false);
        remboButton.setSelected(false);
        renvoiButton.setSelected(false);
    }

    public void updateBoost(ActionEvent actionEvent) {
        int boostCount = 0;
        boostCount += checkSpell(teleportationButton);
        boostCount += checkSpell(RSbutton);
        boostCount += checkSpell(frappeButton);
        boostCount += checkSpell(engrenageButton);
        boostCount += checkSpell(gelureButton);
        boostCount += checkSpell(perturbationButton);
        boostCount += checkSpell(distortionButton);
        boostCount += checkSpell(poussiereButton);
        boostCount += checkSpell(paradoxeButton);
        boostCount += checkSpell(failleButton);
        boostCount += checkSpell(raulebackButton);
        boostCount += checkSpell(instabiliteButton);
        boostCount += checkSpell(penduleButton);
        boostCount += checkSpell(souvenirButton);
        boostCount += checkSpell(fuiteButton);
        boostCount += checkSpell(premoButton);
        boostCount += checkSpell(desychroButton);
        boostCount += checkSpell(bouclierButton);
        boostCount += checkSpell(remboButton);
        boostCount += checkSpell(renvoiButton);
        int totalBoost = boostCount*200;
        String strTotalBoost = String.valueOf(totalBoost);
        boost.setText(strTotalBoost);




    }

    public int checkSpell(ToggleButton spellButton){
        if (spellButton.isSelected()){
            return +1;
        }else{
            return +0;
        }
    }

    public void displayDamage(ActionEvent actionEvent) {
        infoLabel.setText("");
        try {
            synchro.setBoost(Integer.parseInt(boost.getText()));
            enemy.setAirRes(Integer.parseInt(resFixes.getText()));
            enemy.setPercentageAirRes(Integer.parseInt(resPercentage.getText()));
            rangedDamages.setText(calculateDamages());
        } catch (InvalidBoostException invalidBoostFormat) {
            resetValues(actionEvent);
            infoLabel.setText(invalidBoostFormat.getMessage());
        }catch (Exception e){
            infoLabel.setText("Tu dois taper un nombre dans chaque champ ci-dessus");
        }
    }


}
