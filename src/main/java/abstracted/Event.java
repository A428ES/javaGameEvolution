package abstracted;

import classes.GameEntity.Player;
import interfaces.StateManagement;
import org.json.JSONException;
import org.json.JSONObject;
import utilities.JsonHelper;
import java.util.List;
import java.util.Map;

public abstract class Event extends StatefulObject {
    private String name;
    private String eventText;
    private List<String> inputOptions;
    private Output outputManager;
    private Input inputManager;

    public Input getInputManager() {
        return inputManager;
    }

    public Output getOutputManager() {
        return outputManager;
    }

    public Event(String fileName, StateManagement stateManagement) {
        super(fileName, "Event", stateManagement);
    }

    public String getEventText() {
        return eventText;
    }

    public String getName() {
        return name;
    }

    public List<String> getInputOptions() {
        return inputOptions;
    }

    public JSONObject toJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("eventText", eventText);
        jsonObject.put("inputOptions", inputOptions);

        return jsonObject;
    }

    public void fromJson(JSONObject fileData){
        try{
            name = fileData.getString("name");
            eventText = fileData.getString("eventText");
            inputOptions = JsonHelper.convertJsonArray(fileData.getJSONArray("inputOptions"));
        } catch (JSONException e) {
            throw new RuntimeException("Incorrect attributes in supplied JSON!");
        }
    }

    public void loadIOManagers(Input inputManager, Output outputManager){
        this.inputManager = inputManager;
        this.outputManager = outputManager;
    }

    public abstract Map<String, String> eventOutcome();

    public void loadTarget(Location target){
        throw new UnsupportedOperationException("location load not implemented");
    }

    public void loadTarget(Entity target){
        throw new UnsupportedOperationException("location load not implemented");
    }

    public void setPlayer(Player player){
        throw new UnsupportedOperationException("Player load not implemented");
    }

}
