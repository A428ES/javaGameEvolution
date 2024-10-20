package classes.Factory;

import abstracted.Event;
import abstracted.StatefulObjectTypes;
import classes.Events.BattleEvent;
import classes.Events.ItemEvent;
import classes.Events.LocationEvent;
import interfaces.StateManagement;
import static abstracted.StatefulObjectTypes.*;

public class EventFactory extends StatefulObjectFactory {
    public Event generate(StatefulObjectTypes eventType, String name, StateManagement stateManagement){
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
