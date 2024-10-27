package classes.GameEntity;

import abstracted.GameTypes.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import interfaces.StateManagement;

public class Player extends Entity {
    @JsonIgnore
    private boolean inBattle;

    public boolean isInBattle() {
        return inBattle;
    }

    public void setInBattle(boolean inBattle) {
        this.inBattle = inBattle;
    }

    public Player(String fileName, StateManagement stateManagement) {
        super("PLAYER", stateManagement);
        inBattle = false;
    }

    public void confirmAttack(){

    }


}
