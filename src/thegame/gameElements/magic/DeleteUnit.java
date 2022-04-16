package thegame.gameElements.magic;

import thegame.Main;
import thegame.errors.ExceptionUnsupported;
import thegame.gameElements.Player;
import thegame.gameElements.Position;
import thegame.gameElements.unit.UnitCell;

/**
 * varázslat, egy egység törlése
 */
public class DeleteUnit extends Magic{
    public static String name = "Egység törlése";
    public static Integer price = 170;
    public static Integer mana = 52;
    public static final Position[] searchPattern = new Position[]{
            new Position(0, 0)
    };

    /**
     * Létrehozza a Magic ősosztályú objektumot a jelen osztály adataival.
     */
    public DeleteUnit() {
        super(name, price, mana, searchPattern);
    }

    @Override
    public boolean execute(Player player, Position pos)  {
        UnitCell targetUc = Main.gameLogic.board.getBoardPos(pos);
        if(player.getMana() < this.getMana()) {
            return false;
        }
        if(targetUc == null) return false;
        if(targetUc.owner == player) return false;
        player.setMana(player.getMana() - this.getMana());
        Main.gameLogic.setUnitToNull(targetUc);
        return true;
    }
    @Deprecated
    @Override
    public boolean execute(Player player, UnitCell uc) throws ExceptionUnsupported {
        throw new ExceptionUnsupported();
    }
    @Deprecated
    @Override
    public boolean execute(Player player, UnitCell uc_which, Position toWhere) throws ExceptionUnsupported {
        throw new ExceptionUnsupported();
    }
}
