package abstracted.GameTypes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import interfaces.StateManagement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
                .filter(item -> item.getName().equalsIgnoreCase(itemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item not found: " + itemId));
    }

    public List<Item> medicineList() {
        return inventory.stream()
                .filter(item -> item.getType() != null && item.getType().equalsIgnoreCase("MEDICINE")
                && item.getQuantity() > 0)
                .collect(Collectors.toList());
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
