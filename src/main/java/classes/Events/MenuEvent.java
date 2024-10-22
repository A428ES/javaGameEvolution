package classes.Events;

import abstracted.Enum.StatefulObjectTypes;
import abstracted.GameTypes.Event;
import classes.EventInstructions;
import classes.GameEntity.Player;
import classes.SaveLoadManagement;
import exception.ExitGameException;
import exception.InvalidChoiceException;
import interfaces.StateManagement;
import java.io.IOException;
import static abstracted.Enum.StatefulObjectTypes.LOCATION;

public class MenuEvent extends Event {
    public MenuEvent(StateManagement stateManagement) {
        super("MenuEvent", stateManagement);
    }

    public void setInputPayload(){
        setInputPayload(getInputManager().getInput().toUpperCase());

        if(!getInputPayload().matches("[a-zA-Z0-9_]+")){
            throw new InvalidChoiceException("Invalid input");
        }

        if(getInputManager().validationRules(getInputPayload())){
            throw new IllegalArgumentException("Provided input does not meet validation rules for event type");
        }
    }

    public EventInstructions eventOutcome() throws IOException {
        outputEventFeed();
        setInputPayload();

        StateManagement stateManagement = getStateManagement();
        SaveLoadManagement saveLoad = stateManagement.getSaveLoadManagement();

        switch(StatefulObjectTypes.valueOf(getInputPayload())){
            case NEW:
                promptAndSetInput("ENTER NEW GAME NAME: ");
                saveLoad.newGame(getInputPayload());

                Player newPlayer = new Player(getInputPayload(), stateManagement);
                newPlayer.setName(getInputPayload());
                newPlayer.write(newPlayer.toJson());

                return new EventInstructions(LOCATION, "deathstar");

            case LOAD:
                saveLoad.listGames();
                promptAndSetInput("ENTER GAME TO LOAD:");

                saveLoad.loadGame(getInputPayload());
                return new EventInstructions(LOCATION, "deathstar");
            case EXIT:
                throw new ExitGameException("closing game");
            default:
                throw new InvalidChoiceException("Invalid");

        }
    }
}
