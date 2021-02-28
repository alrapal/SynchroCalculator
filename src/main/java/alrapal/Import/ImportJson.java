package alrapal.Import;

import alrapal.Objects.ShieldAndEpic;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ImportJson {
    public static void importItems(){
        ArrayList <String> suggestions = new ArrayList<>();
        Map <String, ShieldAndEpic> allShieldsAndEpics = new HashMap<>();
        JSONParser jsonParser = new JSONParser();
        try(FileReader fileReader = new FileReader("alrapal/shieldsAndEpics.json")) {
            Object _itemsToImport = jsonParser.parse(fileReader);
            JSONArray itemsToImport = (JSONArray) _itemsToImport;
        //TODO: method that import all shields and epics and create the object in the map as well as the suggestion list for the  autocomplete based on item names.
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void parseToJava(JSONObject jsonObject){
        String name = (String) jsonObject.get("name");
        //TODO : Continue on parsing
    }

}
