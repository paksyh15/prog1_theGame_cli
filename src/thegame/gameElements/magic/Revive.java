package thegame.gameElements.magic;

import thegame.errors.ExceptionUnsupported;
import thegame.gameElements.Player;
import thegame.gameElements.Position;
import thegame.gameElements.unit.UnitCell;

public class Revive extends Magic {
    public static String name = "Feltámasztás";
    public static Integer price = 120;
    public static Integer mana = 6;

    public Revive() {
        super(name, price, mana);
    }

    @Override
    public void execute(Player player, Position pos) throws ExceptionUnsupported {

    }

    @Override
    public void execute(Player player, UnitCell uc_which, Position toWhere) {

    }
}
