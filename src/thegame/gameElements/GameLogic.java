package thegame.gameElements;

//import thegame.MainWindow;

import thegame.Main;
import thegame.gameElements.unit.UnitCell;

public class GameLogic {

    private final Player player1 = new Player();
    private final Player player2 = new Player(1000);
    public Board board = new Board();

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
        for(int i = 0; i < this.board.boardCells[0].length; i++) {
            for(int j = 0; j < this.board.boardCells.length; j++) {
                if(this.board.boardCells[j][i] == uc) {
                    this.board.boardCells[j][i] = null;
                    return;
                }
            }
        }
    }

}
