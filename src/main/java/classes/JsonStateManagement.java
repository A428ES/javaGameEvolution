package classes;
import exception.MissingResource;
import interfaces.StateManagement;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class JsonStateManagement implements StateManagement {
    public JSONObject read(String filePath){

        byte[] fileBytes = null;
        Path path = Paths.get(filePath);

        if(!Files.exists(path) || !Files.isRegularFile(path)){
            throw new MissingResource("Resource not found.");
        }

        try {
            fileBytes = Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException("Something else more serious happened");
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
