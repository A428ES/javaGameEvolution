package classes;

import abstracted.Event;
import abstracted.Input;
import abstracted.Location;
import classes.Events.BattleEvent;
import classes.Events.LocationEvent;
import classes.Factory.LocationFactory;
import classes.Factory.StatefulObjectFactory;
import classes.GameEntity.Player;
import classes.Output.LocationOutput;
import interfaces.StateManagement;
import static abstracted.StatefulObjectTypes.*;

import java.nio.file.NoSuchFileException;
import java.util.Map;

public class EventManager {
    private Event currentEvent;
    private Map<String, String> lastEvent;
    private final StateManagement stateManagement;
    private Player playerInstance;

    public void setLastEvent(Map<String, String> lastEvent) {
        this.lastEvent = lastEvent;
    }

    public Map<String, String> getLastEvent() {
        return lastEvent;
    }

    public EventManager(StateManagement stateManagement) {
        this.stateManagement = stateManagement;
    }

    public Event getCurrentEvent() {
        return currentEvent;
    }

    void loadEvent(Map<String, String> eventPayload){
        playerInstance = new Player("player1", stateManagement);

        if(eventPayload.get("nextEvent").equals("LocationEvent")){
            currentEvent = new LocationEvent("LocationEvent", stateManagement);

            StatefulObjectFactory location = StatefulObjectFactory.generateFactory(LOCATION);

            currentEvent.loadTarget(location.generateLocation(ACTIVE, eventPayload.get("eventTarget"), stateManagement));
            currentEvent.loadIOManagers(new Input(), new LocationOutput());
        } else if(eventPayload.get("nextEvent").equals("BattleEvent")){
            currentEvent = new BattleEvent("BattleEvent", stateManagement);

            StatefulObjectFactory entity = StatefulObjectFactory.generateFactory(ENTITY);

            currentEvent.loadTarget(entity.generateEntity(NPC, eventPayload.get("eventTarget"), stateManagement));
            currentEvent.setPlayer(playerInstance);
            currentEvent.loadIOManagers(new Input(), new LocationOutput());
        }
    }

    public Map<String, String> playEvent() {
        return currentEvent.eventOutcome();
    }
}
