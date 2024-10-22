package classes.Events;

import abstracted.Enum.BattleChoices;
import abstracted.GameTypes.Entity;
import abstracted.GameTypes.Event;
import classes.EventInstructions;
import classes.GameEntity.Player;
import exception.InvalidChoiceException;
import interfaces.StateManagement;

import static abstracted.Enum.StatefulObjectTypes.BATTLE;
import static abstracted.Enum.StatefulObjectTypes.LOCATION;

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

    public void setInputPayload() {
        inputPayload = getInputManager().getInput();

        if (getInputManager().validationRules(inputPayload)) {
            throw new IllegalArgumentException("Provided input does not meet validation rules for event type");
        }
    }

    public EventInstructions eventOutcome() {
        outputEventFeed();
        promptAndSetInput("");

        switch(BattleChoices.valueOf(inputPayload)){
            case ATTACK:
                return new EventInstructions(BATTLE, inputPayload);
            case ESCAPE:
                return new EventInstructions(LOCATION, getPlayer().getLastLocation());

            default:
                throw new InvalidChoiceException("tst");
        }
    }
}

