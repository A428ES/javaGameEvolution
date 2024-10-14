package abstracted;

import org.json.JSONException;
import org.json.JSONObject;
import utilities.StateManagement;

public abstract class Item extends StatefulObject{
    private String name;
    private int value;
    private int condition;
    private int modifier;
    private String description;

    public Item(String fileName) {
        super(fileName, "Item");
        this.loadJson();
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

    public String getDescription() {
        return description;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setModifier(int modifier) {
        this.modifier = modifier;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void saveJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", getName());
        jsonObject.put("value", getValue());
        jsonObject.put("condition", getCondition());
        jsonObject.put("description", getDescription());
        jsonObject.put("modifier", getModifier());

        StateManagement.writeJson(getFileName(), jsonObject);
    }

    public void loadJson(){
        JSONObject fileData = StateManagement.readJson(getFileName());

        try{
            setName(fileData.getString("name"));
            setModifier(fileData.getInt("modifier"));
            setDescription(fileData.getString("description"));
            setValue(fileData.getInt("value"));
            setCondition(fileData.getInt("condition"));
        } catch (JSONException e) {
            throw new RuntimeException("Incorrect attributes in supplied JSON!");
        }
    }

    public abstract void adjustCondition();
    public abstract void adjustValue();
    public abstract void adjustModifier();
}