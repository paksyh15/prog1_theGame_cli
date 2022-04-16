package thegame.gameElements.unit;

import thegame.Main;
import thegame.errors.ExceptionNotOnBoard;
import thegame.gameElements.*;

import javax.swing.plaf.TableUI;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Random;

public class UnitCell {
    public Unit unit;  //archer, griffin stb.. osztályokból példa objektum
    public Integer amount;  // egységek száma ezen a kockán
    public Integer edgeHP;  // csak egy egység életét követjük, mert egyesével halnak meg
    public Player owner;


    public UnitCell(Unit unit, Integer amount) {
        this.unit = unit;
        this.amount = amount;
        this.edgeHP = this.unit.health;
    }

    public Position getPosOnBoard(Board board) throws ExceptionNotOnBoard {
        for (int i = 0; i < board.boardCells.length; i++) {
            for (int j = 0; j < board.boardCells[i].length; j++) {
                if (board.boardCells[i][j] == this) {
                    return new Position(i, j);
                }
            }
        }
        throw new ExceptionNotOnBoard();
    }

    public void receiveDamage(UnitCell damagerUc, Integer incomingDamage, boolean stopAttacking) {
        incomingDamage *= (int) Math.ceil(1.0 - (this.owner.stats.defense.getValue() * 0.05));
        this.edgeHP -= incomingDamage;
        if (!stopAttacking || this.unit.infBlowback) {
            this.attackUnitCell(damagerUc, true);
            this.unit.lastBlowbackRound = Main.gameLogic.numRound;
        }
        while (this.edgeHP <= 0) {
            this.amount -= 1;
            this.edgeHP += this.unit.health;
        }
        // széthalás ellenőrzés
        if (this.amount <= 0) {
            Main.gameLogic.setUnitToNull(this);
        }
    }

    public void receiveRawDamage(Integer incomingDamage) {
        this.edgeHP -= incomingDamage;
        while (this.edgeHP <= 0) {
            this.amount -= 1;
            this.edgeHP += this.unit.health;
        }
        // széthalás ellenőrzés
        if (this.amount <= 0) {
            Main.gameLogic.setUnitToNull(this);
        }
    }

    public boolean attackUnitCell(UnitCell targetUC) {
        return this.attackUnitCell(targetUC, false);
    }

    public boolean attackUnitCell(UnitCell targetUC, boolean isBlowback) {
        if (targetUC == null) {
            return false;
        }
        int crit_ = (new Random()).nextInt(1, 11) <= this.owner.stats.luck.getValue() ? 2 : 1;
        if (crit_ > 1.5) {
            System.out.println(this.amount + "x " + this.unit.name + " támadása" + TextColors.YELLOW + " KRITIKUS!" + TextColors.RESET + " (2-es szorzó)");
        }
        int outDmg = (int) (
                Math.round((double) this.unit.getDamageRnd(new Random()) *
                        (double) this.amount *
                        ((double) this.owner.stats.attack.getValue() * 0.1 + 1) *
                        ((double) crit_) *
                        (isBlowback ? 0.5 : 1)) +
                        0.1);
        targetUC.receiveDamage(this, outDmg, isBlowback);
        return true;
    }

    public boolean attackAt(Position pos) {
        return this.attackUnitCell(Main.gameLogic.board.getBoardPos(pos));
    }

    public boolean moveTo(Position pos) {
        ArrayList<Position> goodPosList = this.getViablePositions(Main.gameLogic.board);
        for (Position pos_ : goodPosList) {
            if (pos.equals(pos_)) {
                Position curPos;
                try {
                    curPos = this.getPosOnBoard(Main.gameLogic.board);
                } catch (ExceptionNotOnBoard e) {
                    throw new RuntimeException(e); // elvileg soha
                }
                Main.gameLogic.board.boardCells[curPos.getX()][curPos.getY()] = null;
                Main.gameLogic.board.boardCells[pos.getX()][pos.getY()] = this;
                return true;
            }
        }
        return false;
    }

    public ArrayList<Position> getViablePositions(Board board) {
        final Position[] searchPattern = new Position[]{
                new Position(0, 0),
                new Position(1, 0),
                new Position(1, 1),
                new Position(0, 1),
                new Position(-1, 1),
                new Position(-1, 0),
                new Position(-1, -1),
                new Position(0, -1),
                new Position(1, -1)
        };
        ArrayList<Position> viPoses = new ArrayList<>();
        ArrayList<Integer> expandedSteps = new ArrayList<>();
        int curStep = 0;
        try {
            viPoses.add(this.getPosOnBoard(Main.gameLogic.board));
        } catch (ExceptionNotOnBoard e) {
            System.err.println("Hiba! Ez az egység nincs elhelyezve!");
            return new ArrayList<Position>();
        }
        expandedSteps.add(curStep);
        curStep++;
        while (curStep <= this.unit.getSpeed()) {
            for (int i = 0; i < viPoses.stream().count(); i++) {
                if (expandedSteps.get(i) == curStep - 1) {
                    for (int j = 0; j < searchPattern.length; j++) {
                        Position posPos = new Position(viPoses.get(i).getX() + searchPattern[j].getX(), viPoses.get(i).getY() + searchPattern[j].getY());
                        if (posPos.getX() < 0 || posPos.getX() > 11 || posPos.getY() < 0 || posPos.getY() > 9)
                            continue;
                        if (Main.gameLogic.board.getBoardPos(posPos) == null) {
                            viPoses.add(posPos);
                            expandedSteps.add(curStep);
                        }
                    }
                }
            }
            curStep++;
        }
        return viPoses;
    }
}
