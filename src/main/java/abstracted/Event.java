package abstracted;

import interfaces.StateManagement;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class Event extends StatefulObject {
    private String name;
    private boolean repeatableEvent;
    private boolean eventPassed;
    private String nextEvent;
    private String eventTargetType;
    private String eventTarget;
    private String eventModifier;

    public Event(String fileName, String type, StateManagement stateManagement) {
        super(fileName, type, stateManagement);
    }

    public JSONObject toJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("repeatableEvent", repeatableEvent);
        jsonObject.put("eventPassed", eventPassed);
        jsonObject.put("nextEvent", nextEvent);
        jsonObject.put("eventTargetType", eventTargetType);
        jsonObject.put("eventTarget", eventTarget);
        jsonObject.put("eventModifier", eventModifier);

        return jsonObject;
    }

    public void fromJson(JSONObject fileData){
        try{
            name = fileData.getString("name");
            repeatableEvent = fileData.getBoolean("repeatableEvent");
            eventPassed = fileData.getBoolean("eventPassed");
            nextEvent = fileData.getString("nextEvent");
            eventTargetType = fileData.getString("eventTargetType");
            eventTarget = fileData.getString("eventTarget");
            eventModifier = fileData.getString("eventModifier");
        } catch (JSONException e) {
            throw new RuntimeException("Incorrect attributes in supplied JSON!");
        }
    }
}
