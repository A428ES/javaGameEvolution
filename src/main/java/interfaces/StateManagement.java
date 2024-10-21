package interfaces;

import org.json.JSONObject;

public interface StateManagement {
    JSONObject read(String filePath);

    void write(String filePath, JSONObject jsonObject);
}
