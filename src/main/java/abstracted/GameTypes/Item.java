package abstracted.GameTypes;

import classes.Items.Armor;
import classes.Items.Medicine;
import classes.Items.Weapon;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "itemType" // This property will tell Jackson which subclass to instantiate
)

@JsonSubTypes({
        @JsonSubTypes.Type(value = Armor.class, name = "ARMOR"),
        @JsonSubTypes.Type(value = Weapon.class, name = "WEAPON"),
        @JsonSubTypes.Type(value = Medicine.class, name = "MEDICINE")
})

public abstract class Item {
    @JsonProperty("name")
    private String name;

    @JsonProperty("value")
    private int value;

    @JsonProperty("condition")
    private int condition;

    @JsonProperty("modifier")
    private int modifier;

    @JsonProperty("description")
    private String description;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("itemType")
    private String itemType;

    public Item() {
    }

    public String getType() {
        return itemType;
    }

    public void setType(String itemType) {
        this.itemType = itemType;
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

    public int getModifier() {
        return modifier;
    }

    public void useItem(){
        this.quantity = quantity-1;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public abstract void adjustCondition();
    public abstract void adjustValue();
    public abstract void adjustModifier();
}