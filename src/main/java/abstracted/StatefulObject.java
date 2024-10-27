package abstracted;

import classes.SaveLoadManagement;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.json.JSONObject;
import interfaces.StateManagement;

import java.util.*;

public abstract class StatefulObject  {
    @JsonIgnore
    private final String fileName;

    @JsonIgnore
    private final StateManagement stateManagement;

    @JsonIgnore
    private boolean locked = false;

    public StatefulObject(String fileName, String fileType, StateManagement stateManagement) {
        SaveLoadManagement loadSaveManagement = stateManagement.getSaveLoadManagement();
        this.stateManagement = stateManagement;

        if(fileType.equals("Event")){
            this.fileName = filePathBuilder(loadSaveManagement.getCoreGamePath(), fileType, fileName);
        } else {
            this.fileName = filePathBuilder(loadSaveManagement.getWorkingFilePath(), fileType, fileName);
        }

        this.initialize();
    }

    private String filePathBuilder(String path, String type, String fileName){
        return path + "\\" + type + "\\" + fileName.toUpperCase() + ".json";
    }

    public String getFileName() {
        return fileName;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public StateManagement getStateManagement() {
        return stateManagement;
    }

    public void initialize(){
        load();
    }

    public void load(){
        this.stateManagement.read(this);
    }

    public void write(){
        this.stateManagement.write(this);
    }
}
