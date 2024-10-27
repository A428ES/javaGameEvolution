package abstracted.GameTypes;
import abstracted.StatefulObject;
import interfaces.StateManagement;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class Entity extends StatefulObject {
    @JsonProperty("name")
    private String name;

    @JsonProperty("health")
    private int health;

    @JsonProperty("speed")
    private int speed;

    @JsonProperty("strength")
    private int strength;

    @JsonProperty("location")
    private String location;

    @JsonProperty("inventory")
    private Inventory inventory;

    @JsonProperty("money")
    private int money;

    public Entity(String fileName, StateManagement stateManagement) {
        super(fileName, "Entity", stateManagement);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public String getLocation() {
        return location;
    }

    public int getSpeed() {
        return speed;
    }

    public int getStrength() {
        return strength;
    }

    public void takeDamage(int damage) {
        health = health - damage;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
