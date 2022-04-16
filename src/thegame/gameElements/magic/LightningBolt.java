package thegame.gameElements.magic;

import thegame.Main;
import thegame.errors.ExceptionUnsupported;
import thegame.gameElements.Player;
import thegame.gameElements.Position;
import thegame.gameElements.unit.UnitCell;

/**
 * varázslat, villámcsapás egy egységre
 */
public class LightningBolt extends Magic{
    public static String name = "Villámcsapás";
    public static Integer price = 60;
    public static Integer mana = 5;
    public static final Position[] searchPattern = new Position[]{
            new Position(0, 0)
    };
    public static Integer baseDamage = 30;
    /**
     * Létrehozza a Magic ősosztályú objektumot a jelen osztály adataival.
     */
    public LightningBolt() {
        super(name, price, mana, searchPattern);
    }

    @Override
    public boolean execute(Player player, Position pos)  {
        if(player.getMana() >= this.mana) {
            player.setMana(player.getMana() - this.mana);
            UnitCell targetUc = Main.gameLogic.board.getBoardPos(pos);
            if(targetUc == null) return false;
            if(targetUc.owner == player) return false;
            targetUc.receiveRawDamage(LightningBolt.baseDamage * player.stats.magic.getValue());
            return true;
        }
        return false;
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
