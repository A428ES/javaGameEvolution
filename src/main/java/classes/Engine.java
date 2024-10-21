package classes;

import abstracted.StatefulObjectTypes;
import classes.Factory.StateManagementFactory;
import classes.Output.SystemOutput;
import exception.InvalidChoiceException;
import exception.MissingResource;
import interfaces.StateManagement;

import java.util.HashMap;
import java.util.Map;

import static classes.Factory.StateManagementFactory.StateTypes;

public class Engine {
    private boolean gameRunning = true;
    private final EventManager eventManager;
    private final SystemOutput sysOutput;

    public Engine(StateTypes stateType) {
        StateManagement stateManagement = new StateManagementFactory().generateState(stateType);

        sysOutput = new SystemOutput();
        eventManager = new EventManager(stateManagement);

        eventManager.loadEvent(new EventInstructions(StatefulObjectTypes.MENU, "ENGINE"));
    }

    public void resetEvent(String msg){
        sysOutput.display(msg);
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
        }
    }

    public void engineLoop(){

        while(gameRunning){
            eventIntegration();
        }
    }

}
