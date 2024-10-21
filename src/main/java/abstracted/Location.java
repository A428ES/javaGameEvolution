package abstracted;

import interfaces.StateManagement;
import org.json.JSONException;
import org.json.JSONObject;
import utilities.JsonHelper;

import java.util.List;

public abstract class Location extends StatefulObject {
    private String name;
    private String description;
    private String nextLocation;
    private String previousLocation;
    private List<String> npcList;

    public Location(String fileName, StateManagement stateManagement) {
        super(fileName, "Location", stateManagement);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getNpcList() {
        return npcList;
    }

    public String getNextLocation() {
        return nextLocation;
    }

    public String getPreviousLocation() {
        return previousLocation;
    }

    public JSONObject toJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("description", description);
        jsonObject.put("nextLocation", nextLocation);
        jsonObject.put("previousLocation", previousLocation);
        jsonObject.put("npcList", npcList);

        return jsonObject;
    }

    public void fromJson(JSONObject fileData){
        try {
            name = fileData.getString("name");
            description = fileData.getString("description");
            nextLocation = fileData.getString("nextLocation");
            previousLocation = fileData.getString("previousLocation");
            npcList = JsonHelper.convertJsonArray(fileData.getJSONArray("npcList"));
        } catch (JSONException e) {
            throw new RuntimeException("Incorrect attributes in supplied JSON!");
        }
    }

}
