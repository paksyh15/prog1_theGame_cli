package thegame.gameElements;

//import thegame.MainWindow;

import thegame.Main;

public class GameLogic {

    private boolean isGameStarted = false;

    private Player player1 = new Player();
    private Player player2 = new Player(1000);
    private Board board = new Board();

    public GameLogic() {

    }

    public boolean setDifficulty(int difficulty) {
        if(this.isGameStarted) return false;
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

    public boolean isGameStarted() {
        return isGameStarted;
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

}
