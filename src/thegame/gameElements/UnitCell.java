package thegame.gameElements;

import thegame.errors.ExceptionNotOnBoard;

public class UnitCell {
    Unit unit;  //archer, griffin stb.. osztályokból példa objektum
    Integer amount;  // egységek száma ezen a kockán
    Integer edgeHP;  // csak egy egység életét követjük, mert egyesével halnak meg
    Player owner;

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
