package abstracted;

import java.util.Map;

public abstract class Output {
    public String kvLineFeed(String value, String key){
        return String.format("[%s: %s]\n", key, value);
    }
    public String segmentedFeed(String printPayload){
        return String.format(">>>>>>>>>\n%s\n<<<<<<<<<", printPayload);
    }

    public void gameOutput(String outputFeed){
        System.out.print(outputFeed);
    }

    public abstract void display(Map<String, String> data);
}
