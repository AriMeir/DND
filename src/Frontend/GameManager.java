package Frontend;

import Backend.Tile;
import Backend.Tiles.Units.Enemies.Monster;
import Backend.Tiles.Units.Enemy;
import Backend.Tiles.Units.Player;
import Backend.Tiles.Units.Players.Mage;
import Backend.Tiles.Units.Players.Warrior;
import Backend.Utils.Position;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static Frontend.GameLevel.fileParser;

public class GameManager {
    public static void main(String[] args) {

        //player selection
        Scanner sc = new Scanner(System.in);
        GameLevel gameLevel = GameLevel.getInstance();
        PlayerSelectionMenu menu  = new PlayerSelectionMenu();
        menu.printMenu();


        if (args.length<=0) {
            System.out.print("main is a void");
            return;
        }

        String folder=args[0];
        File levelsDir = new File(folder);
        List<File> levelFiles = null;
        if (levelsDir.exists())
            levelFiles = Arrays.stream(levelsDir.listFiles())
                    .filter(s -> s.getName().matches("^level\\d+\\.txt$"))
                    .collect(Collectors.toList());
        boolean won = true;



        for(int i = 0;i<levelFiles.size() & won;i++) {
            //levels loop

            gameLevel.loadLevel(levelFiles.get(i));
           // GameBoard gameBoard  = gameLevel.getBoard();
            Player player = gameLevel.getPlayer();

            //game loop
            while(player.alive() && gameLevel.enemies.size()>0){
                String input = sc.next();
                gameLevel.tick(input);
            }

            won = player.alive() & gameLevel.enemies.size()== 0;
            if(won & levelFiles.size() == i+1){
                System.out.println("You won!");
            }

        }

//        File levelsDir = new File(folder);
//        List<File> levelFiles = null;
//        if (levelsDir.exists())
//            levelFiles = Arrays.stream(levelsDir.listFiles())
//                    .filter(s -> s.getName().matches("^level\\d+\\.txt$"))
//                    .collect(Collectors.toList());
//        GameBoard board = fileParser.parseLevel(levelFiles.get(0));
//
//        String c ="";
//        Scanner scanner= new Scanner(System.in);
//        board.Printall();
//        Player p=fileParser.player;
//        while(!c.equals("q")) {
//            c=scanner.next();
//            if(c.equals("d")) {
//                System.out.println(p.position);
//                System.out.println(p.position.getRow());
//                Tile t =board.findTile(new Position(p.position.getRow(),p.position.getCol()+1));
//                if(t!= null)
//                    p.SwitchPosition(t);
//                board.SwitchPositions(p,t);
//            }
//            board.Printall();
//
//        }
//        p.setMessageCallback((s)->{System.out.print(s);});
//
//        Player x = new Mage("roobs", 100, 5, 1,300, 30,15, 5,6);
//        x.position = new Position(0,0);
//        x.setMessageCallback((s)->{System.out.print(s);});
//        Enemy e=new Monster('k',"fuck1",200,14,8,4,50);
//        Enemy a=new Monster('s',"fuck2",200,14,8,4,50);
//        Enemy b=new Monster('a',"fuck3",200,14,8,4,50);
//        Enemy c=new Monster('b',"fuck4",200,14,8,4,50);
//        Enemy d=new Monster('d',"fuck5",200,14,8,4,50);
//        Enemy f=new Monster('j',"fuck6",200,14,8,4,50);
//        e.position = new Position(0,2);
//        a.position = new Position(0,1);
//        b.position = new Position(1,0);
//        c.position = new Position(1,1);
//        d.position = new Position(2,0);
//        f.position = new Position(1,3);
//        e.setMessageCallback((s)->{System.out.print(s);});
//        a.setMessageCallback((s)->{System.out.print(s);});
//        b.setMessageCallback((s)->{System.out.print(s);});
//        c.setMessageCallback((s)->{System.out.print(s);});
//        d.setMessageCallback((s)->{System.out.print(s);});
//        f.setMessageCallback((s)->{System.out.print(s);});
//        e.visit(x);
//        List<Enemy> enemies= new LinkedList<Enemy>();
//        enemies.add(e);
//        enemies.add(a);
//        enemies.add(b);
//        enemies.add(c);
//        enemies.add(d);
//        enemies.add(f);
//        x.CastAbility(enemies);
//        f.position.printPosition();
//        f.processStep(x);
//        f.position.printPosition();
//        a.processStep(x);
//        x.CastAbility(enemies);
//        x.CastAbility(enemies);
//        x.CastAbility(enemies);
//        x.CastAbility(enemies);

    }
}
