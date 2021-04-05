package alrapal;

import alrapal.Exceptions.InvalidBoostException;
import alrapal.ImportExport.ImportExportClass;
import alrapal.Objects.Enemy;
import alrapal.Objects.Multiplier;
import alrapal.Objects.ShieldAndEpic;
import alrapal.Objects.Synchro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainWindowController {



    /**Attributes**/
    private ImportExportClass importExportClass = new ImportExportClass();
    private Synchro synchro = new Synchro();
    private Enemy enemy = new Enemy();
    private final static  Map <String , ShieldAndEpic>  allShieldsAndEpics = new HashMap<>();
    private final static ArrayList <String> suggestions = new ArrayList<>();
    private final ObservableList <String> registeredShieldsAndEpics = FXCollections.observableArrayList();
    private final ObservableList <Multiplier> registeredMultiplier = FXCollections.observableArrayList();
    private float importedBase;



   /**Getters**/
    public Synchro getSynchro() {return this.synchro;}
    public Enemy getEnemy() {return this.enemy;}
    public ImportExportClass getImportExportClass(){return this.importExportClass;}
    public float getImportedBase(){return this.importedBase;}

    /**Setters**/
    public void setSynchro(Synchro synchro) {this.synchro = synchro;}
    public void setEnemy(Enemy enemy) {this.enemy = enemy;}
    public void setImportExportClass(ImportExportClass importExportClass){this.importExportClass = importExportClass;}


    /**Initializers**/
    public void initialize(){

        importedBase = importExportClass.importConfig(baseDamageInput, infoLabel);
        synchro.setBaseDamage(importedBase);
        /** Import of shields and epics */
        importExportClass.importItems(allShieldsAndEpics, suggestions);
        /** Binding suggestions and input for autocomplete */
        TextFields.bindAutoCompletion(shieldAndEpicInput,suggestions);

        /** Initialise listeners for all TextFields Inputs */
        baseDamageInput.textProperty().addListener((observable, oldValue, newValue) -> {
           try {
               if (newValue.isBlank()){
                   synchro.setBaseDamage(368);
               }else{
                   synchro.setBaseDamage(Float.parseFloat(newValue));
               }


           }catch (NumberFormatException numberFormatException){
               infoLabel.setText("La base de dommage de la Synchro doit être un nombre.");
           }
        });

        boost.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                infoLabel.setText("");
                if (newValue.isBlank()){ synchro.setBoost(0);}else{
                    synchro.setBoost(Float.parseFloat(newValue));}
            } catch (InvalidBoostException e) {
                infoLabel.setText(e.getMessage());
            } catch (NumberFormatException numberFormatException){infoLabel.setText("Le boost synchro doit être un NOMBRE multiple de 200 (0,200,400,etc...)");}
        });

        resFixes.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (newValue.isBlank()){
                    enemy.setAirRes(0);
                }else{
                    enemy.setAirRes(Float.parseFloat(newValue));}
            }catch (NumberFormatException numberFormatException){infoLabel.setText("Les résistances fixes air doivent être un nombre.");}
        });

        resPercentage.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (newValue.isBlank()){
                    enemy.setPercentageAirRes(0);
                }else{
                    enemy.setPercentageAirRes(Float.parseFloat(newValue));}
            }catch (NumberFormatException numberFormatException){infoLabel.setText("Les résistances % doivent être sous la forme d'un nombre, sans le signe %");}
        });

        /** Connects the registered multipliers to the ListView*/
        damageMultiplierOutput.setItems(registeredMultiplier);

        /** Formats the ListView cells with a checkbox based on the BooleanProperty in the class enemy through the getter activatedProperty()*/
        damageMultiplierOutput.setCellFactory(CheckBoxListCell.forListView(Multiplier::activatedProperty));

    }

    /**Javafx elements**/
    @FXML
    private Button reset;
    @FXML
    private Label rangedDamages;
    @FXML
    private Label meleeDamages;
    @FXML
    private Parent mainRoot;
    @FXML
    private TextField boost;
    @FXML
    private Label infoLabel;
    @FXML
    private Button calculate;
    @FXML
    private TextField resFixes;
    @FXML
    private RadioButton keepOnTop;
    @FXML
    private TextField resPercentage;
    @FXML
    private TextField damageMultiplierNameInput;
    @FXML
    private TextField damageMultiplierValueInput;
    @FXML
    private ListView damageMultiplierOutput;
    @FXML
    private CustomTextField shieldAndEpicInput;
    @FXML
    private Button addShield;
    @FXML
    private ListView shieldMultiplierOutput;
    @FXML
    private TextField baseDamageInput;
    @FXML
    private ToggleButton teleportationButton;
    @FXML
    private ToggleButton RSbutton;
    @FXML
    private ToggleButton frappeButton;
    @FXML
    private ToggleButton engrenageButton;
    @FXML
    private ToggleButton gelureButton;
    @FXML
    private ToggleButton perturbationButton;
    @FXML
    private ToggleButton poussiereButton;
    @FXML
    private ToggleButton souvenirButton;
    @FXML
    private ToggleButton paradoxeButton;
    @FXML
    private ToggleButton failleButton;
    @FXML
    private ToggleButton distortionButton;
    @FXML
    private ToggleButton penduleButton;
    @FXML
    private ToggleButton raulebackButton;
    @FXML
    private ToggleButton instabiliteButton;
    @FXML
    private ToggleButton bouclierButton;
    @FXML
    private ToggleButton desychroButton;
    @FXML
    private ToggleButton fuiteButton;
    @FXML
    private ToggleButton premoButton;
    @FXML
    private ToggleButton remboButton;
    @FXML
    private ToggleButton renvoiButton;

    /**Calculation methods**/
    private int calculateRangedDamagesMin(float totalMultiplier) {
        float rangedMultiplier = enemy.getTotalRangedMultiplierMin();
        return calculateDamages(rangedMultiplier, totalMultiplier);
    }

    private int calculateRangedDamagesMax(float totalMultiplier) {
        float rangedMultiplier = enemy.getTotalRangedMultiplierMax();
        return calculateDamages(rangedMultiplier, totalMultiplier);
    }

    private int calculateMeleeDamagesMin(float totalMultiplier){
        float meleeMultiplier = enemy.getTotalMeleeMultiplierMin();
        return calculateDamages(meleeMultiplier, totalMultiplier);
    }

    private int calculateMeleeDamagesMax(float totalMultiplier){
        float meleeMultiplier = enemy.getTotalMeleeMultiplierMax();
        return calculateDamages(meleeMultiplier, totalMultiplier);
    }

    private int calculateDamages(float meleeOrRangeMultiplier, float totalMultiplier){
        float boost = synchro.getBoost();
        float baseDamage = synchro.getBaseDamage(); 
        float airRes = enemy.getAirRes();
        float percentageAirRes = enemy.getPercentageAirRes();
        double domMinusRes = (baseDamage-airRes)*(1-(percentageAirRes/100));
        double floorDomMinusRes = Math.floor(domMinusRes);
        double floorMultiplier = Math.floor(floorDomMinusRes * totalMultiplier);
        double floorTotal = floorMultiplier * ((boost/100)-1) * meleeOrRangeMultiplier;
        double doubleRoundedTotalDamage = Math.floor(floorTotal);
        int roundedTotalDamage = (int) doubleRoundedTotalDamage;
        if (roundedTotalDamage < 0){
            return 0;
        }else{
            return roundedTotalDamage;
        }
    }

    private float calculateTotalMultiplier(){
        float totalMultiplier = 1;
        for (Multiplier multiplier : registeredMultiplier){
            if (multiplier.activatedProperty().get()){
                totalMultiplier = totalMultiplier * (multiplier.getValue()/100);
            }
        }
        return totalMultiplier;
    }

    /**Settings and options**/
    public void keepOnTop(MouseEvent mouseEvent) {
        Stage stage = (Stage) mainRoot.getScene().getWindow();
        if (keepOnTop.isSelected()){
            stage.setAlwaysOnTop(true);
        }else {
            stage.setAlwaysOnTop(false);
        }
    }

    /**Reset**/
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
        float currentBase = synchro.getBaseDamage();
        synchro = new Synchro();
        synchro.setBaseDamage(currentBase);
        boost.setText("0");
        resetSpellButtons();
        resetDamages();
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


    /** Update values
     * (inputs without buttons are updated in the initialize() through listeners) **/
    public void updateDamageMultiplier(ActionEvent actionEvent){
        String name = damageMultiplierNameInput.getText();
        String valueStr = damageMultiplierValueInput.getText();
        try {
            float newMultiplier = Float.valueOf(valueStr);
            //enemy.addMultiplier(newMultiplier);
            updateMultiplierOutput(name, newMultiplier);
            damageMultiplierNameInput.setText("");
            damageMultiplierValueInput.setText("");
        }catch (NumberFormatException numberFormatException){
            infoLabel.setText("Format du multiplicateur incorrect. Entrez un nombre, sans le signe %");
        }
    }

    private void updateMultiplierOutput(String name, float value){
        Multiplier multiplier = new Multiplier(name,value);
        registeredMultiplier.add(multiplier);
    }

    public void removeDamageMultiplier(ActionEvent actionEvent) {
        Multiplier selectedMultiplier = (Multiplier) damageMultiplierOutput.getSelectionModel().getSelectedItem();
        for (Multiplier multiplier: registeredMultiplier){
            if (selectedMultiplier.equals(multiplier)) {
                enemy.removeMultiplier(selectedMultiplier.getValue());
                registeredMultiplier.remove(selectedMultiplier);
                return;
            }
        }
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

    private int checkSpell(ToggleButton spellButton){
        if (spellButton.isSelected()){
            return +1;
        }else{
            return +0;
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

////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////DISPLAY DAMAGE////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////
    /** Display damage methods **/
    public void displayDamage(ActionEvent actionEvent) {
        infoLabel.setText("");
            float totalMultiplier = calculateTotalMultiplier();
            int rangedDamagesMin = calculateRangedDamagesMin(totalMultiplier);
            int rangedDamagesMax = calculateRangedDamagesMax(totalMultiplier);
            int meleeDamagesMin = calculateMeleeDamagesMin(totalMultiplier);
            int meleeDamagesMax = calculateMeleeDamagesMax(totalMultiplier);
            displayRangedDamages(rangedDamagesMin, rangedDamagesMax);
            displayMeleeDamages(meleeDamagesMin, meleeDamagesMax);
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
}