package abstracted.IO;

import java.util.Map;

public abstract class Output {
    public String kvLineFeed(String value, String key){
        return String.format("[%s: %s]\n", key, value);
    }
    public String segmentedFeed(String printPayload){
        return String.format("\n>>>>>>>>>\n%s\n<<<<<<<<<\n", printPayload);
    }

    public void display(String outputFeed){
        System.out.print(segmentedFeed(outputFeed));
    }

    public void display(Map<String, String> data){

    }

    public void display(String desc, String options){

    }
}
