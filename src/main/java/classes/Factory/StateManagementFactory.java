package classes.Factory;

import classes.JsonStateManagement;
import classes.SaveLoadManagement;
import interfaces.StateManagement;

public class StateManagementFactory {
    public enum StateTypes {
        JSON, OTHER
    }

    public StateManagement generateState(StateTypes stateType, SaveLoadManagement saveLoadManagement){
        StateManagement stateManagement;

        switch(stateType) {
            case JSON:
                stateManagement = new JsonStateManagement(saveLoadManagement);
                break;
            case OTHER:
                throw new UnsupportedOperationException("StateType OTHER not implemented yet");
            default:
                throw new IllegalArgumentException("Invalid StateType provided");
        }

        return stateManagement;
    }
}
