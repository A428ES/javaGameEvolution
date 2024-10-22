package abstracted.GameTypes;

import abstracted.StatefulObject;
import org.json.JSONException;
import org.json.JSONObject;
import interfaces.StateManagement;

public abstract class Item extends StatefulObject {
    private String name;
    private int value;
    private int condition;
    private int modifier;
    private String description;

    public Item(String fileName, StateManagement stateManagement) {
        super(fileName, "Item", stateManagement);
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


    public JSONObject toJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", getName());
        jsonObject.put("value", getValue());
        jsonObject.put("condition", getCondition());
        jsonObject.put("description", getDescription());
        jsonObject.put("modifier", getModifier());

        return jsonObject;
    }

    public void fromJson(JSONObject fileData){
        try{
            name = fileData.getString("name");
            modifier = fileData.getInt("modifier");
            description = fileData.getString("description");
            value = fileData.getInt("value");
            condition = fileData.getInt("condition");
        } catch (JSONException e) {
            throw new RuntimeException("Incorrect attributes in supplied JSON!");
        }
    }

    public abstract void adjustCondition();
    public abstract void adjustValue();
    public abstract void adjustModifier();
}