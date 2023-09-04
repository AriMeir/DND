package Backend.Tiles.Units.Players;

import Backend.Tiles.Unit;
import Backend.Tiles.Units.Enemy;
import Backend.Tiles.Units.Player;
import Backend.Utils.MageResorce;
import Backend.Utils.NumericGenerator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Mage extends Player {
    public MageResorce mageResorce;
    private int spellPower;
    private int hitsCount;
    private int abilityRange;
    protected static final int MANA_BONUS = 25;
    protected static final int SPELL_POWER_BONUS = 10;

    public Mage(String name, int healthCapacity, int attack, int defense, int mana, int cost, int spellPower, int hitsCount, int abilityRange)
    {
        super(name, healthCapacity, attack, defense);
        mageResorce = new MageResorce(healthCapacity,healthCapacity,mana, cost);
        this.spellPower = spellPower;
        this.hitsCount = hitsCount;
        this.abilityRange = abilityRange;
    }

    @Override
    public void CastAbility(List<Enemy> e)
    {
        int hits=0;
        if(mageResorce.getMana()>mageResorce.getCost()) {
            List<Enemy> closeEnemies = new LinkedList<Enemy>();
            for (Enemy enemy : e)
            {
                int distance = this.CheckDistance(enemy);
                if (distance <= abilityRange)
                    closeEnemies.add(enemy);
            }
            if(closeEnemies.size()!=0)
                messageCallback.send(this.name+" have used Blizzard, "+"\n");
            for (int i =0; i<closeEnemies.size();i++)
            {
                if(hits<hitsCount) {
                    double coinflip = NumericGenerator.getInstance().nextDouble();
                    if (coinflip > 0.5) {
                        closeEnemies.get(i).acceptAbility(this,spellPower);
                        hits = hits + 1;
                    }
                }
            }
            mageResorce.setMana();
            messageCallback.send(hits+" enemies were hit."+"\n");;
        }
        else{ messageCallback.send(this.name+" doesn't have enough mana"+"\n");}
    }



    @Override
    public void levelUp()
    {
        super.levelUp(0,0,0);
        int mana = MANA_BONUS*level;
        spellPower =spellPower+SPELL_POWER_BONUS*level;
        mageResorce.setUponLevelUp(mana);

    }

    public String tickDescribe() {
        return String.format("%s\t\tMana: %d\t\t|\n" +
                "|-------------------------------------------------------|",super.tickDescribe(),mageResorce.getMana());
        //return String.format("%s\t\tLevel: %d\t\tExperience: %d/%d", super.describe(), getLevel(), getExperience(), levelUpRequirement());
    }
    @Override
    public void updateResources(){
        mageResorce.manaOnTick(level);
    }



}
