package classes;

import abstracted.Event;
import abstracted.Input;
import classes.Events.LocationEvent;
import classes.Output.GameOutput;
import interfaces.StateManagement;

import java.util.Map;

public class EventManager {
    private Event currentEvent;
    private final StateManagement stateManagement;

    public EventManager(StateManagement stateManagement) {
        this.stateManagement = stateManagement;
    }

    void loadEvent(Map<String, String> eventPayload){
        if(eventPayload.get("nextEvent").equals("LocationEvent")){
            currentEvent = new LocationEvent("LocationEvent", "Event", stateManagement);
            currentEvent.loadIOManagers(new Input(), new GameOutput());
        }
    }

    public Map<String, String> playEvent() {
        return currentEvent.eventOutcome();
    }
}
