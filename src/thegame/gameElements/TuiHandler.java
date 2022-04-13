package thegame.gameElements;

public class TuiHandler {
    public void printBoard(Board board) {
        System.out.print("0\s\s");
        for(int i = 1; i <= board.boardCells[0].length; i++) {
            if(i <= 9)
                System.out.print("\s" + i + "\s\s");
            else
                System.out.print("\s" + i + "\s");
        }
        System.out.print("\n");
        int i = 1;
        for (UnitCell[] row : board.boardCells) {
            if(i <= 9)
                System.out.print(i + "\s|\s");
            else
                System.out.print(i + "|\s");
            for (UnitCell cell : row) {
                if(cell == null) {
                    System.out.print("\s\s|\s");
                } else {
                    System.out.print(cell.unit.letter + " |");
                }

            }
            System.out.print("\n");
            i++;
        }
    }
}
