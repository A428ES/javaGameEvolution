package classes.Factory;

import abstracted.*;
import interfaces.StateManagement;

public abstract class StatefulObjectFactory {
    public static StatefulObjectFactory generateFactory(StatefulObjectTypes objectType){
        switch(objectType) {
            case EVENT:
                return new EventFactory();
            case ENTITY:
                return new PlayerFactory();
            case LOCATION:
                return new LocationFactory();
            case ITEM:
                return new ItemFactory();
            default:
                throw new IllegalArgumentException("Invalid Factory type requested");
        }
    }

    public Location generateLocation(StatefulObjectTypes eventType, String name, StateManagement stateManagement) {
        throw new UnsupportedOperationException("generateLocation not implemented");
    }

    public Item generateItem(StatefulObjectTypes eventType, String name, StateManagement stateManagement) {
        throw new UnsupportedOperationException("generateItem not implemented");
    }

    public Event generateEvent(StatefulObjectTypes eventType, String name, StateManagement stateManagement) {
        throw new UnsupportedOperationException("generateEvent not implemented");
    }

    public Entity generateEntity(StatefulObjectTypes eventType, String name, StateManagement stateManagement) {
        throw new UnsupportedOperationException("generateEntity not implemented");
    }
}