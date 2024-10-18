package abstracted;

import org.json.JSONObject;
import interfaces.StateManagement;

public abstract class StatefulObject  {
    private final String fileName;
    private boolean locked;
    private final StateManagement stateManagement;

    public StatefulObject(String fileName, String fileType, StateManagement stateManagement) {
        this.fileName = "C:\\JavaTextGame\\" + fileType + "\\" + fileName + ".json";
        this.locked = false;
        this.stateManagement = stateManagement;
    }

    public boolean isLocked() {
        return locked;
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

    public void initialize(){
        fromJson(read());
    }
}
