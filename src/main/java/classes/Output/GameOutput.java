package classes.Output;

import abstracted.Output;

import java.util.Map;

public class GameOutput {
    private Output output;

    public void setOutput(Output output) {
        this.output = output;
    }

    public void display(Map<String, String> data) {
        output.display(data);  // Dynamically handle different output types
    }
}
