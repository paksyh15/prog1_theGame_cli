package thegame;

import thegame.gameElements.*;
import thegame.gameElements.unit.*;

import java.util.Random;

/**
 * Ez a program belépési pontja, a fő metódusa
 */
public class Main {

    public static GameLogic gameLogic = new GameLogic();
    public static BuyProcess buyProcess = new BuyProcess();
    public static UnitCellsPlacementProcess ucpp;
    // TuiHandler létezik

    public static void main(String[] args) {
        TuiHandler.clearSceen();
        // heló, nehézségválasztás
        boolean cont = false;
        while (!cont) {
            int diff = buyProcess.askDifficulty();
            if(diff == -1) {
                System.out.println("Érvénytelen nehézség!");
                continue;
            }
            gameLogic.setDifficulty(diff);
            cont = true;
        }
        System.out.printf("Nehézség beállitva. Kezdöarany: %d\n\n", gameLogic.getPlayer(1).getBalance());
        // mindenféle vásárlásolás
        buyProcess.askChooseBuy();  // nem töröljük mer bot még használhatja
        ucpp = new UnitCellsPlacementProcess(gameLogic.getPlayer(1));
        ucpp.askPlaceAllUnits(gameLogic.getPlayer(1));
        System.out.println("Elhelyezted az összes egységed!");
        // bot vásárlás
        BuyProcessBot bpb = new BuyProcessBot(new Random());
        bpb.doStuff();
        // bot vásárlás ok
        TuiHandler.pressEnterKey();
        TuiHandler.clearSceen();
        // játékos 1 és 2 statok, varázslatok kiírása
        TuiHandler.printBoard(gameLogic.board);
        System.out.println(TextColors.GREEN + "Játékos tulajdonságai:" + TextColors.RESET);
        TuiHandler.printPlayerStats(gameLogic.getPlayer(1));
        System.out.println(TextColors.RED + "Ellenfél tulajdonságai:" + TextColors.RESET);
        TuiHandler.printPlayerStats(gameLogic.getPlayer(2));
        System.out.println(TextColors.GREEN + "Játékos varázslatai:" + TextColors.RESET);
        TuiHandler.printPlayerMagics(gameLogic.getPlayer(1));
        System.out.println(TextColors.RED + "Ellenfél varázslatai:" + TextColors.RESET);
        TuiHandler.printPlayerMagics(gameLogic.getPlayer(2));
        TuiHandler.pressEnterKey("A játék kezdéséhez nyomj ENTER-t! ...");
        // kb full "hand-off" GameLogic-nak
        gameLogic.battle();
    }
}
