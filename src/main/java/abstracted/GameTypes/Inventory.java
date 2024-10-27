package abstracted.GameTypes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import interfaces.StateManagement;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    @JsonProperty("activeWeapon")
    String activeWeaponId;

    @JsonProperty("activeArmor")
    String activeArmorId;

    @JsonProperty("inventory")
    List<Item> inventory;

    @JsonIgnore
    StateManagement stateManagement;

    public Inventory() {
    }

    public Inventory(StateManagement stateManagement) {
        this.stateManagement = stateManagement;
        this.inventory = new ArrayList<Item>();
    }

    public Item getItem(String itemId){
        return inventory.stream()
                .filter(item -> item.getName().equalsIgnoreCase(activeWeaponId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item not found: " + activeWeaponId));
    }

    @JsonIgnore
    public Item getActiveWeapon(){
        return getItem(activeWeaponId);
    }

    @JsonIgnore
    public Item getActiveArmor(){
        return getItem(activeArmorId);
    }


}
