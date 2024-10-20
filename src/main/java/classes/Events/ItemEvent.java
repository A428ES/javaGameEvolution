package classes.Events;

import abstracted.Event;
import abstracted.Location;
import interfaces.StateManagement;

import java.util.HashMap;
import java.util.Map;

public class ItemEvent extends Event {
    public Map<String, String> eventOutcome(){
        return new HashMap<>();
    }

    public ItemEvent(String fileName, StateManagement stateManagement) {
        super(fileName, stateManagement);
    }

    private Location target;

    public Location getTarget() {
        return target;
    }

    public void loadTarget(Location target) {
        this.target = target;
    }

}
