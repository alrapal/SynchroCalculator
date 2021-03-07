package alrapal.ImportExport;

import alrapal.Objects.ShieldAndEpic;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class ImportItems {
    public static void importItems(Map<String, ShieldAndEpic> allShieldsAndEpics, ArrayList suggestions){
        JSONParser jsonParser = new JSONParser();
        try(FileReader fileReader = new FileReader("src/main/resources/alrapal/shieldsAndEpics.json")) {
            Object _itemsToImport = jsonParser.parse(fileReader);
            JSONArray itemsToImport = (JSONArray) _itemsToImport;
            Iterator iterator = itemsToImport.iterator();
            while (iterator.hasNext()){
                ShieldAndEpic shieldAndEpic = parseToJava((JSONObject) iterator.next());
                allShieldsAndEpics.put(shieldAndEpic.getName(), shieldAndEpic);
                suggestions.add(shieldAndEpic.getName());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static ShieldAndEpic parseToJava(JSONObject jsonObject){
        String name = (String) jsonObject.get("name");
        float meleeMultiplierMin = ((Long) jsonObject.get("meleeMultiplierMin")).floatValue();
        float rangedMultiplierMin = ((Long) jsonObject.get("rangedMultiplierMin")).floatValue();
        float meleeMultiplierMax = ((Long) jsonObject.get("meleeMultiplierMax")).floatValue();
        float rangedMultiplierMax = ((Long) jsonObject.get("rangedMultiplierMax")).floatValue();
        ShieldAndEpic shieldAndEpic = new ShieldAndEpic(name, meleeMultiplierMin, meleeMultiplierMax, rangedMultiplierMin, rangedMultiplierMax);
        return shieldAndEpic;

    }

}
