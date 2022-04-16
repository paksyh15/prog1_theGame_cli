package thegame.gameElements;

//import thegame.MainWindow;

import thegame.Main;
import thegame.errors.ExceptionNotOnBoard;
import thegame.errors.ExceptionUnsupported;
import thegame.gameElements.unit.UnitCell;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * ezen osztály példányosított másolata(i) felel(nek) a játék működéséért körről-körre
 */
public class GameLogic {

    public final Player player1 = new Player();
    public final Player player2 = new Player(1000);
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
            default:
                return false;
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
        Position ucPos;
        try {
            ucPos = uc.getPosOnBoard(this.board);
        } catch (ExceptionNotOnBoard e) {
            throw new RuntimeException(e); // elvileg soha
        }
        this.board.boardCells[ucPos.getX()][ucPos.getY()] = null;
    }

    private ArrayList<UnitCell> getMoveOrderedUCs() {
        ArrayList<UnitCell> oList = new ArrayList<UnitCell>();
        oList.addAll(getPlayer(1).getAllPlacedCells());
        oList.addAll(getPlayer(2).getAllPlacedCells());
        for (int i = 0; i <= oList.stream().count(); i++) {
            for (int j = 0; j < oList.stream().count() - 1; j++) {
                if ((oList.get(j).unit.initiative + oList.get(j).owner.stats.moral.getValue()) < (oList.get(j + 1).unit.initiative + oList.get(j + 1).owner.stats.moral.getValue())) {
                    UnitCell temp = oList.get(j);
                    oList.set(j, oList.get(j + 1));
                    oList.set(j + 1, temp);
                }
            }
        }
        Iterator<UnitCell> it = oList.iterator();
        while (it.hasNext()) {
            UnitCell unitCell = it.next();
            if (unitCell.unit.lastActionRound == numRound) {
                it.remove();
            }
        }
        return oList;
    }

    @SuppressWarnings("ConstantConditions")
    private int whoWon(ArrayList<UnitCell> ucList) {
        boolean has1 = false, has2 = false;
        for(UnitCell uc : ucList) {
            if(uc.owner == Main.gameLogic.getPlayer(1)) has1 = true;
            else if(uc.owner == Main.gameLogic.getPlayer(2)) has2 = true;
            if( has1 && has2 ) return 0; // még senki
        }
        if(has1 && !has2) return 1; // enber
        if(!has1 && has2) return 2; // bot
        return 3; // mindketten vesztenek (mittomén nemtom lehet-e)
    }

    public void battle() {
        TuiHandler.clearSceen();
        boolean isOver = false, isRoundOver = false;
        while (!isOver) {
            // round start
            this.numRound += 1;
            isRoundOver = false;
            while (!isRoundOver) {
                TuiHandler.printRoundLine();
                TuiHandler.printBoard(Main.gameLogic.board);
                ArrayList<UnitCell> orderedUnits = getMoveOrderedUCs();
                if (orderedUnits.stream().count() == 0) {
                    isRoundOver = true;
                    break;
                }
                System.out.print("Az egységek következési sorrendben: ");
                TuiHandler.printOrderedUnits(orderedUnits);
                // vibe check

                // vibe check over
                TuiHandler.printPlayerMana(Main.gameLogic.getPlayer(1));
                Player curPlayer = orderedUnits.get(0).owner;
                if (curPlayer == getPlayer(1)) {
                    // enber gyün
                    switch (TuiHandler.askWhatDo(orderedUnits.get(0))) {
                        case 0: // aktív egységgel valamit
                            // egységgel csinálás
                            UnitCell uc = orderedUnits.get(0);
                            switch (TuiHandler.askWhatDoUnitCell(uc)) {
                                case 0: // támad
                                    Position pos = TuiHandler.askPosition();
                                    if (uc.attackAt(pos)) {
                                        uc.unit.lastActionRound = this.numRound;
                                    } else {
                                        System.out.println("Sikertelen támadás!");
                                        TuiHandler.pressEnterKey();
                                    }
                                    break;
                                case 1: // egység spec képesség
                                    Position pos4 = TuiHandler.askPosition();
                                    if(uc.unit.specialAttack(uc, pos4)) {
                                        uc.unit.lastActionRound = this.numRound;
                                    } else {
                                        System.out.println("Sikertelen különleges képesség használat!");
                                        TuiHandler.pressEnterKey();
                                    }
                                    break;
                                case 2: // mozgás
                                    Position pos2 = TuiHandler.askPosition();
                                    if (uc.moveTo(pos2)) {
                                        uc.unit.lastActionRound = this.numRound;
                                    } else {
                                        System.out.println("Sikertelen mozgás!");
                                        TuiHandler.pressEnterKey();
                                    }
                                    break;
                                case 3: // egység afk
                                    uc.unit.lastActionRound = this.numRound;
                                    break;
                            }
                            break;
                        case 1:
                            // varázslás
                            int uMagic = TuiHandler.askWhatDoMagic(this.getPlayer(1));
                            if(uMagic == -1) break;
                            Position pos5 = TuiHandler.askPosition();
                            try {
                                if(this.getPlayer(1).ownedMagic.get(uMagic).execute(getPlayer(1), pos5)) {
                                    this.getPlayer(1).lastActionRound = this.numRound;
                                } else {
                                    System.out.println("Varázslás sikertelen!");
                                    TuiHandler.pressEnterKey();
                                }
                            } catch (ExceptionUnsupported e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        case 2:
                            // támadás
                            Position pos3 = TuiHandler.askPosition();
                            UnitCell targetUc3 = Main.gameLogic.board.getBoardPos(pos3);
                            if (Main.gameLogic.getPlayer(1).attack(targetUc3)) {
                                Main.gameLogic.getPlayer(1).lastActionRound = this.numRound;
                            } else {
                                System.out.println("Sikertelen támadás!");
                                TuiHandler.pressEnterKey();
                            }
                            break;
                    }

                } else {
                    // bot gyün
                    // TODO: az egész bot kb
                    UnitCell uc = orderedUnits.get(0);
                    uc.unit.lastActionRound = numRound;
                }
            }
        }
    }

}
