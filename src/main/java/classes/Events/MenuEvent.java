package classes.Events;

import abstracted.Enum.StatefulObjectTypes;
import abstracted.GameTypes.Event;
import classes.EventInstructions;
import classes.GameEntity.Player;
import exception.ExitGameException;
import exception.InvalidChoiceException;
import interfaces.StateManagement;

import java.io.IOException;

import static abstracted.Enum.StatefulObjectTypes.LOCATION;
import static abstracted.Enum.StatefulObjectTypes.MENU;

public class MenuEvent extends Event {
    private String inputPayload;
    private StateManagement stateManagement;


    public MenuEvent(StateManagement stateManagement) {
        super("MenuEvent", stateManagement);
        this.stateManagement = stateManagement;
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

    public EventInstructions eventOutcome() throws IOException {
        beginEvent();
        beginInputEvent();

        switch(StatefulObjectTypes.valueOf(inputPayload)){
            case NEW:
                beginInputEvent();
                if(!inputPayload.matches("[a-zA-Z0-9_]+")){
                    throw new InvalidChoiceException("Invalid input");
                }

                stateManagement.getSaveLoadManagement().newGame(inputPayload);

                Player newPlayer = new Player(inputPayload, stateManagement);
                newPlayer.setName(inputPayload);
                newPlayer.write(newPlayer.toJson());


                return new EventInstructions(LOCATION, "deathstar");

            case LOAD:
                stateManagement.getSaveLoadManagement().listGames();
                beginInputEvent();
                stateManagement.getSaveLoadManagement().loadGame(inputPayload);
                return new EventInstructions(LOCATION, "deathstar");
            case EXIT:
                throw new ExitGameException("closing game");
            default:
                System.out.println("tests");
                throw new InvalidChoiceException("Invalid");

        }
    }
}
