package abstracted;

import classes.SaveLoadManagement;
import org.json.JSONObject;
import interfaces.StateManagement;

import java.util.*;

public abstract class StatefulObject  {
    private final String fileName;
    private final StateManagement stateManagement;
    private Map<String, String> outputPayload;
    private boolean locked;

    public StatefulObject(String fileName, String fileType, StateManagement stateManagement) {
        SaveLoadManagement loadSaveManagement = stateManagement.getSaveLoadManagement();

        this.locked = false;
        this.stateManagement = stateManagement;

        if(fileType.equals("Event")){
            this.fileName = loadSaveManagement.getCoreGamePath() + "\\" + fileType + "\\" + fileName.toUpperCase() + ".json";
        } else {
            this.fileName = loadSaveManagement.getWorkingFilePath() + "\\" + fileType + "\\" + fileName.toUpperCase() + ".json";
        }
        this.initialize();
    }

    public StateManagement getStateManagement() {
        return stateManagement;
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
        Map<String, String> hashMapOutput = new HashMap<>();
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
