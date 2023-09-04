package Frontend;

import Backend.Tile;
import Backend.Utils.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameBoard {
    private ArrayList<Tile> tiles = new ArrayList<>();

    public Tile findTile(Position position){
        for (Tile t:tiles) {
            if(t.position.getCol()==position.getCol() && t.position.getRow()==position.getRow())
                return t;
        }
        return null;
    }
    public Position FindPosition(Tile tile){
        for(Tile t:tiles){
            if(t.position.getCol()==tile.position.getCol()&&t.position.getRow()==tile.position.getRow())
                return t.position;
        }
        return null;
    }
    public Integer getIndex(Tile tile){
        for (Tile t:tiles) {
            if(t.position.getCol()==tile.position.getCol()&&t.position.getRow()==tile.position.getRow())
                return tiles.indexOf(t);
        }
        return null;
    }
    public void SwitchPositions(Tile tile1, Tile tile2){
        int index1=getIndex(tile1);
        int index2=getIndex(tile2);
        int temp=index1;
        tiles.set(index1,tile2);
        tiles.set(index2,tile1);
    }

    public void remove(Tile t) { tiles.remove(t);}

    public void add(Tile t ,int index){
        tiles.add(index,t);
    }
    public void add(Tile t ){
        tiles.add(t);
    }

    public void Printall(){
                //tiles.sort(new Backend.TilesComparator());
                for(Tile tile : tiles){
                    if (tile.position.getCol() == 0)
                        System.out.println();
                    System.out.print(tile);
        }
        System.out.println();
    }


}