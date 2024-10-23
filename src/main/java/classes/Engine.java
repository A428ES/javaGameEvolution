package classes;

import classes.Factory.StateManagementFactory;
import classes.Output.SystemOutput;
import exception.ExitGameException;
import exception.InvalidChoiceException;
import exception.MissingResource;
import exception.ResourceExistsException;
import interfaces.StateManagement;


import java.io.IOException;

import static abstracted.Enum.StatefulObjectTypes.MENU;
import static classes.Factory.StateManagementFactory.StateTypes;

public class Engine {
    private boolean gameRunning = true;
    private final EventManager eventManager;
    private final SystemOutput sysOutput;

    public boolean isGameRunning() {
        return gameRunning;
    }

    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }

    public Engine(StateTypes stateType) {
        SaveLoadManagement saveLoadManagement = new SaveLoadManagement();
        StateManagement stateManagement = new StateManagementFactory().generateState(stateType, saveLoadManagement);

        sysOutput = new SystemOutput();
        eventManager = new EventManager(stateManagement);

        eventManager.loadEvent(new EventInstructions(MENU, "ENGINE"));
        eventManager.setLastEvent(new EventInstructions(MENU, "ENGINE"));
    }

    public void resetEvent(String msg){
        sysOutput.display(msg);
        System.out.println(eventManager.getLastEvent());
        eventManager.loadEvent(eventManager.getLastEvent());
    }

    public void eventIntegration(){
        try {
            EventInstructions lastEvent = eventManager.playEvent();
            eventManager.loadEvent(lastEvent);
            eventManager.setLastEvent(lastEvent);
        } catch(InvalidChoiceException e){
            resetEvent("You made an invalid selection!");
        } catch (MissingResource e){
            resetEvent("System resource not found!");
        } catch (ExitGameException e){
            sysOutput.display("THANK YOU FOR PLAYING! Good-bye!");

            setGameRunning(false);
        } catch (ResourceExistsException e){
            resetEvent("Game already exists");
        } catch (IOException e) {
            resetEvent("I messed up some other way");
        } catch (IllegalArgumentException e){
            resetEvent("Invalid MENU choice");
        }
    }

    public void engineLoop(){

        while(isGameRunning()){
            eventIntegration();
        }
    }

}
