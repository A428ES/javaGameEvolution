package src.main.abstracted;

import java.util.List;

public abstract class Entity extends StatefulObject {
    private int health;
    private int money;
    private String weapon;
    private String location;
    private boolean loaded;
    private List<String> inventory;

    public Entity(String fileName, boolean locked) {
        super(fileName, locked);
    }

    public List<String> getInventory() {
        return inventory;
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

    public abstract void load();

    public void attack(){
        if(!isLocked()) {
            confirmAttack();
        }
    }

    public abstract void confirmAttack();
}
