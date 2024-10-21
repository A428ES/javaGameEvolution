package classes.Factory;

import abstracted.Entity;
import abstracted.Item;
import abstracted.StatefulObjectTypes;
import classes.Items.Armor;
import classes.Items.Medicine;
import classes.Items.Weapon;
import interfaces.StateManagement;

public class ItemFactory extends StatefulObjectFactory {
    public Item generate(StatefulObjectTypes itemType, String name, StateManagement stateManagement){
        Item item;

        switch(itemType) {
            case ARMOR:
                item = new Armor(name, stateManagement);
                break;
            case MEDICINE:
                item = new Medicine(name, stateManagement);
                break;
            case WEAPON:
                item = new Weapon(name, stateManagement);
                break;
            default:
                throw new IllegalArgumentException("Invalid Item type provided");
        }

        return item;
    }
}
