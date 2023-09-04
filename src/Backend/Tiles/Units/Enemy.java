package Backend.Tiles.Units;

import Backend.Callbacks.MessageCallback;
import Backend.Callbacks.PlayerDeathCallback;
import Backend.Callbacks.VisibilityCallBack;
import Backend.Tiles.Unit;
import Backend.Utils.Position;
import Frontend.Input.InputProvider;

public abstract class Enemy extends Unit {
    int exp;
    protected VisibilityCallBack visibilityCallBack;

    protected Enemy(char tile, String name, int healthCapacity, int attack, int defense,int exp) {
        super(tile, name, healthCapacity, attack, defense);
        this.exp=exp;
    }

    public void initialize(Position position, MessageCallback messageCallback, VisibilityCallBack visibilityCallBack){
        super.initialize(position, messageCallback);
        this.deathCallback = deathCallback;
        this.visibilityCallBack = visibilityCallBack;
        //return this;
    }


    public void processStep(Player p) {

    }

    public void setTile(char newTile) {
        this.tile = newTile;
    }


    public void onDeath(Position position, Player p) {

        messageCallback.send("enemy"+this.name+"has been defeated");
        movementCallback.call(position, this.position);
        p.position =  new Position(this.position.getRow(),this.position.getCol());
        this.position = new Position(position.getRow(),position.getCol());
        deathCallback.call();

    }
    @Override
    public void onDeath(Position p) {
        messageCallback.send("enemy"+this.name+"has been defeated");
        deathCallback.call();
    }

    @Override
    public void visit(Enemy e) { }



    @Override
    public void visit(Player p) {
        messageCallback.send("--------------------------------------------------------------------------------------------------------------------------");
        messageCallback.send(p.name+" have engaged in battle with "+this.name+ "\n");

        int damage = p.attack()-this.defend();
        if(damage>0) {
            resource.setCurrentHealth(resource.getCurrentHealth() - damage);
            messageCallback.send(this.name + " has received " + damage + " damage " );

        }
        else
            messageCallback.send(p.name + " missed the attack against " +this.name);

        if(resource.getCurrentHealth()<=0) {
            onDeath(p.position,p);
            p.gaindExperience(exp);
        }
        else
            messageCallback.send(this.describe());
    }



    @Override
    public void accept(Unit unit) {
        unit.accept(this);
    }

    public void acceptAbility(Player p, int abilityDamage){
        int damage = abilityDamage- defend();
        if(damage>0) {
            resource.setCurrentHealth(resource.getCurrentHealth() - damage);
            messageCallback.send(this.name + " has receive " + damage + " damage " + "\n");
            if(resource.getCurrentHealth()<=0) {
                onDeath(this.position);
                p.gaindExperience(exp);
            }
        }
    }
    public void setVisibilityCallBack(VisibilityCallBack v){this.visibilityCallBack=v;}


}
