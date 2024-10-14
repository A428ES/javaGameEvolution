package utilities;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StateManagement {
    public static JSONObject readJson(String filePath){

        byte[] fileBytes = null;

        try {
            fileBytes = Files.readAllBytes(Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String jsonString = new String(fileBytes);

        return new JSONObject(jsonString);
    }

    public static void writeJson(String filePath, JSONObject jsonObject){
        try {
            Files.write(Paths.get(filePath), jsonObject.toString(2).getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> convertJsonArray(JSONArray jsonArray){
        List<String> convertedList = new ArrayList<>();

        for(int i=0;i<jsonArray.length();i++){
            convertedList.add(jsonArray.getString(i));
        }

        return convertedList;
    }
}
