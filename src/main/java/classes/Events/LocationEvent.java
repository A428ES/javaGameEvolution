package classes.Events;

import abstracted.GameTypes.Event;
import abstracted.GameTypes.Location;
import abstracted.Enum.LocationChoices;
import classes.EventInstructions;
import exception.InvalidChoiceException;
import interfaces.StateManagement;

import static abstracted.Enum.StatefulObjectTypes.*;

public class LocationEvent extends Event {
    private String inputPayload;
    private Location target;

    public Location getTarget() {
        return target;
    }

    public void loadTarget(Location target) {
        this.target = target;
    }

    public LocationEvent(StateManagement stateManagement) {
        super("LocationEvent", stateManagement);
    }

    private String formatEventText() {
        return getEventText().replace("{target}", getTarget().getName())
                .replace("{targetDescription}", getTarget().getDescription())
                .replace("{npcList}", getTarget().getNpcList().toString());
    }

    public void beginEvent(){
        getOutputManager().display(formatEventText(), getInputOptions().toString());
    }

    public void beginInputEvent(){
        inputPayload = getInputManager().getInput();

        if(!getInputOptions().contains(inputPayload) && !getTarget().getNpcList().contains(inputPayload)){
            throw new InvalidChoiceException("Invalid Location action selected");
        }

        if(getInputManager().validationRules(inputPayload)){
            throw new IllegalArgumentException("Provided input does not meet validation rules for event type");
        }
    }

    public EventInstructions eventOutcome() {
        beginEvent();
        beginInputEvent();

        if(getTarget().getNpcList().contains(inputPayload)){
            return new EventInstructions(BATTLE, inputPayload);
        }

        switch(LocationChoices.valueOf(inputPayload)){
            case WEST:
                return new EventInstructions(LOCATION, getTarget().getNextLocation());
            case EAST:
                return new EventInstructions(LOCATION, getTarget().getPreviousLocation());
            case MENU:
                return new EventInstructions(MENU, "ENGINE");
            default:
                throw new InvalidChoiceException("Invalid choice");
        }
    }
}
