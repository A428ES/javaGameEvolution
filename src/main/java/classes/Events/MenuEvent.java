package classes.Events;

import abstracted.Event;
import classes.EventInstructions;
import interfaces.StateManagement;
import static abstracted.StatefulObjectTypes.MENU;

public class MenuEvent extends Event {
    private String inputPayload;


    public MenuEvent(StateManagement stateManagement) {
        super("MenuEvent", stateManagement);
    }

    public void loadTarget(Event target) {
    }


    public void beginEvent(){
        getOutputManager().display(getEventText(), getInputOptions().toString());
    }

    public void beginInputEvent(){
        inputPayload = getInputManager().getInput().toUpperCase();

        if(getInputManager().validationRules(inputPayload)){
            throw new IllegalArgumentException("Provided input does not meet validation rules for event type");
        }
    }

    public EventInstructions eventOutcome() {
        beginEvent();
        beginInputEvent();

        return new EventInstructions(MENU, "ENGINE");
    }
}
