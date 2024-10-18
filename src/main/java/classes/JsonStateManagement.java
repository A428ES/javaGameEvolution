package classes;
import interfaces.StateManagement;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class JsonStateManagement implements StateManagement {
    public JSONObject read(String filePath){

        byte[] fileBytes = null;

        try {
            fileBytes = Files.readAllBytes(Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String jsonString = new String(fileBytes);

        return new JSONObject(jsonString);
    }

    public void write(String filePath, JSONObject jsonObject){
        try {
            Files.write(Paths.get(filePath), jsonObject.toString(2).getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
