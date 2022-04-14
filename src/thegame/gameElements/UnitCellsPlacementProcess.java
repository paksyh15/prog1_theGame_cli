package thegame.gameElements;

import thegame.Main;
import thegame.gameElements.unit.UnitCell;

import java.util.ArrayList;
import java.util.Scanner;

public class UnitCellsPlacementProcess {
    private final Player player;

    public UnitCellsPlacementProcess(Player player) {
        this.player = player;
    }

    public void askPlaceAllUnits(Player player) {

        boolean isUserDone = false;
        int uIn = -1;
        while (!isUserDone) {
            TuiHandler.clearSceen();
            System.out.print("\n");
            TuiHandler.printBoard(Main.gameLogic.board);
            System.out.println("Helyezd el az egységeidet az első két oszlopban!");
            ArrayList<UnitCell> cellsToPlace = new ArrayList<>();
            ArrayList<UnitCell> cellsPlaced = player.getAllPlacedCells();
            for (UnitCell cell : player.ownedCells) {
                if (!cellsPlaced.contains(cell)) {
                    cellsToPlace.add(cell);
                }
            }
            if(cellsToPlace.isEmpty()) {
                isUserDone = true;
                break;
            }
            System.out.println("Válassz egységet!");
            int maxI = 0;
            for (int i = 0; i < cellsToPlace.stream().count(); i++) {
                System.out.printf("[%d] - %dx %s\n",
                        i,
                        cellsToPlace.get(i).amount,
                        cellsToPlace.get(i).unit.name);
                maxI = i;
            }
            System.out.print(": ");
            try {
                Scanner sc = new Scanner(System.in);
                uIn = sc.nextInt();
            } catch (Exception e) {
                continue;
            }
            if (uIn > maxI || uIn < 0) continue;
            int uIndex = uIn;

            System.out.print("Add meg X koordinátát (oszlopszám)\n: ");
            try {
                Scanner sc = new Scanner(System.in);
                uIn = sc.nextInt();
            } catch (Exception e) {
                continue;
            }
            if (uIn > 12 || uIn < 1) {
                System.out.println("Hiba! Érvénytelen koordinátát adtál meg!");
                TuiHandler.pressEnterKey();
                continue;
            }
            if(uIn > 2) {
                System.out.println("Hiba! Csak az első két oszlopba helyezhetsz el egységeket!");
                TuiHandler.pressEnterKey();
                continue;
            }
            int uX = uIn - 1;

            System.out.print("Add meg Y koordinátát (sorszám)\n: ");
            try {
                Scanner sc = new Scanner(System.in);
                uIn = sc.nextInt();
            } catch (Exception e) {
                continue;
            }
            if (uIn > 10 || uIn < 1) {
                System.out.println("Hiba! Érvénytelen koordinátát adtál meg!");
                TuiHandler.pressEnterKey();
                continue;
            }
            int uY = uIn - 1;

            if(Main.gameLogic.board.boardCells[uX][uY] != null) {
                System.out.println("Hiba! Az megadott cella már foglalt!");
                TuiHandler.pressEnterKey();
                continue;
            }

            Main.gameLogic.board.boardCells[uX][uY] = cellsToPlace.get(uIndex);

        }
    }
}
