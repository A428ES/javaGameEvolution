package classes.Events;

import abstracted.Event;
import abstracted.Input;
import abstracted.Output;
import interfaces.StateManagement;

import java.util.HashMap;
import java.util.Map;

public class BattleEvent extends Event {
    Output outputManager;
    Input inputManager;

    public BattleEvent(String fileName, String type, StateManagement stateManagement) {
        super(fileName, type, stateManagement);
    }

    public void loadIOManagers(Output outputSystem, Input inputSystem){
        outputManager = outputSystem;
        inputManager = inputSystem;
    }

    public Map<String, String> eventOutcome(){
        return new HashMap<>();
    }
}
