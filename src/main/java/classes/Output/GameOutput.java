package classes.Output;

import abstracted.Output;

import java.util.List;



public class GameOutput implements Output {

    public enum OutputType {
        ACTION, DESCRIPTION, BATTLE

    }
    public GameOutput() {
    }

    public String menuOptions(List<String> optionsMenu){
        StringBuilder menuBuilder = new StringBuilder();

        for(int i=0;i<optionsMenu.size();i++){
            menuBuilder.append(i+1)
                        .append(". ")
                        .append(optionsMenu.get(i))
                        .append("\n");
        }

        return menuBuilder.toString();
    }

    public String actionConfirmation(String actionDesc){
        return "[YOU SELECTED: " + actionDesc + " -- CONFIRM YES OR NO]";
    }

    public String descriptionOutput(String description){
        return "[" + description + "]";
    }

    public String battleOutput(String battleStatement){
        return "[BATTLE OUTCOME: " + battleStatement + "]";
    }


    public void outputText(OutputType actionType, String payLoad){
        String printMsg;

        switch(actionType){
            case ACTION:
                printMsg = actionConfirmation(payLoad);
                break;
            case DESCRIPTION:
                printMsg = descriptionOutput(payLoad);
                break;
            case BATTLE:
                printMsg = battleOutput(payLoad);
                break;
            default:
                printMsg = "[No Action Type Detected]";
        }

        System.out.println(printMsg);
    }

    public void outputText(List<String> menuOptions){
        System.out.println(menuOptions(menuOptions));
    }
}
