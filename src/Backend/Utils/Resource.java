package Backend.Utils;

public class Resource {
    private Integer currentHealth;
    private Integer healthCapacity;
    public Resource(int currentHealth,int healthCapacity){
        this.healthCapacity=healthCapacity;
        this.currentHealth=currentHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getHealthCapacity() {
        return healthCapacity;
    }

    @Override
    public String toString() {
        return this.currentHealth.toString()+"/"+healthCapacity.toString();
    }
    public void setCurrentHealth(int newCurrentHealth){
        currentHealth=newCurrentHealth;
    }
    public void setHealthCapacity(int newHealthCapacity){
        healthCapacity=newHealthCapacity;
    }
}
