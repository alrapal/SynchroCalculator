package alrapal;

import alrapal.Exceptions.InvalidBoostException;
import alrapal.ImportExport.ImportExportClass;
import alrapal.Objects.Enemy;
import alrapal.Objects.ShieldAndEpic;
import alrapal.Objects.Synchro;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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



    /**Attributes**/
    private ImportExportClass importExportClass = new ImportExportClass();
    private Synchro synchro = new Synchro();
    private Enemy enemy = new Enemy();
    private final static  Map <String , ShieldAndEpic>  allShieldsAndEpics = new HashMap<>();
    private final static ArrayList <String> suggestions = new ArrayList<>();
    private final ObservableList <String> registeredShieldsAndEpics = FXCollections.observableArrayList();
    private final ObservableList <String> registeredMultiplier = FXCollections.observableArrayList();

   /**Getters**/
    public Synchro getSynchro() {return this.synchro;}
    public Enemy getEnemy() {return this.enemy;}
    public ImportExportClass getImportExportClass(){return this.importExportClass;}

    /**Setters**/
    public void setSynchro(Synchro synchro) {this.synchro = synchro;}
    public void setEnemy(Enemy enemy) {this.enemy = enemy;}
    public void setImportExportClass(ImportExportClass importExportClass){this.importExportClass = importExportClass;}


    /**Initializers**/
    public void initialize(){
        //TODO : Import synchro base from a file (and delete the file ?)
        importExportClass.importConfig(baseDamageInput, infoLabel);
        //Import of shields and epics
        importExportClass.importItems(allShieldsAndEpics, suggestions);
        //Binding suggestions and input for autocomplete
        TextFields.bindAutoCompletion(shieldAndEpicInput,suggestions);

        //Initialise listeners for all TextFields Inputs
        baseDamageInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               try {
                   if (newValue.isBlank()){ synchro.setBaseDamage(368);}
                   else{synchro.setBaseDamage(Float.parseFloat(newValue));}
               }catch (NumberFormatException numberFormatException){
                   infoLabel.setText("La base de dommage de la Synchro doit être un nombre.");
               }
            }
        });

        boost.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    infoLabel.setText("");
                    if (newValue.isBlank()){ synchro.setBoost(0);}else{
                        synchro.setBoost(Float.parseFloat(newValue));}
                } catch (InvalidBoostException e) {
                    infoLabel.setText(e.getMessage());
                } catch (NumberFormatException numberFormatException){infoLabel.setText("Le boost synchro doit être un NOMBRE multiple de 200 (0,200,400,etc...)");}
            }
        });

        resFixes.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    if (newValue.isBlank()){
                        enemy.setAirRes(0);
                    }else{
                        enemy.setAirRes(Float.parseFloat(newValue));}
                }catch (NumberFormatException numberFormatException){infoLabel.setText("Les résistances fixes air doivent être un nombre.");}
            }
        });

        resPercentage.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    if (newValue.isBlank()){
                        enemy.setPercentageAirRes(0);
                    }else{
                        enemy.setPercentageAirRes(Float.parseFloat(newValue));}
                }catch (NumberFormatException numberFormatException){infoLabel.setText("Les résistances % doivent être sous la forme d'un nombre, sans le signe %");}
            }
        });
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
        //updateBaseDamage(baseDamageInput.getText());
        float baseDamage = synchro.getBaseDamage(); 
        float airRes = enemy.getAirRes();
        float percentageAirRes = enemy.getPercentageAirRes();
        float multiplier = enemy.getDamageMultiplier();
        double domMinusRes = (baseDamage-airRes)*(1-(percentageAirRes/100));
        double floorDomMinusRes = Math.floor(domMinusRes);
        double floorMultiplier = Math.floor(floorDomMinusRes * multiplier);
        double floorTotal = floorMultiplier * ((boost/100)-1) * meleeOrRangeMultiplier;
        double doubleRoundedTotalDamage = Math.floor(floorTotal);
        int roundedTotalDamage = (int) doubleRoundedTotalDamage;
        if (roundedTotalDamage < 0){
            return 0;
        }else{
            return roundedTotalDamage;
        }
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
            float newMultiplier = Float.valueOf(valueStr)/100;
            enemy.addMultiplier(newMultiplier);
            updateMultiplierOutput(name, valueStr);
            damageMultiplierNameInput.setText("");
            damageMultiplierValueInput.setText("");
        }catch (NumberFormatException numberFormatException){
            infoLabel.setText("Format du multiplicateur incorrect. Entrez un nombre, sans le signe %");
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
            int rangedDamagesMin = calculateRangedDamagesMin();
            int rangedDamagesMax = calculateRangedDamagesMax();
            int meleeDamagesMin = calculateMeleeDamagesMin();
            int meleeDamagesMax = calculateMeleeDamagesMax();
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