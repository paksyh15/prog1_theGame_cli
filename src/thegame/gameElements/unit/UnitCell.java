package thegame.gameElements.unit;

import thegame.Main;
import thegame.errors.ExceptionNotOnBoard;
import thegame.gameElements.Board;
import thegame.gameElements.Player;
import thegame.gameElements.Position;

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
        if (!stopAttacking) this.attackUnitCell(damagerUc, true);
        while (edgeHP <= 0) {
            this.amount -= 1;
            this.edgeHP += this.unit.health;
        }
        // széthalás ellenőrzés
        if(this.amount <= 0) {
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
        int outDmg = (int)
                Math.ceil((double) this.unit.getDamageRnd(new Random()) *
                        (double) this.amount *
                        (double) this.owner.stats.attack.getValue() *
                        ((new Random()).nextInt(1, 11) <= this.owner.stats.luck.getValue() ? 2 : 1));
        System.out.println("én vagyok " + this.amount + " " + this.unit.name  + "atk " + outDmg);
        targetUC.receiveDamage(this, outDmg, isBlowback);
        return true;
    }

    public boolean attackAt(Position pos) {
        return this.attackUnitCell(Main.gameLogic.board.getBoardPos(pos));
    }
}
