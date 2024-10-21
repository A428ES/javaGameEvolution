package classes.Events;

import abstracted.Entity;
import abstracted.Event;
import classes.EventInstructions;
import classes.GameEntity.Player;
import interfaces.StateManagement;

import static abstracted.StatefulObjectTypes.LOCATION;

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

    public BattleEvent(StateManagement stateManagement) {
        super("BattleEvent", stateManagement);
    }

    private String formatEventText() {
        return getEventText().replace("{target}", getTarget().getName());
    }

    public void beginEvent() {
        getOutputManager().display(formatEventText(), getInputOptions().toString());
    }

    public void beginInputEvent() {
        inputPayload = getInputManager().getInput();

        if (getInputManager().validationRules(inputPayload)) {
            throw new IllegalArgumentException("Provided input does not meet validation rules for event type");
        }
    }

    public EventInstructions eventOutcome() {
        beginEvent();
        beginInputEvent();

        if (inputPayload.equals("escape")) {
            return new EventInstructions(LOCATION, getPlayer().getLastLocation());
        }

        throw new IllegalArgumentException("Incorrect");
    }
}

