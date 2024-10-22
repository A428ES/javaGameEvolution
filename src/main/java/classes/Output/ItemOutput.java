package classes.Output;
import abstracted.IO.Output;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ItemOutput extends Output {
    public String fieldPayload(Map<String, String> payloadMap, List<String> payloadFields){
        StringBuilder finalPayload = new StringBuilder();

        for(String item : payloadFields){
            finalPayload.append(kvLineFeed(item.toUpperCase(), payloadMap.get(item)));
        }

        return finalPayload.toString();
    }

    public void display(Map<String, String> item){
        String itemDescOutput = String.format("[The {%s}: %s]\n\n", item.get("name"), item.get("description"));
        String itemPayload = fieldPayload(item, Arrays.asList("value", "condition", "modifier"));

        display(segmentedFeed(itemDescOutput + itemPayload));
    }
}
