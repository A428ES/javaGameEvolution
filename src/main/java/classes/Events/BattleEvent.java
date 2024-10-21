package classes.Events;

import abstracted.Entity;
import abstracted.Event;
import abstracted.Location;
import abstracted.StatefulObject;
import classes.GameEntity.Player;
import interfaces.StateManagement;
import java.util.HashMap;
import java.util.Map;

public class BattleEvent extends Event {
    private String inputPayload;
    private Entity target;
    private Player player;

    public Entity getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Entity getTarget() {
        return target;
    }

    public void loadTarget(Entity target) {
        this.target = target;
    }

    public BattleEvent(String fileName, StateManagement stateManagement) {
        super(fileName, stateManagement);
    }

    private String formatEventText() {
        return getEventText().replace("{target}", getTarget().getName());
    }

    public void beginEvent() {
        getOutputManager().display(formatEventText(), getInputOptions().toString());
    }

    public void beginInputEvent() {
        inputPayload = getInputManager().getInput();

        if (!getInputManager().validationRules(inputPayload)) {
            throw new IllegalArgumentException("Provided input does not meet validation rules for event type");
        }
    }

    public Map<String, String> eventOutcome() {
        beginEvent();
        beginInputEvent();
        Map<String, String> returnPayload = new HashMap<String, String>();

        if (inputPayload.equals("escape")) {
            returnPayload.put("nextEvent", "LocationEvent");
            returnPayload.put("eventTarget", getPlayer().getLastLocation());
        }

        return returnPayload;
    }
}

