package abstracted;

import org.json.JSONObject;
import interfaces.StateManagement;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class StatefulObject  {
    private final String fileName;
    private final StateManagement stateManagement;
    private Map<String, String> dependencyList;
    private Map<String, String> outputPayload;
    private boolean locked;

    public StatefulObject(String fileName, String fileType, StateManagement stateManagement) {
        this.fileName = "C:\\JavaTextGame\\" + fileType + "\\" + fileName + ".json";
        this.locked = false;
        this.stateManagement = stateManagement;
    }

    public boolean isLocked() {
        return locked;
    }

    public Map<String, String> getOutputPayload() {
        return outputPayload;
    }

    public abstract JSONObject toJson();
    public abstract void fromJson(JSONObject fileData);

    public JSONObject read(){
        return stateManagement.read(fileName);
    }

    public void write(JSONObject jsonObject) {
        this.locked = true;

        stateManagement.write(fileName, jsonObject);

        this.locked = false;
    }

    private void setOutputPayload(){
        Map<String, String> hashMapOutput = new HashMap<String, String>();
        JSONObject jsonObject = toJson();
        Iterator<String> keys = jsonObject.keys();

        while(keys.hasNext()) {
            String key = keys.next();
            hashMapOutput.put(key, jsonObject.get(key).toString());
        }

        outputPayload = hashMapOutput;
    }

    public void initialize(){
        fromJson(read());
        setOutputPayload();
    }
}
