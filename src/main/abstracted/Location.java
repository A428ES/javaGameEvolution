package src.main.abstracted;

import java.util.List;

public abstract class Location {
    private String name;
    private String description;
    private Location nextLocation;
    private Location previousLocation;
    private final List<Entity> npcList;

    public Location(String name, String description, Location nextLocation, Location previousLocation, List<Entity> npcList) {
        this.name = name;
        this.description = description;
        this.nextLocation = nextLocation;
        this.previousLocation = previousLocation;
        this.npcList = npcList;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Location getNextLocation() {
        return nextLocation;
    }

    public Location getPreviousLocation() {
        return previousLocation;
    }

    public List<Entity> getNpcList() {
        return npcList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNextLocation(Location nextLocation) {
        this.nextLocation = nextLocation;
    }

    public void setPreviousLocation(Location previousLocation) {
        this.previousLocation = previousLocation;
    }

    public abstract void loadLocation();
}
