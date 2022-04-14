package thegame;

import thegame.gameElements.GameLogic;
import thegame.gameElements.BuyProcess;
import thegame.gameElements.TuiHandler;
import thegame.gameElements.UnitCellsPlacementProcess;

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
        ucpp = new UnitCellsPlacementProcess(Main.gameLogic.getPlayer(1));
        ucpp.askPlaceAllUnits(Main.gameLogic.getPlayer(1));
        System.out.println("Elhelyezted az összes egységed!");
        TuiHandler.pressEnterKey();
    }
}
