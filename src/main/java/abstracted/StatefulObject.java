package abstracted;

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


    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public abstract void loadJson();
    public abstract void saveJson();
}