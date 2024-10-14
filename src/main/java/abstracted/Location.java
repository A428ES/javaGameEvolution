package abstracted;

import java.util.List;

public abstract class Location extends StatefulObject {
    private String name;
    private String description;
    private Location nextLocation;
    private Location previousLocation;
    private List<Entity> npcList;

    public Location(String fileName, boolean locked) {
        super(fileName, locked);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Location getNextLocation() {
        return nextLocation;
    }

    public void setNextLocation(Location nextLocation) {
        this.nextLocation = nextLocation;
    }

    public Location getPreviousLocation() {
        return previousLocation;
    }

    public void setPreviousLocation(Location previousLocation) {
        this.previousLocation = previousLocation;
    }

    public List<Entity> getNpcList() {
        return npcList;
    }

    public void setNpcList(List<Entity> npcList) {
        this.npcList = npcList;
    }

}
