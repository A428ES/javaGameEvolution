package classes;

import abstracted.GameTypes.Item;
import classes.GameEntity.Player;
import classes.Items.Medicine;

import java.util.List;

public class ItemSupporter {
    public void listMedicine(Player player){
        List<Item> medicines = player.getInventory().medicineList();

        medicines
                .forEach(medicine ->
                        System.out.printf("Name: %s, Quantity: %d%n",
                                medicine.getName(),
                                medicine.getQuantity())
                );
    }

    public void useMedicine(Player player, String item){
        Item med = player.getInventory().getItem(item);
        int healthIncrease = med.getModifier();
        System.out.println(player.getHealth());
        med.useItem();

        player.increaseHealth(healthIncrease);
        System.out.println(player.getHealth());
        System.out.println(player.getInventory().getItem(item).getQuantity());
        player.write();
    }
}
