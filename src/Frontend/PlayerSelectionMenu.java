package Frontend;

import Backend.Tiles.Units.Player;
import Frontend.Input.ScannerSingleton;

import java.util.List;


public class PlayerSelectionMenu {

    private TileFactory tileFactory;

    public PlayerSelectionMenu() {
        tileFactory = new TileFactory();
    }


    public void printMenu(){
        String s="|------------------------------------------------------------------------------------------------------------|";
        List<Player> playerList = tileFactory.listPlayers();
        System.out.println("|------------------------------------------------------------------------------------------------------------|");
        System.out.println("|                                           Select Your Character                                            |");
        System.out.println("|------------------------------------------------------------------------------------------------------------|");
        for(int i=0;i<playerList.size();i++) {
            if (i == 0)
                System.out.println("|------------------------------------------------Warriors----------------------------------------------------|");
            if (i == 2)
                System.out.println("|-------------------------------------------------Mages------------------------------------------------------|");
            if (i == 4)
                System.out.println("|----------------------------------------- ------Rouges------------------------------------------------------|");

            Player p = playerList.get(i);
            String toprint = "| " + (i+1) + ". " + p.describe();
            System.out.print(toprint);
            if (toprint.length() < s.length()) {
                while (toprint.length()<86) {
                    System.out.print(" ");
                    toprint = toprint + " ";
                }
                if(i==0)
                    System.out.println(" |");
                else if(i==4)
                    System.out.println("   |");
                else
                    System.out.println("  |");


            }
        }
        System.out.println("|------------------------------------------------------------------------------------------------------------|");
        Integer input;
        try {

             input =  ScannerSingleton.getInstance().nextInput();
            GameLevel.getInstance().setPlayer(playerList.get(input-1));
        }
        catch (Exception e){

           System.out.println("you should choose a number between 1-6");
            printMenu();

        }



    }

    public static void main(String[] args) {
        PlayerSelectionMenu k=new PlayerSelectionMenu();
        k.printMenu();
    }

}
