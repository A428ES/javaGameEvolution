package classes;

import interfaces.StateManagement;
import static classes.StateManagementFactory.StateTypes;

public class Engine {
    private final StateManagement stateManagement;

    public Engine(StateTypes stateType) {
        this.stateManagement = new StateManagementFactory().generateState(stateType);
    }


}
