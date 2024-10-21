package abstracted;

import java.util.Scanner;

public class Input {
    public boolean validationRules(String inputToValidate){
        return inputToValidate.equals("true");
    }

    public String getInput(){
        System.out.print("\nMAKE SELECTION: ");
        return new Scanner(System.in).next();
    }
}
