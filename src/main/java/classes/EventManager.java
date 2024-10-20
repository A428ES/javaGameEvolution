package classes;

import abstracted.Event;
import abstracted.Input;
import abstracted.StatefulObject;
import classes.Events.LocationEvent;
import classes.Factory.StatefulObjectFactory;
import classes.Output.LocationOutput;
import interfaces.StateManagement;
import static abstracted.StatefulObjectTypes.*;

import java.util.Map;

public class EventManager {
    private Event currentEvent;
    private final StateManagement stateManagement;

    public EventManager(StateManagement stateManagement) {
        this.stateManagement = stateManagement;
    }

    void loadEvent(Map<String, String> eventPayload){
        if(eventPayload.get("nextEvent").equals("LocationEvent")){
            currentEvent = new LocationEvent("LocationEvent", stateManagement);

            StatefulObjectFactory location = StatefulObjectFactory.generateFactory(LOCATION);

            currentEvent.loadTarget(location.generate(ACTIVE, eventPayload.get("targetEvent"), stateManagement));
            currentEvent.loadIOManagers(new Input(), new LocationOutput());
        }
    }

    public Map<String, String> playEvent() {
        return currentEvent.eventOutcome();
    }
}
