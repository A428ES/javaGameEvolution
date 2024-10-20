package classes.Events;

import abstracted.Event;
import interfaces.StateManagement;
import java.util.HashMap;
import java.util.Map;

public class LocationEvent extends Event {

    public LocationEvent(String fileName, StateManagement stateManagement) {
        super(fileName, stateManagement);
    }

    public void beginEvent(){
        getOutputManager().display(getEventText(), getInputOptions().toString());
    }

    public void beginInputEvent(){
        String inputPayload = getInputManager().getInput();

        if(!getInputManager().validationRules(inputPayload)){
            throw new IllegalArgumentException("Provided input does not meet validation rules for event type");
        }
    }

    public Map<String, String> eventOutcome(){
        beginEvent();
        beginInputEvent();

        Map<String, String> returnPayload = new HashMap<String, String>();

        returnPayload.put("nextEvent", "BattleEvent");
        returnPayload.put("eventTarget", "goblin1");

        return returnPayload;
    }
}
