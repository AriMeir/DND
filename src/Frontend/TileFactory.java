package Frontend;

import Backend.Tiles.Units.Enemies.Monster;
import Backend.Tiles.Units.Enemies.Trap;
import Backend.Tiles.Units.Enemy;
import Backend.Tiles.Units.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class TileFactory {
    private List<Supplier<Player>> playersList;
    public Map<Character, Supplier<Enemy>> enemiesMap;
    private Player selected;

    public TileFactory(){
        playersList = initPlayers();
        enemiesMap = initEnemies();
    }

    private Map<Character, Supplier<Enemy>> initEnemies() {
        List<Supplier<Enemy>> enemies = Arrays.asList(
                () -> new Monster('s', "Lannister Solider", 80, 8, 3,25, 3),
                () -> new Monster('k', "Lannister Knight", 200, 14, 8, 50,   4),
                () -> new Monster('q', "Queen's Guard", 400, 20, 15, 100,  5),
                () -> new Trap('B', "Bonus Trap", 1, 1, 1, 250,  1, 10),
                () -> new Trap('Q', "Queen's Trap", 250, 50, 10, 100, 3, 10),
                () -> new Monster('z', "Wright", 600, 30, 15,100, 3),
                () -> new Monster('b', "Bear-Wright", 1000, 75, 30, 250,  4),
                () -> new Monster('g', "Giant-Wright",1500, 100, 40,500,   5),
                () -> new Monster('w', "White Walker", 2000, 150, 50, 1000, 6),
                () -> new Trap('D', "Death Trap", 500, 100, 20, 250, 1, 10),
                () -> new Monster('M',"The Mountain",1000,60,25,6,500),
                () -> new Monster('C',"Queen Cersei",100,10,10,1,1000),
                () -> new Monster('K',"Night's King",5000,300,150,8,5000)

        );

        return enemies.stream().collect(Collectors.toMap(s -> s.get().getTile(), Function.identity()));
    }

    private List<Supplier<Backend.Tiles.Units.Player>> initPlayers() {
        return Arrays.asList(
                () -> new Backend.Tiles.Units.Players.Warrior("Jon Snow", 300, 30, 4, 3),
                () -> new Backend.Tiles.Units.Players.Warrior("The Hound", 400, 20, 6, 5),
                () -> new Backend.Tiles.Units.Players.Mage("Melisandre", 100, 5, 1, 300, 30, 15, 5, 6),
                () -> new Backend.Tiles.Units.Players.Mage("Thoros of Myr", 250, 25, 4, 150, 20, 20, 3, 4),
                () -> new Backend.Tiles.Units.Players.Rogue("Arya Stark", 150, 40, 2, 20),
                () -> new Backend.Tiles.Units.Players.Rogue("Bronn", 250, 35, 3, 50)

        );
    }

    public List<Player> listPlayers(){
        return playersList.stream().map(Supplier::get).collect(Collectors.toList());
    }

    //public Backend.Tiles.Units.Enemy produceEnemy(char tile, Backend.Utils.Position position, MessageCallback messageCallback, Backend.Callbacks.DeathCallback enemyDeathCallback) {
     //   ...
    //}

    //public Backend.Tiles.Units.Player producePlayer(int idx){
	//	...
    //}

   // public Backend.Tiles.Empty produceEmpty(Backend.Utils.Position position){
    //    ...
   // }

   // public Backend.Tiles.Wall produceWall(Backend.Utils.Position position){
   //     ...
   // }
}