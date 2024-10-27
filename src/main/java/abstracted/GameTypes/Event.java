package abstracted.GameTypes;

import abstracted.IO.Input;
import abstracted.IO.Output;
import abstracted.StatefulObject;
import classes.EventInstructions;
import classes.GameEntity.Player;
import com.fasterxml.jackson.annotation.JsonProperty;
import interfaces.StateManagement;
import org.json.JSONException;
import org.json.JSONObject;
import utilities.FileHelper;

import java.io.IOException;
import java.util.List;

public abstract class Event extends StatefulObject {
    @JsonProperty("name")
    private String name;

    @JsonProperty("eventText")
    private String eventText;

    @JsonProperty("inputOptions")
    private List<String> inputOptions;

    private Output outputManager;
    private Input inputManager;
    private String inputPayload;
    private final StateManagement stateManagement;

    public Event(String fileName, StateManagement stateManagement) {
        super(fileName, "Event", stateManagement);
        this.stateManagement = stateManagement;
    }

    public void setInputPayload(String inputPayload) {
        this.inputPayload = inputPayload;
    }

    public String getInputPayload() {
        return inputPayload;
    }

    public StateManagement getStateManagement() {
        return stateManagement;
    }

    public Input getInputManager() {
        return inputManager;
    }

    public Output getOutputManager() {
        return outputManager;
    }

    public String getEventText() {
        return eventText;
    }

    public List<String> getInputOptions() {
        return inputOptions;
    }

    public void promptAndSetInput(String prompt) {
        outputManager.display(prompt);
        setInputPayload();
    }

    public void outputEventFeed(String output){
        outputManager.display(output, getInputOptions().toString());
    }

    public void loadIOManagers(Input inputManager, Output outputManager){
        this.inputManager = inputManager;
        this.outputManager = outputManager;
    }

    public abstract EventInstructions eventOutcome() throws IOException;
    public abstract void setInputPayload();

    public void loadTarget(Location target){ throw new UnsupportedOperationException("location load not implemented"); }
    public void loadTarget(Entity target){
        throw new UnsupportedOperationException("location load not implemented");
    }
    public void setPlayer(Player player){
        throw new UnsupportedOperationException("Player load not implemented");
    }

    public void setLocation(Location location) {
        throw new UnsupportedOperationException("Player load not implemented");
    }
}
