package classes;

import exception.InvalidChoiceException;
import utilities.FileHelper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveLoadManagement {
    private final String systemPath = "C:\\javaGameEvolution";
    private final String saveFilePath = systemPath + "\\GameSaves";
    private String workingFilePath;

    public String getCoreGamePath() {
        return systemPath;
    }

    public String getWorkingFilePath() {
        return workingFilePath;
    }

    public void deleteSave(String gameToDelete){
        if(!gameToDelete.matches("[a-zA-Z0-9_]+")){
            throw new InvalidChoiceException("Invalid input");
        }

        FileHelper.deleteDirectory(new File(saveFilePath + "\\" + gameToDelete));

        workingFilePath = null;
    }

    public void loadGame(String game){
        workingFilePath = saveFilePath + "\\" + game;
    }

    public void newGame(String newPlayerName) throws IOException {
        Path template = Paths.get(systemPath + "\\" + "GameSaveTemplate");
        Path newDir = Paths.get(saveFilePath + "\\" + newPlayerName);

        FileHelper.copyFolder(template, newDir);

        loadGame(newPlayerName);
    }

    public void listGames(){
        FileHelper.listDirectories(systemPath + "\\GameSaves");
    }
}
