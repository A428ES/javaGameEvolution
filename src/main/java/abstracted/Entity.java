package abstracted;

import org.json.JSONException;
import org.json.JSONObject;
import utilities.StateManagement;
import java.util.List;

public abstract class Entity extends StatefulObject {
    private String name;
    private int health;
    private int money;
    private String weapon;
    private String location;
    private List<String> inventory;


    public Entity(String fileName) {
        super(fileName, "Entity");
        this.loadJson();
    }

    public List<String> getInventory() {
        return inventory;
    }

    public void setInventory(List<String> inventory) {
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void saveJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", getName());
        jsonObject.put("health", getHealth());
        jsonObject.put("weapon", getWeapon());
        jsonObject.put("location", getLocation());
        jsonObject.put("inventory", getInventory());
        jsonObject.put("money", getMoney());

        StateManagement.writeJson(getFileName(), jsonObject);
    }

    public void loadJson(){
        JSONObject fileData = StateManagement.readJson(getFileName());

        try {
            setName(fileData.getString("name"));
            setHealth(fileData.getInt("health"));
            setLocation(fileData.getString("location"));
            setMoney(fileData.getInt("money"));
            setWeapon(fileData.getString("weapon"));
            setInventory(StateManagement.convertJsonArray(fileData.getJSONArray("inventory")));
        } catch (JSONException e) {
            throw new RuntimeException("Incorrect attributes in supplied JSON!");
        }
    }

    public void attack(){
        if(!isLocked()) {
            confirmAttack();
        }
    }

    public abstract void confirmAttack();
}
