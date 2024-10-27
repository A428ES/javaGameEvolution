package abstracted.GameTypes;

import abstracted.StatefulObject;
import interfaces.StateManagement;
import org.json.JSONException;
import org.json.JSONObject;
import utilities.FileHelper;

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

    public void updateNpcList(String entry){
        npcList.remove(entry);
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

}
