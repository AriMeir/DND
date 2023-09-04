package Backend.Tiles.Units.Players;

import Backend.Tiles.Units.Enemy;
import Backend.Tiles.Units.Player;
import Backend.Utils.NumericGenerator;
import Backend.Utils.WarriorResource;

import java.util.LinkedList;
import java.util.List;

public class Warrior extends Player {

    public WarriorResource warriorResource;
    protected static final int ATTACK_BONUS = 2;
    protected static final int DEFENSE_BONUS = 1;
    protected static final int HEALTH_BONUS = 5;
    protected static final int ABILITY_HEALTH_BONUS = 10;
    public Warrior(String name, int healthCapacity, int attack, int defense, int cooldown) {
        super(name, healthCapacity, attack, defense);
        warriorResource = new WarriorResource(healthCapacity,healthCapacity,cooldown);

    }

    @Override
    public void CastAbility(List<Enemy> e) {
        if(warriorResource.getRemainingCooldown()==0) {
            List<Enemy> closeEnemies = new LinkedList<Enemy>();
            for (Enemy enemy : e) {
                int distance = this.CheckDistance(enemy);
                if (distance < 3)
                    closeEnemies.add(enemy);
            }
            messageCallback.send(this.name+" have have used Avengers Shield"+"\n");
            int index = (int)Math.round(NumericGenerator.getInstance().nextDouble()*(closeEnemies.size()-1));
            if(!closeEnemies.isEmpty())
                closeEnemies.get(index).acceptAbility(this,(int)(Math.round(0.1*warriorResource.getHealthCapacity())));
            warriorResource.castedAbility();
            resource.setCurrentHealth(Math.min((resource.getCurrentHealth()+ABILITY_HEALTH_BONUS*defense),resource.getHealthCapacity()));

        }
        else
            messageCallback.send("ability cool down hasn't reset yet"+"\n");




    }



    @Override
    public void levelUp()
    {
        int attackAdd = ATTACK_BONUS*(level+1);
        int defAdd = DEFENSE_BONUS*(level+1);
        int hpAdd = HEALTH_BONUS*(level+1);
        super.levelUp(defAdd,attackAdd,hpAdd);
        warriorResource.resetCooldown();


    }

    public String tickDescribe() {
        return String.format("%s\t\tCooldown: %d\t\t|\n" +
                "|-------------------------------------------------------|",super.tickDescribe(),warriorResource.getRemainingCooldown());
        //return String.format("%s\t\tLevel: %d\t\tExperience: %d/%d", super.describe(), getLevel(), getExperience(), levelUpRequirement());
    }
    @Override
    public void updateResources(){
        warriorResource.cooldownOnTick();
    }


}
