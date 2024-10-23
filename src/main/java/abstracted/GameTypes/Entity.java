package abstracted.GameTypes;

import abstracted.Enum.StatefulObjectTypes;
import abstracted.StatefulObject;
import classes.Factory.ItemFactory;
import classes.Items.Armor;
import classes.Items.Weapon;
import exception.MissingResource;
import org.json.JSONException;
import org.json.JSONObject;
import interfaces.StateManagement;
import utilities.FileHelper;

import java.util.List;

import static abstracted.Enum.StatefulObjectTypes.ARMOR;
import static abstracted.Enum.StatefulObjectTypes.WEAPON;

public abstract class Entity extends StatefulObject {
    private String name;
    private int health;
    private int money;
    private int speed;
    private int strength;
    private String location;
    private String lastLocation;
    private List<String> inventory;
    private Item weaponEquip;
    private Item armorEquip;

    public void setName(String name) {
        this.name = name;
    }

    public String getLastLocation() {
        return lastLocation;
    }

    public Entity(String fileName, StateManagement stateManagement) {
        super(fileName, "Entity", stateManagement);
    }

    public List<String> getInventory() {
        return inventory;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getMoney() {
        return money;
    }

    public String getLocation() {
        return location;
    }

    public void takeDamage(int damage){
        health = health - damage;

        write(toJson());
    }

    public Item getWeaponEquip() {
        return weaponEquip;
    }

    public void setWeaponEquip(String weaponName){
        weaponEquip = itemLoader(WEAPON, weaponName);
    }

    public Item getArmorEquip(){
        return armorEquip;
    }

    public void setArmorEquip(String armorName){
        armorEquip = itemLoader(ARMOR, armorName);
    }

    public boolean validatePossession(String item){
        return getInventory().contains(item);
    }

    public Item itemLoader(StatefulObjectTypes itemType, String item){
        if(!validatePossession(item)){
            throw new MissingResource("Missing weapon");
        }

        return new ItemFactory().generate(itemType, item, getStateManagement());
    }

    public JSONObject toJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", getName());
        jsonObject.put("health", getHealth());
        jsonObject.put("weapon", getWeaponEquip().getName().toLowerCase());
        jsonObject.put("armor", getArmorEquip().getName().toLowerCase());
        jsonObject.put("location", getLocation());
        jsonObject.put("inventory", getInventory());
        jsonObject.put("money", getMoney());
        jsonObject.put("lastLocation", getLastLocation());
        jsonObject.put("speed", getSpeed());
        jsonObject.put("strength", getStrength());

        return jsonObject;
    }


    public int getSpeed() {
        return speed;
    }

    public int getStrength() {
        return strength;
    }

    public void fromJson(JSONObject fileData){
        try {
            name = fileData.getString("name");
            health = fileData.getInt("health");
            location = fileData.getString("location");
            money = fileData.getInt("money");
            speed = fileData.getInt("speed");
            strength = fileData.getInt("strength");
            lastLocation = fileData.getString("lastLocation");

            setInventory(FileHelper.convertJsonArray(fileData.getJSONArray("inventory")));
            setArmorEquip(fileData.getString("armor"));
            setWeaponEquip(fileData.getString("weapon"));
        } catch (JSONException e) {
            throw new RuntimeException("Incorrect attributes in supplied JSON!");
        }
    }

    private void setInventory(List<String> inventory) {
        this.inventory = inventory;
    }

    public void attack(){
        if(!isLocked()) {
            confirmAttack();
        }
    }

    public abstract void confirmAttack();
}
