package src.main.abstracted;

public abstract class Item {
    private final String name;
    private int value;
    private int condition;
    private int modifier;
    private String description;

    public Item(String name, int value, int condition, int modifier, String description) {
        this.name = name;
        this.value = value;
        this.condition = condition;
        this.modifier = modifier;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int getCondition() {
        return condition;
    }

    public int getDamage() {
        return modifier;
    }

    public String getDescription() {
        return description;
    }

    public abstract void adjustCondition();
    public abstract void adjustValue();
    public abstract void adjustModifier();
}