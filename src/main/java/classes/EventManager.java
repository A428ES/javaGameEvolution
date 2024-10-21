package classes;

import abstracted.Event;
import abstracted.Input;
import abstracted.StatefulObjectTypes;
import classes.Events.BattleEvent;
import classes.Events.LocationEvent;
import classes.Events.MenuEvent;
import classes.Factory.StatefulObjectFactory;
import classes.Output.LocationOutput;
import interfaces.StateManagement;
import static abstracted.StatefulObjectTypes.*;

public class EventManager {
    private Event currentEvent;
    private EventInstructions lastEvent;
    private final StateManagement stateManagement;

    public EventManager(StateManagement stateManagement) {
        this.stateManagement = stateManagement;
    }

    public void setLastEvent(EventInstructions lastEvent) {
        this.lastEvent = lastEvent;
    }

    public EventInstructions getLastEvent() {
        return lastEvent;
    }

    void loadEvent(EventInstructions nextEvent){
        StatefulObjectTypes currentEventType = nextEvent.getNextEventType();
        String currentTarget = nextEvent.getNextEventTarget();

        switch(currentEventType){
            case LOCATION:
                currentEvent = new LocationEvent(stateManagement);
                StatefulObjectFactory location = StatefulObjectFactory.generateFactory(LOCATION);
                currentEvent.loadTarget(location.generateLocation(ACTIVE, currentTarget, stateManagement));

                break;
            case MENU:
                currentEvent = new MenuEvent(stateManagement);

                break;
            case BATTLE:
                currentEvent = new BattleEvent(stateManagement);
                StatefulObjectFactory entity = StatefulObjectFactory.generateFactory(ENTITY);
                currentEvent.loadTarget(entity.generateEntity(NPC, currentTarget, stateManagement));

                break;
            default:
                throw new IllegalArgumentException("Invalid event type");
        }

        currentEvent.loadIOManagers(new Input(), new LocationOutput());
    }

    public EventInstructions playEvent() {
        return currentEvent.eventOutcome();
    }
}
