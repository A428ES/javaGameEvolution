package classes.Factory;

import abstracted.GameTypes.Event;
import abstracted.Enum.StatefulObjectTypes;
import classes.Events.EntityEvent;
import classes.Events.ItemEvent;
import classes.Events.LocationEvent;
import classes.Events.MenuEvent;
import interfaces.StateManagement;

public class EventFactory extends StatefulObjectFactory {
    @Override
    public Event generateEvent(StatefulObjectTypes eventType, StateManagement stateManagement){
        Event event;

        switch(eventType) {
            case BATTLE:
                event = new EntityEvent(stateManagement);
                break;
            case ITEM:
                event = new ItemEvent(stateManagement);
                break;
            case LOCATION:
                event = new LocationEvent(stateManagement);
                break;
            case MENU:
                event = new MenuEvent(stateManagement);
                break;
            default:
                throw new IllegalArgumentException("Invalid Event type provided");
        }

        return event;
    }
}
