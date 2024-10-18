package interfaces;

import org.json.JSONObject;

public interface StateManagement {
    JSONObject read(String filePath);
    abstract void write(String filePath, JSONObject jsonObject);
}
