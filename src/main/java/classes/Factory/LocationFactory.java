package classes.Factory;

import abstracted.Location;
import abstracted.StatefulObjectTypes;
import classes.ActiveLocation;
import interfaces.StateManagement;

public class LocationFactory extends StatefulObjectFactory {
    public Location generateLocation(StatefulObjectTypes itemType, String name, StateManagement stateManagement){
        Location location;

        switch(itemType) {
            case ACTIVE:
                location = new ActiveLocation(name, stateManagement);
                break;
            case NEXT:
            case PREVIOUS:
                throw new UnsupportedOperationException("Location type not implemented yet");
            default:
                throw new IllegalArgumentException("Invalid Item type provided");
        }

        return location;
    }
}
