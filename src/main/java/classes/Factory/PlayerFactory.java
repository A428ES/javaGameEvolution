package classes.Factory;

import abstracted.GameTypes.Entity;
import abstracted.Enum.StatefulObjectTypes;
import classes.GameEntity.NPC;
import classes.GameEntity.Player;
import interfaces.StateManagement;

public class PlayerFactory extends StatefulObjectFactory {
    public Entity generateEntity(StatefulObjectTypes playerType, String name, StateManagement stateManagement){
        switch(playerType) {
            case PLAYER:
                return new Player(name, stateManagement);
            case NPC:
                return new NPC(name, stateManagement);
            default:
                throw new IllegalArgumentException("Invalid Entity type provided");
        }
    }
}
