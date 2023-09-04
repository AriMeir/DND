package Backend.Utils;

public class WarriorResource extends Resource {

    private int abilitycooldown;
    private int remainingCooldown;



    public WarriorResource(int currentHealth, int healthCapacity, int abilitycooldown) {
        super(currentHealth, healthCapacity);
        this.abilitycooldown = abilitycooldown;
        remainingCooldown = 0;

    }
    public int getRemainingCooldown() { return remainingCooldown;}
    public void castedAbility()
    {
        remainingCooldown = abilitycooldown;

    }
    public void cooldownOnTick(){
        if(remainingCooldown>0)
            remainingCooldown=remainingCooldown-1;
    }
    public void resetCooldown()
    {remainingCooldown=0;}
}
