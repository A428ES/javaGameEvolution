package abstracted.GameTypes;

import abstracted.StatefulObject;
import org.json.JSONException;
import org.json.JSONObject;
import interfaces.StateManagement;
import java.util.List;

public abstract class Entity extends StatefulObject {
    private String name;
    private int health;
    private int money;
    private String weapon;
    private String location;
    private String lastLocation;
    private List<String> inventory;

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

    public String getWeapon() {
        return weapon;
    }

    public String getLocation() {
        return location;
    }

    public JSONObject toJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", getName());
        jsonObject.put("health", getHealth());
        jsonObject.put("weapon", getWeapon());
        jsonObject.put("location", getLocation());
        jsonObject.put("inventory", getInventory());
        jsonObject.put("money", getMoney());
        jsonObject.put("lastLocation", getLastLocation());

        return jsonObject;
    }

    public void fromJson(JSONObject fileData){
        try {
            name = fileData.getString("name");
            health = fileData.getInt("health");
            location = fileData.getString("location");
            money = fileData.getInt("money");
            weapon = fileData.getString("weapon");
            lastLocation = fileData.getString("lastLocation");
            //setInventory(StateManagement.convertJsonArray(fileData.getJSONArray("inventory")));
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
