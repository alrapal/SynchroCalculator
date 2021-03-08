package alrapal.ImportExport;

import alrapal.Objects.ShieldAndEpic;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;


public class ImportClass {
    public void importItems(Map<String, ShieldAndEpic> allShieldsAndEpics, ArrayList suggestions){

        try(InputStream fileReader = this.getClass().getClassLoader().getResourceAsStream("database.json"); InputStreamReader reader = new InputStreamReader(fileReader)){
            JsonArray itemToImport = (JsonArray) JsonParser.parseReader(reader);
            for (int i = 0; i < itemToImport.size(); i++){
                JsonObject currentImportedItem = (JsonObject) itemToImport.get(i);
                ShieldAndEpic importedShieldOrEpic = parseToJava(currentImportedItem);
                allShieldsAndEpics.put(importedShieldOrEpic.getName(), importedShieldOrEpic);
                suggestions.add(importedShieldOrEpic.getName());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
        ShieldAndEpic shieldAndEpic = new ShieldAndEpic(name, meleeMultiplierMin, meleeMultiplierMax, rangedMultiplierMin, rangedMultiplierMax);
        return shieldAndEpic;
    }
}
