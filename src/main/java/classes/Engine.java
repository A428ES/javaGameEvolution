package classes;

import abstracted.Event;
import classes.Output.GameOutput;
import interfaces.StateManagement;

import java.util.HashMap;
import java.util.Map;

import static classes.StateManagementFactory.StateTypes;

public class Engine {
    private boolean gameRunning = true;
    private EventManager eventManager;

    public Engine(StateTypes stateType) {
        StateManagement stateManagement = new StateManagementFactory().generateState(stateType);
        GameOutput gameOutput = new GameOutput();
        eventManager = new EventManager(stateManagement);

        Map<String, String> firstEvent = new HashMap<String, String>();

        firstEvent.put("nextEvent", "LocationEvent");
        firstEvent.put("eventTarget", "deathstar");

        eventManager.loadEvent(firstEvent);
    }

    public void eventIntegration(){
        eventManager.getCurrentEvent().ev
    }

    public void engineLoop(){

        while(gameRunning){
            eventIntegration();
        }
    }

}
