package classes.Factory;

import abstracted.Entity;
import abstracted.Event;
import abstracted.StatefulObjectTypes;
import classes.Events.BattleEvent;
import classes.Events.ItemEvent;
import classes.Events.LocationEvent;
import interfaces.StateManagement;

public class EventFactory extends StatefulObjectFactory {
    @Override
    public Event generateEvent(StatefulObjectTypes eventType, String name, StateManagement stateManagement){
        Event event;

        switch(eventType) {
            case BATTLE:
                event = new BattleEvent(name, stateManagement);
                break;
            case ITEM:
                event = new ItemEvent(name, stateManagement);
                break;
            case LOCATION:
                event = new LocationEvent(name, stateManagement);
                break;
            default:
                throw new IllegalArgumentException("Invalid Event type provided");
        }

        return event;
    }
}
