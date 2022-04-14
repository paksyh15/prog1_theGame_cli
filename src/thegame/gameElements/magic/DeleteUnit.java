package thegame.gameElements.magic;

import thegame.Main;
import thegame.errors.ExceptionUnsupported;
import thegame.gameElements.Player;
import thegame.gameElements.Position;
import thegame.gameElements.unit.UnitCell;

public class DeleteUnit extends Magic{
    public static String name = "Egység törlése";
    public static Integer price = 200;
    public static Integer mana = 52;

    public DeleteUnit() {
        super(name, price, mana);
    }

    @Override
    public boolean execute(Player player, Position pos) throws ExceptionUnsupported {
        throw new ExceptionUnsupported();
    }

    @Override
    public boolean execute(Player player, UnitCell uc) throws ExceptionUnsupported {
        if(player.getMana() < this.getMana()) {
            return false;
        }
        player.setMana(player.getMana() - this.getMana());
        Main.gameLogic.setUnitToNull(uc);
        return true;
    }

    @Override
    public boolean execute(Player player, UnitCell uc_which, Position toWhere) throws ExceptionUnsupported {
        throw new ExceptionUnsupported();
    }
}
