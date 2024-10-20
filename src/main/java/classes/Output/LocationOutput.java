package classes.Output;

import abstracted.Output;

public class LocationOutput extends Output {
    public void display(String desc, String options){
        display(desc + "\n\n YOUR OPTIONS: " + options);
    }
}
