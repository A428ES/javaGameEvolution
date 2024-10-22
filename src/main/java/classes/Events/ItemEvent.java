package classes.Events;

import abstracted.GameTypes.Event;
import abstracted.GameTypes.Location;
import abstracted.Enum.StatefulObjectTypes;
import classes.EventInstructions;
import interfaces.StateManagement;


public class ItemEvent extends Event {
    private Location target;

    public ItemEvent(StateManagement stateManagement) {
        super("ItemEvent", stateManagement);
    }

    public void setInputPayload(){

    }

    public Location getTarget() {
        return target;
    }

    public void loadTarget(Location target) {
        this.target = target;
    }

    public EventInstructions eventOutcome(){
        return new EventInstructions(StatefulObjectTypes.ITEM, "NOONE");
    }
}
