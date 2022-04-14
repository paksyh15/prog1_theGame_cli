package thegame.gameElements.magic;

import thegame.errors.ExceptionUnsupported;
import thegame.gameElements.Player;
import thegame.gameElements.Position;
import thegame.gameElements.unit.UnitCell;

public class DeleteUnit extends Magic{
    public static String name = "Egység törlése";
    public static Integer price = 200;
    public static Integer mana = 90;

    public DeleteUnit() {
        super(name, price, mana);
    }

    @Override
    public void execute(Player player, Position pos) throws ExceptionUnsupported {

    }


    @Override
    public void execute(Player player, UnitCell uc_which, Position toWhere) {

    }
}
