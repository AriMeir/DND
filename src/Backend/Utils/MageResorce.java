package Backend.Utils;

public class MageResorce extends Resource {
    private int manaPool;
    private int currentMana;
    private int cost;
    public MageResorce(int currentHealth, int healthCapacity, int mana , int cost) {
        super(currentHealth, healthCapacity);
        currentMana = mana/4;
        this.cost = cost;
        manaPool = mana;
    }
    public void setUponLevelUp(int mana)
    {
        manaPool = manaPool+mana;
        currentMana = Math.min((currentMana+manaPool/4),manaPool);
    }
    public int getMana() { return currentMana; }
    public int getCost(){return cost;}
    public void setMana(){ currentMana = currentMana-cost;}
    public void manaOnTick(int level){ currentMana = Math.min((currentMana+level),manaPool); }



}
