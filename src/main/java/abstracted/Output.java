package abstracted;

import classes.Output.GameOutput;

import java.util.List;

public interface Output {
    public abstract void outputText(GameOutput.OutputType actionType, String payLoad);
    public abstract void outputText(List<String> menuOptions);
}
