package classes;

import classes.Interactive.Player;
import classes.Output.GameOutput;
import interfaces.StateManagement;

import static classes.Output.GameOutput.OutputType.DESCRIPTION;
import static classes.StateManagementFactory.StateTypes;

public class Engine {
    private final StateManagement stateManagement;
    private final GameOutput gameOutput;

    public Engine(StateTypes stateType) {
        this.stateManagement = new StateManagementFactory().generateState(stateType);
        gameOutput = new GameOutput();
    }

    public void testRun(){
        Player playerOne = new Player("player1", stateManagement);
        playerOne.initialize();
        gameOutput.outputText(DESCRIPTION, playerOne.getName());
    }

}
