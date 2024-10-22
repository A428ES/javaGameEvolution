package classes.GameEntity;

import abstracted.GameTypes.Entity;
import interfaces.StateManagement;

public class Player extends Entity {
    public Player(String fileName, StateManagement stateManagement) {
        super("PLAYER", stateManagement);
    }

    public void confirmAttack(){

    }


}
