package thegame.gameElements.unit;

import thegame.errors.ExceptionNotOnBoard;
import thegame.gameElements.Board;
import thegame.gameElements.Player;
import thegame.gameElements.Position;

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
        for(int i = 0; i < board.boardCells.length; i++) {
            for(int j = 0; j < board.boardCells[i].length; j++) {
                if(board.boardCells[i][j] == this) {
                    return new Position(i, j);
                }
            }
        }
        throw new ExceptionNotOnBoard();
    }
}
