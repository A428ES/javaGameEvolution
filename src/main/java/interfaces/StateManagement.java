package interfaces;

import abstracted.StatefulObject;
import classes.SaveLoadManagement;
import org.json.JSONObject;

public interface StateManagement {
    JSONObject read(String filePath);
    void write(String filePath, JSONObject jsonObject);
    void read(StatefulObject statefulObject);
    void write(StatefulObject statefulObject);

    SaveLoadManagement getSaveLoadManagement();
}
