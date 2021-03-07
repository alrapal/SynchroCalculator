package alrapal;

import alrapal.Exceptions.InvalidBoostException;
import alrapal.Objects.Enemy;
import alrapal.Objects.ShieldAndEpic;
import alrapal.Objects.Synchro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainWindowController {

    ////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////ATTRIBUTES////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////
    public static final String EOL = System.lineSeparator();
    private Synchro synchro = new Synchro();
    private Enemy enemy = new Enemy();
    public static  Map <String , ShieldAndEpic>  allShieldsAndEpics = new HashMap<>();
    public static ArrayList <String> suggestions = new ArrayList<>();
    private static ObservableList <String> registeredShieldsAndEpics = FXCollections.observableArrayList();
    private static ObservableList <String> registeredMultiplier = FXCollections.observableArrayList();
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
    public ListView damageMultiplierOutput;
    public CustomTextField shieldAndEpicInput;
    public Button addShield;
    public ListView shieldMultiplierOutput;



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
            float newMultiplier = Float.valueOf(valueStr)/100;
            enemy.addMultiplier(newMultiplier);
            updateMultiplierOutput(name, valueStr);
            damageMultiplierNameInput.setText("");
            damageMultiplierValueInput.setText("");
        }catch (NumberFormatException numberFormatException){
            infoLabel.setText("Format du multiplicateur incorrect. Entrez un nombre");
        }
    }

    private void updateMultiplierOutput(String name, String value){

        String newMultiplier = name + " <> " + value +"%";
        registeredMultiplier.add(newMultiplier);
        damageMultiplierOutput.setItems(registeredMultiplier);
    }

    public void removeDamageMultiplier(ActionEvent actionEvent) {
        String multiplierToRemove = (String) damageMultiplierOutput.getSelectionModel().getSelectedItem();
        float reversedMultiplier = retrieveMultiplier(multiplierToRemove);
        enemy.addMultiplier(reversedMultiplier);
        registeredMultiplier.remove(multiplierToRemove);
    }

    private float retrieveMultiplier(String multiplierExpression){
        String multiplier = multiplierExpression.replaceAll("[^0-9]", "");
        float floatMultiplier = 1/(Float.parseFloat(multiplier)/100);
        return floatMultiplier;
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

    public void resetAllValues(ActionEvent actionEvent) {
        infoLabel.setText("");
        resetSynchro();
        resetDamages();
        resetSpellButtons();
        resetEnemyResistances();
        resetMultipliers();
        resetShieldsAndEpics();
    }

    public void resetSynchro(){
        synchro = new Synchro();
        synchroLvl3.setSelected(true);
        boost.setText("0");
        resetSpellButtons();
    }

    public void resetDamages(){
        rangedDamages.setText("de 0 à 0");
        meleeDamages.setText("de 0 à 0");
    }

    public void resetEnemyResistances(){
        resFixes.setText("0");
        resPercentage.setText("0");
    }

    public void resetMultipliers(){
        registeredMultiplier.removeAll(registeredMultiplier);
        damageMultiplierNameInput.setText("");
        damageMultiplierValueInput.setText("");
        enemy.setDamageMultiplier(1);
    }

    public void resetShieldsAndEpics(){

        registeredShieldsAndEpics.removeAll(registeredShieldsAndEpics);
        shieldAndEpicInput.setText("");
        enemy.setShieldAndEpics(new HashMap<String, ShieldAndEpic>());
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
            int rangedDamagesMin = calculateRangedDamagesMin();
            int rangedDamagesMax = calculateRangedDamagesMax();
            int meleeDamagesMin = calculateMeleeDamagesMin();
            int meleeDamagesMax = calculateMeleeDamagesMax();
            displayRangedDamages(rangedDamagesMin, rangedDamagesMax);
            displayMeleeDamages(meleeDamagesMin, meleeDamagesMax);
        } catch (InvalidBoostException invalidBoostFormat) {
            resetAllValues(actionEvent);
            infoLabel.setText(invalidBoostFormat.getMessage());
        }catch (Exception e){
            infoLabel.setText("Format invalide. Merci de taper un nombre.");
        }
    }

        private void displayRangedDamages(int rangedDamageMin, int rangedDamageMax){
        if (rangedDamageMin < rangedDamageMax){
            rangedDamages.setText("de " + rangedDamageMin + " à " + rangedDamageMax);
        }else {
            rangedDamages.setText("de " + rangedDamageMax + " à " + rangedDamageMin);
        }
        }

    private void displayMeleeDamages(int meleeDamageMin, int meleeDamageMax){
        if (meleeDamageMin < meleeDamageMax){
            meleeDamages.setText("de " + meleeDamageMin + " à " + meleeDamageMax);
        }else {
            meleeDamages.setText("de " + meleeDamageMax + " à " + meleeDamageMin);
        }
    }

    public int calculateRangedDamagesMin() {
        float rangedMultiplier = enemy.getTotalRangedMultiplierMin();
        return calculateDamages(rangedMultiplier);
    }

    public int calculateRangedDamagesMax() {
        float rangedMultiplier = enemy.getTotalRangedMultiplierMax();
        return calculateDamages(rangedMultiplier);
    }

    public int calculateMeleeDamagesMin(){
        float meleeMultiplier = enemy.getTotalMeleeMultiplierMin();
        return calculateDamages(meleeMultiplier);
    }

    public int calculateMeleeDamagesMax(){
        float meleeMultiplier = enemy.getTotalMeleeMultiplierMax();
        return calculateDamages(meleeMultiplier);
    }

    public int calculateDamages(float meleeOrRangeMultiplier){
        float boost = synchro.getBoost();
        float baseDamage = synchro.getBaseDamage();
        float airRes = enemy.getAirRes();
        float percentageAirRes = enemy.getPercentageAirRes();
        float multiplier = enemy.getDamageMultiplier();
        float totalDamage = (baseDamage - airRes) * (1-(percentageAirRes/100)) * multiplier * ((boost/100)-1) * meleeOrRangeMultiplier;
        int roundedTotalDamage = Math.round(totalDamage);
        if (roundedTotalDamage < 0){
            return 0;
        }else{
            return roundedTotalDamage;
        }
    }

    public void addShieldMultiplier(ActionEvent actionEvent) {
        if (shieldAndEpicInput.getText().isBlank()){
            infoLabel.setText("Tapez le nom d'un item à ajouter");
            return;
        }
        String choice = shieldAndEpicInput.getText();
        if (!allShieldsAndEpics.containsKey(choice)){
            infoLabel.setText("L'objet n'existe pas.");
            return;
        }
        ShieldAndEpic addedShieldOrEpic = allShieldsAndEpics.get(choice);
        if (enemy.getShieldAndEpics().containsKey(choice)) {
            infoLabel.setText("L'item '" + choice + "' est déjà pris en compte.");
        } else {
            enemy.getShieldAndEpics().put(addedShieldOrEpic.getName(), addedShieldOrEpic);
            registeredShieldsAndEpics.add(addedShieldOrEpic.getName());
            shieldMultiplierOutput.setItems(registeredShieldsAndEpics);
        }
        shieldAndEpicInput.setText("");
    }

    public void removeShieldMultiplier(ActionEvent actionEvent) {
        String itemsNameToRemove = (String) shieldMultiplierOutput.getSelectionModel().getSelectedItem();
        registeredShieldsAndEpics.remove(itemsNameToRemove);
        enemy.getShieldAndEpics().remove(itemsNameToRemove);
        shieldAndEpicInput.setText("");
    }

    public void newSynchro(ActionEvent actionEvent) {
        resetSynchro();
    }
}

//TODO: check for the formula if it is working
//TODO: write all items as JSON
//TODO: export setting choice (lvl of synchro)
