package classes;

import abstracted.Location;
import interfaces.StateManagement;

public class ActiveLocation extends Location {
    public ActiveLocation(String fileName, StateManagement stateManagement) {
        super(fileName, stateManagement);
    }
}
