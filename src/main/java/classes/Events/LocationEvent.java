package classes.Events;

import abstracted.Event;
import abstracted.Location;
import exception.InvalidChoiceException;
import interfaces.StateManagement;
import java.util.HashMap;
import java.util.Map;

public class LocationEvent extends Event {
    private String inputPayload;
    private Location target;

    public Location getTarget() {
        return target;
    }

    public void loadTarget(Location target) {
        this.target = target;
    }

    public LocationEvent(String fileName, StateManagement stateManagement) {
        super(fileName, stateManagement);
    }

    private String formatEventText() {
        return getEventText().replace("{target}", getTarget().getName())
                .replace("{targetDescription}", getTarget().getDescription())
                .replace("{npcList}", getTarget().getNpcList().toString());
    }

    public void beginEvent(){
        getOutputManager().display(formatEventText(), getInputOptions().toString());
    }

    public void beginInputEvent(){
        inputPayload = getInputManager().getInput().toLowerCase();

        if(!getInputOptions().contains(inputPayload) && !getTarget().getNpcList().contains(inputPayload)){
            throw new InvalidChoiceException("Invalid Location action selected");
        }

        if(!getInputManager().validationRules(inputPayload)){
            throw new IllegalArgumentException("Provided input does not meet validation rules for event type");
        }
    }

    public Map<String, String> eventOutcome() {
        beginEvent();
        beginInputEvent();
        Map<String, String> returnPayload = new HashMap<String, String>();

        if (inputPayload.equals("west")) {
            returnPayload.put("nextEvent", "LocationEvent");
            returnPayload.put("eventTarget", getTarget().getNextLocation());
        } else if (inputPayload.equals("east")){
            returnPayload.put("nextEvent", "LocationEvent");
            returnPayload.put("eventTarget", getTarget().getPreviousLocation());
        } else if(getTarget().getNpcList().contains(inputPayload)){
            returnPayload.put("nextEvent", "BattleEvent");
            returnPayload.put("eventTarget", inputPayload);
        }

        return returnPayload;
    }
}
