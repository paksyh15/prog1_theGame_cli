package thegame.gameElements;

//import thegame.MainWindow;

import thegame.Main;
import thegame.gameElements.unit.UnitCell;

import java.util.ArrayList;
import java.util.Arrays;

public class GameLogic {

    private final Player player1 = new Player();
    private final Player player2 = new Player(1000);
    public Board board = new Board();
    public Integer numRound = 0; // jelenlegi kör

    public GameLogic() {

    }

    public boolean setDifficulty(int difficulty) {
        Integer money = null;
        switch (difficulty) {
            case 0:
                money = 1300;
                break;
            case 1:
                money = 1000;
                break;
            case 2:
                money = 700;
                break;
        }

        player1.setBalance(money);

        return true;
    }

    public Player getPlayer(int num) {
        switch (num) {
            case 1:
                return player1;
            case 2:
                return player2;
            default:
                return null;
        }
    }

    public void setUnitToNull(UnitCell uc) {
        for (int i = 0; i < this.board.boardCells[0].length; i++) {
            for (int j = 0; j < this.board.boardCells.length; j++) {
                if (this.board.boardCells[j][i] == uc) {
                    this.board.boardCells[j][i] = null;
                    return;
                }
            }
        }
    }

    private ArrayList<UnitCell> getMoveOrderedUCs() {
        ArrayList<UnitCell> oList = new ArrayList<UnitCell>();
        oList.addAll(getPlayer(1).getAllPlacedCells());
        oList.addAll(getPlayer(2).getAllPlacedCells());
        for (int i = 0; i <= oList.stream().count(); i++) {
            for (int j = 0; j < oList.stream().count() - 1; j++) {
                if ((oList.get(j).unit).initiative < oList.get(j + 1).unit.initiative) {
                    UnitCell temp = oList.get(j);
                    oList.set(j, oList.get(j + 1));
                    oList.set(j + 1, temp);
                }
            }
        }
        return oList;
    }

    public void battle() {
        TuiHandler.clearSceen();
        boolean isOver = false, isRoundOver = false;
        while (!isOver) {
            // round start
            numRound += 1;
            while (!isRoundOver) {
                TuiHandler.printRoundLine();
                TuiHandler.printBoard(Main.gameLogic.board);
                ArrayList<UnitCell> orderedUnits = getMoveOrderedUCs();
                System.out.print("Az egységek következési sorrendben: ");
                TuiHandler.printOrderedUnits(orderedUnits);
                Player curPlayer = orderedUnits.get(0).owner;
                if(curPlayer == getPlayer(1)) {
                    // enber gyün
                    switch (TuiHandler.askWhatDo(orderedUnits.get(0))) {
                        case 0:
                            // egységgel csinálás
                            UnitCell uc = orderedUnits.get(0);
                            switch(TuiHandler.askWhatDoUnitCell(uc)) {
                                case 0:

                                    break;
                                case 1:

                                    break;
                                case 2:

                                    break;
                            }
                            break;
                        case 1:
                            // varázslás
                            break;
                    }

                } else {
                    // bot gyün
                    // TODO: az egész bot kb
                }
            }
        }
    }

}
