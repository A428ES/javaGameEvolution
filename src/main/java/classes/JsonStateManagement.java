package classes;
import abstracted.StatefulObject;
import exception.MissingResource;
import interfaces.StateManagement;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

public class JsonStateManagement implements StateManagement {
    SaveLoadManagement saveLoadManagement;

    public JsonStateManagement(SaveLoadManagement saveLoadManagement) {
        this.saveLoadManagement = saveLoadManagement;
    }

    public SaveLoadManagement getSaveLoadManagement() {
        return saveLoadManagement;
    }

    public void setSaveLoadManagement(SaveLoadManagement saveLoadManagement) {
        this.saveLoadManagement = saveLoadManagement;
    }

    public void read(StatefulObject statefulObject){
        ObjectMapper objectMapper = new ObjectMapper();

        File jsonFile = new File(statefulObject.getFileName());
        try{
            ObjectReader objectReader = objectMapper.readerForUpdating(statefulObject);
            objectReader.readValue(jsonFile);
        } catch (IOException e){
            System.err.println(e);
            System.err.println("Messed up");
        }
    }

    public void write(StatefulObject statefulObject){
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(new File(statefulObject.getFileName()), statefulObject);
        } catch (IOException e){
            System.err.println("Messed up");
        }
    }

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
