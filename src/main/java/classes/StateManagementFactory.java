package classes;

import interfaces.StateManagement;

public class StateManagementFactory {
    public enum StateTypes {
        JSON, OTHER
    }

    public StateManagement generateState(StateTypes stateType){
        StateManagement stateManagement;

        switch(stateType) {
            case JSON:
                stateManagement = new JsonStateManagement();
                break;
            case OTHER:
                throw new UnsupportedOperationException("StateType OTHER not implemented yet");
            default:
                throw new IllegalArgumentException("Invalid StateType provided");
        }

        return stateManagement;
    }
}
