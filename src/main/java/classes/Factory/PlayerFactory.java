package classes.Factory;

import abstracted.Entity;
import abstracted.StatefulObject;
import abstracted.StatefulObjectTypes;
import classes.GameEntity.NPC;
import classes.GameEntity.Player;
import interfaces.StateManagement;
import static abstracted.StatefulObjectTypes.*;

public class PlayerFactory extends StatefulObjectFactory {
    public StatefulObject generate(StatefulObjectTypes playerType, String name, StateManagement stateManagement){
        switch(playerType) {
            case PLAYER:
                return new Player(name, stateManagement);
            case NPC:
                return new NPC(name, stateManagement);
            default:
                throw new IllegalArgumentException("Invalid Item type provided");
        }
    }
}
