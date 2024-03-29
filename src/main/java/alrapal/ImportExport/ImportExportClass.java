package alrapal.ImportExport;

import alrapal.Objects.ShieldAndEpic;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;


public class ImportExportClass {

    private final static String workingDir = System.getProperty("user.dir");
    private final static String fileSeparator = System.getProperty("file.separator");
    private final String filePathBaseSynchro = workingDir + fileSeparator + "SynchroCalculator_Base.txt";

    public void importItems(Map<String, ShieldAndEpic> allShieldsAndEpics, ArrayList<String> suggestions){

        try(InputStream fileReader = this.getClass().getClassLoader().getResourceAsStream("database.json")) {
            assert fileReader != null;
            try(InputStreamReader reader = new InputStreamReader(fileReader)){
                JsonArray itemToImport = (JsonArray) JsonParser.parseReader(reader);
                for (int i = 0; i < itemToImport.size(); i++){
                    JsonObject currentImportedItem = (JsonObject) itemToImport.get(i);
                    ShieldAndEpic importedShieldOrEpic = parseToJava(currentImportedItem);
                    allShieldsAndEpics.put(importedShieldOrEpic.getName(), importedShieldOrEpic);
                    suggestions.add(importedShieldOrEpic.getName());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ShieldAndEpic parseToJava(JsonObject next) {
        String name = next.getAsJsonPrimitive("name").getAsString();
        float meleeMultiplierMin = next.get("meleeMultiplierMin").getAsFloat();
        float meleeMultiplierMax = next.get("meleeMultiplierMax").getAsFloat();
        float rangedMultiplierMin = next.get("rangedMultiplierMin").getAsFloat();
        float rangedMultiplierMax = next.get("rangedMultiplierMax").getAsFloat();
        return new ShieldAndEpic(name, meleeMultiplierMin, meleeMultiplierMax, rangedMultiplierMin, rangedMultiplierMax);
    }

    public void exportConfig(float lastBaseDamage){
        try(FileWriter writer = new FileWriter(filePathBaseSynchro)) {
            int intValue = (int) lastBaseDamage;
            writer.write(String.valueOf(intValue));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public float importConfig(TextField baseDamageInput, Label infoLabel){
        float importedBase_ = 0;
        File baseFile = new File(filePathBaseSynchro);
        try(Scanner scanner = new Scanner(baseFile)) {
            String importedBase = scanner.nextLine();
            importedBase_ = Float.parseFloat(importedBase);
            if (importedBase_ == 368){
                infoLabel.setText("INFO: Utilisation de la base de dommage correspondant au niveau 200");
            }else {
                baseDamageInput.setText(importedBase);
                //synchro.setBaseDamage(newBase);
                infoLabel.setText("INFO: Utilisation de la dernière base de dommage personnalisée: " + importedBase);
            }
        } catch (FileNotFoundException e) {
            infoLabel.setText("INFO: Utilisation de la base de dommage correspondant au niveau 200");
        }
        if (importedBase_ != 0){
        return importedBase_;}else{ return 368;}
    }

}
