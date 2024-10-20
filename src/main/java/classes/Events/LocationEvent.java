package classes.Events;

import abstracted.Event;
import abstracted.Input;
import abstracted.Output;
import interfaces.StateManagement;

import java.util.HashMap;
import java.util.Map;

public class LocationEvent extends Event {
    private Output outputManager;
    private Input inputManager;

    public LocationEvent(String fileName, String type, StateManagement stateManagement) {
        super(fileName, type, stateManagement);
    }

    public void beginEvent(){
        outputManager.display(getEventText());
    }

    public void beginInputEvent(){
        outputManager.display(getInputOptions().toString());
        String inputPayload = inputManager.getInput();

        if(!inputManager.validationRules(inputPayload)){
            throw new IllegalArgumentException("Provided input does not meet validation rules for event type");
        }
    }

    public Map<String, String> eventOutcome(){
        beginEvent();

        Map<String, String> returnPayload = new HashMap<String, String>();

        returnPayload.put("nextEvent", "BattleEvent");
        returnPayload.put("eventTarget", "goblin1");

        return returnPayload;
    }
}
