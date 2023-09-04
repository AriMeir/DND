package Backend.Tiles.Units.Enemies;

import Backend.Tile;
import Backend.Tiles.Units.Enemy;
import Backend.Tiles.Units.Player;
import Backend.Utils.NumericGenerator;
import Backend.Utils.Position;
import Frontend.GameBoard;
import Frontend.GameLevel;

public class Monster extends Enemy {

    int vision;
    public Monster(char tile, String name, int healthCapacity, int attack, int defense,int exp,int vision) {
        super(tile, name, healthCapacity, attack, defense,exp);
        this.vision=vision;
    }
    @Override
    public void processStep(Player p) {
        if(p.position.checkDistance(this.position)<vision) {
            chase(p);
        }
        else{
            GameBoard gameBoard = GameLevel.getInstance().getBoard();
            switch ((int)(NumericGenerator.getInstance().nextDouble()*5)){
                case 1:
                    interact(this.position.getCol()+1,this.position.getRow(),gameBoard);//Left
                case 2:
                    interact(this.position.getCol()-1,this.position.getRow(),gameBoard);//right
                case 3:
                    interact(this.position.getCol(),this.position.getRow()+1,gameBoard);//Up
                case 4:
                    interact(this.position.getCol(),this.position.getRow()-1,gameBoard);//Down
                default:
                    //stay in place
            }
        }


    }
    public void chase(Player p ){
        int yDistance = this.position.getRow() - p.position.getRow();
        int xDistance = this.position.getCol() - p.position.getCol();
        GameBoard gameBoard = GameLevel.getInstance().getBoard();
        if(Math.abs(xDistance)>Math.abs(yDistance))
        {
            if(xDistance>0)
            { interact((this.position.getCol()-1),this.position.getRow(),gameBoard); }
            else
            { interact((this.position.getCol()+1),this.position.getRow(),gameBoard);}
        }
        else
        {
            if(yDistance>0)
            { interact(this.position.getCol(),(this.position.getRow()-1),gameBoard); }
            else
            { interact(this.position.getCol(),(this.position.getRow()+1),gameBoard);}
        }
    }
    private void interact(int colIndex, int rowIndex, GameBoard gameBoard){
        Position oldPosition = this.position;
        Tile tile = gameBoard.findTile(new Position(rowIndex,colIndex));
        tile.visit(this);
        movementCallback.call(oldPosition,this.position);
    }
    @Override
    public void updateResources(){}
}
