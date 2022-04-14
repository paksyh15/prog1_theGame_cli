package thegame.gameElements;

import thegame.Main;
import thegame.gameElements.magic.Magic;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private Integer money = null;
    // statok
    public PlayerStats stats = new PlayerStats();
    public List<UnitCell> ownedCells = new ArrayList<UnitCell>();
    public List<Magic> ownedMagic = new ArrayList<Magic>();
    public Integer attrPrice = 5;

    public Player(Integer money) {
        if (money >= 0)
            this.money = money;
        else this.money = 0;
    }

    public Player() {

    }

    public Integer getBalance() {
        return money;
    }

    public boolean setBalance(Integer money) {
        if (money > 0) {
            this.money = money;
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<UnitCell> getAllPlacedCells() {
        ArrayList<UnitCell> list = new ArrayList<>();
        Board board = Main.gameLogic.board;
        for(int i = 0; i < board.boardCells[0].length; i++) {
            for(int j = 0; j < board.boardCells.length; j++) {
                if(board.boardCells[j][i] == null) continue;
                if(board.boardCells[j][i].owner == this) {
                    list.add(board.boardCells[j][i]);
                }
            }
        }
        return list;
    }
}
