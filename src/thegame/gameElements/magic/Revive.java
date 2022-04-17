package thegame.gameElements.magic;

import thegame.Main;
import thegame.errors.ExceptionUnsupported;
import thegame.gameElements.Player;
import thegame.gameElements.Position;
import thegame.gameElements.unit.UnitCell;

/**
 * varázslat, egy adott egység gyógyítása
 */
public class Revive extends Magic {
    public static String name = "Feltámasztás";
    public static Integer price = 120;
    public static Integer mana = 6;
    public static final Position[] searchPattern = new Position[]{
            new Position(0, 0)
    };

    // halottakra nem működik, Gercsó Márk megengedte
    /**
     * Létrehozza a Magic ősosztályú objektumot a jelen osztály adataival.
     */

    public Revive() {
        super(name, price, mana, searchPattern);
    }

    @Override
    public boolean execute(Player player, Position pos) throws ExceptionUnsupported {
        UnitCell targetUc = Main.gameLogic.board.getBoardPos(pos);
        if(player.getMana() < this.getMana()) {
            return false;
        }
        if(targetUc == null) return false;
        if(targetUc.owner != player) return false;
        player.setMana(player.getMana() - this.getMana());
        targetUc.receiveHealing(player.stats.magic.getValue() * 50);
        return true;
    }
    @Override
    public boolean execute(Player player, UnitCell uc) throws ExceptionUnsupported {
        throw new ExceptionUnsupported();
    }

    @Override
    public boolean execute(Player player, UnitCell uc_which, Position toWhere) throws ExceptionUnsupported {
        throw new ExceptionUnsupported();
    }
}
