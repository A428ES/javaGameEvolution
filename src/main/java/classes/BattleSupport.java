package classes;

import abstracted.GameTypes.Entity;
import abstracted.GameTypes.Item;
import abstracted.GameTypes.Location;
import abstracted.IO.Output;
import classes.GameEntity.Player;

import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.round;

public class BattleSupport {
    Output outputManager;
    Player player;
    Entity target;
    Location location;

    public BattleSupport(Output outputManager, Player player, Entity npc, Location location) {
        this.outputManager = outputManager;
        this.player = player;
        this.target = npc;
        this.location = location;
    }

    public Output getOutputManager() {
        return outputManager;
    }

    public void outputWeaponNamesAttack(Entity eventTarget, Entity eventAttacker){
        String weaponNames =  eventTarget.getName() +
                " attempts to defend with " +
                eventTarget.getInventory().getActiveWeapon().getName() +
                ", but he's no match for " +
                eventAttacker.getName() +
                " and their mastery of the " +
                eventAttacker.getInventory().getActiveWeapon().getName();

        getOutputManager().display(weaponNames);
    }

    public void outputDamageCalculation(String target, int damage){
        getOutputManager().display(target + " TOOK " + String.valueOf(damage) + " DAMAGE!");
    }

    public void outPutDefeated(String target){
        getOutputManager().display(target + " IS DEFEATED");
    }

    public double calculateRandomizedAdjustedSpeed(int speed, int strength) {
        double adjustedSpeed = speed + (strength * 0.05);
        double randomFactor = ThreadLocalRandom.current().nextDouble(0.8, 1.2);

        return adjustedSpeed * randomFactor;
    }

    public Entity[] determineAttackOrder(){
        double targetSpeed = calculateRandomizedAdjustedSpeed(target.getSpeed(), target.getStrength());
        double playerSpeed = calculateRandomizedAdjustedSpeed(player.getSpeed(), player.getStrength());

        Entity[] attackOrder = new Entity[2];

        if(targetSpeed > playerSpeed){
            attackOrder[0] = target;
            attackOrder[1] = player;
        } else {
            attackOrder[1] = target;
            attackOrder[0] = player;
        }

        return attackOrder;
    }

    public void attackEvent(){
        player.setInBattle(true);
        Entity[] attackOrder = determineAttackOrder();

        damageEvent(attackOrder[0], attackOrder[1]);

        if(target.getHealth() <= 0) {
            removeNpc();
            outPutDefeated(target.getName());

            player.setInBattle(false);
        } else if(player.getHealth() <= 0) {
            outPutDefeated(player.getName());

            player.setInBattle(false);
        } else {
            damageEvent(attackOrder[1], attackOrder[0]);
        }
    }

    public void damageEvent(Entity eventTarget, Entity eventAttacker){
        int damageOutcome = modifierCalculation(eventTarget, eventAttacker);

        if(damageOutcome > 0) {
            outputWeaponNamesAttack(eventTarget, eventAttacker);
            eventTarget.takeDamage(damageOutcome);
            outputDamageCalculation(eventTarget.getName(), damageOutcome);
        } else {
            outputManager.display(eventTarget.getName() + " DODGED THE ATTACK");
        }
    }

    public void removeNpc(){
        location.updateNpcList(target.getName().toUpperCase());
        location.write();
    }

    public int modifierCalculation(Entity eventTarget, Entity eventAttacker){
        Item tArmor = eventTarget.getInventory().getActiveArmor();
        Item aWeapon = eventAttacker.getInventory().getActiveWeapon();

        long damageMax = aWeapon.getModifier() + aWeapon.getCondition();
        damageMax = round(damageMax + eventAttacker.getStrength() * 0.25 + eventAttacker.getSpeed() * 0.15);

        return Math.toIntExact(round(damageMax - (tArmor.getModifier() + tArmor.getCondition() * 1.2) + eventTarget.getStrength()));
    }
}
