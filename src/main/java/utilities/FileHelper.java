package utilities;

import exception.ResourceExistsException;
import org.json.JSONArray;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {
    public static List<String> convertJsonArray(JSONArray jsonArray){
        List<String> convertedList = new ArrayList<>();

        for(int i=0;i<jsonArray.length();i++){
            convertedList.add(jsonArray.getString(i));
        }

        return convertedList;
    }

    public static void copyFolder(Path source, Path target) throws IOException {
        Files.walk(source).forEach(sourcePath -> {
            try {
                Path targetPath = target.resolve(source.relativize(sourcePath));
                Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new ResourceExistsException("resource exists");
            }
        });
    }

    public static boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                // Recursive call to delete files and subdirectories
                deleteDirectory(file);
            }
        }
        // Finally, delete the main directory
        return directoryToBeDeleted.delete();
    }

    public static void listDirectories(String directoryPath) {
        File dir = new File(directoryPath);
        File[] directories = dir.listFiles(File::isDirectory);

        if (directories != null) {
            for (File folder : directories) {
                System.out.println(folder.getName());
            }
        } else {
            System.err.println("Invalid directory or an error occurred.");
        }
    }

}

