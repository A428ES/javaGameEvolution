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
import static abstracted.Enum.StatefulObjectTypes.MENU;

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
        outputEventFeed(getEventText());
        setInputPayload();

        StateManagement stateManagement = getStateManagement();
        SaveLoadManagement saveLoad = stateManagement.getSaveLoadManagement();
        Player playerLoad;

        switch(StatefulObjectTypes.valueOf(getInputPayload())){
            case NEW:
                promptAndSetInput("ENTER NEW GAME NAME: ");
                saveLoad.newGame(getInputPayload());

                playerLoad = new Player(getInputPayload(), stateManagement);
                playerLoad.setName(getInputPayload());
                playerLoad.write();
                break;
            case LOAD:
                saveLoad.listGames();
                promptAndSetInput("ENTER GAME TO LOAD:");

                saveLoad.loadGame(getInputPayload());
                playerLoad = new Player(getInputPayload(), stateManagement);
                break;
            case DELETE:
                saveLoad.listGames();
                promptAndSetInput("ENTER SAVE TO DELETE: ");

                String toDelete = getInputPayload();
                promptAndSetInput("ARE YOU SURE? (YES/NO)");

                if(getInputPayload().equals("YES")) {
                    saveLoad.deleteSave(toDelete);
                    getOutputManager().display("GAME SAVE SUCCESSFULLY DELETED");
                } else {
                    getOutputManager().display("GAME SAVE DELETION CANCELLED");
                }

                return new EventInstructions(MENU, "ENGINE");
            case EXIT:
                throw new ExitGameException("closing game");
            default:
                throw new InvalidChoiceException("Invalid");

        }

        return new EventInstructions(LOCATION, playerLoad.getLocation());
    }
}
