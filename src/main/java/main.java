import classes.Output.GameOutput;

import java.util.ArrayList;
import java.util.List;

import static classes.Output.GameOutput.OutputType.DESCRIPTION;

public class main{
    public static void main(String[] args){
        GameOutput gameOutput = new GameOutput();

        List<String> loadMenu = new ArrayList<String>();
        loadMenu.add("New Game");
        loadMenu.add("Load Game");
        loadMenu.add("Credits");
        loadMenu.add("Exit");

        gameOutput.outputText(DESCRIPTION, "Please make a Game Menu selection");
        gameOutput.outputText(loadMenu);

    }
}