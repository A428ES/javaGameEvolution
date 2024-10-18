import classes.Engine;
import static classes.StateManagementFactory.StateTypes.JSON;

public class main{
    public static void main(String[] args){
        Engine gameEngine = new Engine(JSON);

        gameEngine.testRun();
    }
}