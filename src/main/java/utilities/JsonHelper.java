package utilities;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class JsonHelper {
    public static List<String> convertJsonArray(JSONArray jsonArray){
        List<String> convertedList = new ArrayList<>();

        for(int i=0;i<jsonArray.length();i++){
            convertedList.add(jsonArray.getString(i));
        }

        return convertedList;
    }
}
