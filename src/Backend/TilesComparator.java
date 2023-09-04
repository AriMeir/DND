package Backend;

import Backend.Tile;

import java.util.Comparator;

public class TilesComparator implements Comparator<Tile> {
    @Override
    public int compare(Tile o1, Tile o2) {
        return o1.compareTo(o2);
    }
}
