package thegame.gameElements;

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
}
