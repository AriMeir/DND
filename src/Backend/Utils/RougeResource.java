package Backend.Utils;

public class RougeResource extends Resource{
    private int energy;
    private int cost;
    protected static final int MAX_ENERGY = 100;
    protected static final int ENERGY_BONUS = 10;
    public RougeResource(int currentHealth, int healthCapacity , int cost) {
        super(currentHealth, healthCapacity);
        this.energy = MAX_ENERGY;
        this.cost = cost;
    }

    public int getEnergy() { return energy; }
    public int getCost(){return cost;}
    public void setEnergy() { energy = energy-cost; }
    public void energyOnTick() { energy = Math.min((energy+ENERGY_BONUS),MAX_ENERGY); }
    public void resetEnergy() { energy = MAX_ENERGY;}
}
