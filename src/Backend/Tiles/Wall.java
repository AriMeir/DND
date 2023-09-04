package Backend.Tiles;

import Backend.Tile;
import Backend.Tiles.Unit;
import Backend.Tiles.Units.Enemy;
import Backend.Tiles.Units.Player;
import Backend.Utils.Position;

public class Wall extends Tile {

    public Wall(Position position) {
        super('#',position);
    }

    @Override
    public void accept(Unit unit) {

    }
    public boolean isEmpty() {return false;}
    public void visit(Enemy e){}
    public void visit(Player p){}
}
