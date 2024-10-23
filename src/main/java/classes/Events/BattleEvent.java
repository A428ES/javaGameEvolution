package classes.Events;

import abstracted.Enum.BattleChoices;
import abstracted.Enum.StatefulObjectTypes;
import abstracted.GameTypes.Entity;
import abstracted.GameTypes.Event;
import abstracted.GameTypes.Item;
import abstracted.GameTypes.Location;
import classes.EventInstructions;
import classes.Factory.ItemFactory;
import classes.GameEntity.Player;
import classes.Items.Weapon;
import exception.InvalidChoiceException;
import exception.MissingResource;
import interfaces.StateManagement;

import java.util.concurrent.ThreadLocalRandom;

import static abstracted.Enum.StatefulObjectTypes.*;
import static java.lang.Math.round;

public class BattleEvent extends Event {
    private String inputPayload;
    private Entity target;
    private Player player;
    private Location location;
    private boolean inProgress = false;

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

    public BattleEvent(StateManagement stateManagement) {
        super("BattleEvent", stateManagement);
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

    public void outputWeaponNamesAttack(Entity eventTarget, Entity eventAttacker){
        String weaponNames =  eventTarget.getName() +
                " attempts to defend with " +
                eventTarget.getWeaponEquip().getName() +
                ", but he's no match for " +
                eventAttacker.getName() +
                " and their mastery of the " +
                eventAttacker.getWeaponEquip().getName();

        getOutputManager().display(weaponNames);
    }

    public void outputDamageCalculation(String target, int damage){
        getOutputManager().display(target + " TOOK " + String.valueOf(damage) + " DAMAGE!");
    }

    public void outPutDefeated(String target){
        getOutputManager().display(target + " IS DEFEATED");
    }

    public double calculateRandomizedAdjustedSpeed(int speed, int strength) {
        // Calculate base adjusted speed with minimal strength impact
        double adjustedSpeed = speed + (strength * 0.05);

        // Apply randomness factor (e.g., 0.8 to 1.2) to the adjusted speed
        double randomFactor = ThreadLocalRandom.current().nextDouble(0.8, 1.2);

        // Final randomized adjusted speed
        return adjustedSpeed * randomFactor;
    }

    public void attackEvent(){
        inProgress = true;

        double targetSpeed = calculateRandomizedAdjustedSpeed(target.getSpeed(), target.getStrength());
        double playerSpeed = calculateRandomizedAdjustedSpeed(player.getSpeed(), player.getStrength());

        Entity goFirst;
        Entity goSecond;

        if(targetSpeed > playerSpeed){
            goFirst = target;
            goSecond = player;
        } else {
            goFirst = player;
            goSecond = target;
        }

        damageEvent(goSecond, goFirst);

        if(target.getHealth() <= 0) {
            removeNpc();
            outPutDefeated(target.getName());

            inProgress = false;
        } else if(player.getHealth() <= 0) {
            outPutDefeated(player.getName());

            inProgress = false;
        } else {
            damageEvent(goFirst, goSecond);
        }
    }
    
    public void damageEvent(Entity eventTarget, Entity eventAttacker){
        int damageOutcome = modifierCalculation(eventTarget, eventAttacker);

        outputWeaponNamesAttack(eventTarget, eventAttacker);
        eventTarget.takeDamage(damageOutcome);
        outputDamageCalculation(eventTarget.getName(), damageOutcome);
    }

    public void removeNpc(){
        location.updateNpcList(target.getName().toUpperCase());
        location.write(location.toJson());
    }

    public int modifierCalculation(Entity eventTarget, Entity eventAttacker){
        Item tArmor = eventTarget.getArmorEquip();
        Item aWeapon = eventAttacker.getWeaponEquip();

        long damageMax = aWeapon.getModifier() + aWeapon.getCondition();
        damageMax = round(damageMax + eventAttacker.getStrength() * 0.25 + eventAttacker.getSpeed() * 0.15);

        return Math.toIntExact(round(damageMax - (tArmor.getModifier() + tArmor.getCondition() * 1.2) + eventTarget.getStrength()));
    }

    public EventInstructions eventOutcome() {
        outputEventFeed(formatEventText());

        do{
            setInputPayload();

            switch(BattleChoices.valueOf(inputPayload)){
                case ATTACK:
                    attackEvent();
                    break;
                case ESCAPE:
                    inProgress = false;
                    break;
                default:
                    throw new InvalidChoiceException("tst");
            }
        } while(inProgress);

        return new EventInstructions(LOCATION, getPlayer().getLocation());
    }
}