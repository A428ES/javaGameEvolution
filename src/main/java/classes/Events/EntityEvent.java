package classes.Events;

import abstracted.Enum.BattleChoices;
import abstracted.GameTypes.Entity;
import abstracted.GameTypes.Event;
import abstracted.GameTypes.Location;
import classes.BattleSupport;
import classes.EventInstructions;
import classes.GameEntity.Player;
import classes.ItemSupporter;
import interfaces.StateManagement;

import static abstracted.Enum.StatefulObjectTypes.*;
import static java.lang.Math.round;

public class EntityEvent extends Event {
    private String inputPayload;
    private Entity target;
    private Player player;
    private Location location;
    BattleSupport battleHandler;

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    public Entity getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Entity getTarget() {
        return target;
    }

    public void loadTarget(Entity target) {
        this.target = target;
    }

    public EntityEvent(StateManagement stateManagement) {
        super("EntityEvent", stateManagement);
    }

    private String formatEventText() {
        return getEventText().replace("{target}", getTarget().getName());
    }

    public void setInputPayload() {
        inputPayload = getInputManager().getInput();

        if (getInputManager().validationRules(inputPayload)) {
            throw new IllegalArgumentException("Provided input does not meet validation rules for event type");
        }
    }

    public EventInstructions eventOutcome() {
        outputEventFeed(formatEventText());

        if(battleHandler == null){
            battleHandler = new BattleSupport(getOutputManager(), player, target, location);
        }

        do{
            setInputPayload();

            switch(BattleChoices.valueOf(inputPayload)){
                case ATTACK:
                    if(player.getHealth() > 0){
                        battleHandler.attackEvent();
                    } else {
                        getOutputManager().display("YOU CANNOT BATTLE WITH NO HEALTH!");
                    }
                    break;
                case MEDICINE:
                    if(player.getHealth() < 1000){
                        ItemSupporter itemSupport = new ItemSupporter();
                        itemSupport.listMedicine(player);

                        promptAndSetInput("SELECT MEDICINE");

                        itemSupport.useMedicine(player, inputPayload);
                    }
                case ESCAPE:
                    getOutputManager().display("YOU RAN AWAY!");
                    player.setInBattle(false);
                    break;
                default:
                    return eventOutcome();
            }
        } while(player.isInBattle());

        return new EventInstructions(LOCATION, getPlayer().getLocation());
    }
}