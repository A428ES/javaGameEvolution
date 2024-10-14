package abstracted;

import org.json.JSONException;
import org.json.JSONObject;
import utilities.StateManagement;

import java.util.List;

public abstract class Location extends StatefulObject {
    private String name;
    private String description;
    private String nextLocation;
    private String previousLocation;
    private List<String> npcList;

    public Location(String fileName) {
        super(fileName, "Location");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNextLocation() {
        return nextLocation;
    }

    public void setNextLocation(String nextLocation) {
        this.nextLocation = nextLocation;
    }

    public String getPreviousLocation() {
        return previousLocation;
    }

    public void setPreviousLocation(String previousLocation) {
        this.previousLocation = previousLocation;
    }

    public List<String> getNpcList() {
        return npcList;
    }

    public void setNpcList(List<String> npcList) {
        this.npcList = npcList;
    }

    public void saveJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", getName());
        jsonObject.put("description", getDescription());
        jsonObject.put("nextLocation", getNextLocation());
        jsonObject.put("previousLocation", getPreviousLocation());
        jsonObject.put("npcList", getNpcList());

        StateManagement.writeJson(getFileName(), jsonObject);
    }

    public void loadJson(){
        JSONObject fileData = StateManagement.readJson(getFileName());

        try {
            setName(fileData.getString("name"));
            setDescription(fileData.getString("description"));
            setNextLocation(fileData.getString("nextLocation"));
            setPreviousLocation(fileData.getString("previousLocation"));

            setNpcList(StateManagement.convertJsonArray(fileData.getJSONArray("npcList")));
        } catch (JSONException e) {
            throw new RuntimeException("Incorrect attributes in supplied JSON!");
        }
    }

}
