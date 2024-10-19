package classes;

import classes.Interactive.Player;
import classes.Items.Weapon;
import classes.Output.GameOutput;
import classes.Output.ItemOutput;
import interfaces.StateManagement;

import static classes.StateManagementFactory.StateTypes;

public class Engine {
    private final StateManagement stateManagement;
    private final GameOutput gameOutput;

    public Engine(StateTypes stateType) {
        this.stateManagement = new StateManagementFactory().generateState(stateType);
        this.gameOutput = new GameOutput();
    }

    public void testRun(){
        Player playerOne = new Player("player1", stateManagement);
        Weapon lightSword = new Weapon("lightsaber", stateManagement);
        lightSword.initialize();
        playerOne.initialize();

        gameOutput.setOutput(new ItemOutput());
        gameOutput.display(lightSword.getOutputPayload());
    }

}
