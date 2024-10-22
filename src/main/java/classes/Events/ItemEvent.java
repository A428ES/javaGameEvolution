package classes.Events;

import abstracted.GameTypes.Event;
import abstracted.GameTypes.Location;
import abstracted.Enum.StatefulObjectTypes;
import classes.EventInstructions;
import interfaces.StateManagement;


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
