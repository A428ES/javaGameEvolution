package classes.Events;

import abstracted.Event;
import abstracted.Location;
import abstracted.StatefulObjectTypes;
import classes.EventInstructions;
import interfaces.StateManagement;

import java.util.HashMap;
import java.util.Map;

public class ItemEvent extends Event {
    public EventInstructions eventOutcome(){
        return new EventInstructions(StatefulObjectTypes.ITEM, "NOONE");
    }

    public ItemEvent(StateManagement stateManagement) {
        super("ItemEvent", stateManagement);
    }

    private Location target;

    public Location getTarget() {
        return target;
    }

    public void loadTarget(Location target) {
        this.target = target;
    }

}
