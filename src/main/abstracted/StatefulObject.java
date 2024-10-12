package src.main.abstracted;

public abstract class StatefulObject {
    private final String fileName;
    private String name;
    private String description;
    boolean locked;

    public StatefulObject(String fileName, boolean locked) {
        this.fileName = fileName;
        this.locked = locked;
    }

    public String getFileName() {
        return fileName;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }
}
