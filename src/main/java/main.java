import classes.Player;
import classes.Weapon;

import java.util.ArrayList;
import java.util.List;

public class main{
    public static void main(String[] args){
        Player playerOne = new Player("player1");

        List<String> list = new ArrayList<>();
        list.add("lightsaber");
        playerOne.setInventory(list);
        playerOne.saveJson();

        Player playerTwo = new Player("player1");
        System.out.println(playerTwo.getInventory().toString());
    }
}