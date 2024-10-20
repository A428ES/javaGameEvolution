package classes.Factory;

import abstracted.StatefulObject;
import abstracted.StatefulObjectTypes;
import interfaces.StateManagement;

import static abstracted.StatefulObjectTypes.*;

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

    public abstract StatefulObject generate(StatefulObjectTypes eventType, String name, StateManagement stateManagement);
}