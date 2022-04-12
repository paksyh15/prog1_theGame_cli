package thegame.gameElements;

public class Board {
    public final UnitCell[][] boardCells = new UnitCell[10][12];

    public UnitCell getBoardPos(Position pos) {
        return boardCells[pos.getX()][pos.getY()];
    }

    public void setToBoard(UnitCell uc, Position pos) {
        boardCells[pos.getX()][pos.getY()] = uc;
    }

    public boolean addToBoard(UnitCell uc, Position pos) {
        if(this.getBoardPos(pos) != null) return false;
        setToBoard(uc, pos);
        return true;
    }
}
