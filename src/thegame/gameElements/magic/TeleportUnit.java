package thegame.gameElements.magic;

import thegame.Main;
import thegame.errors.ExceptionUnsupported;
import thegame.gameElements.Player;
import thegame.gameElements.Position;
import thegame.gameElements.TuiHandler;
import thegame.gameElements.unit.UnitCell;

import javax.swing.plaf.TableUI;

/**
 * varázslat, egy saját egység teleportálása a pályán
 */
public class TeleportUnit extends Magic {
    public static String name = "Egység teleportálása";
    public static Integer price = 130;
    public static Integer mana = 25;
    public static final Position[] searchPattern = new Position[]{
            new Position(0, 0)
    };
    /**
     * Létrehozza a Magic ősosztályú objektumot a jelen osztály adataival.
     */
    public TeleportUnit() {
        super(name, price, mana, searchPattern);
    }

    @Override
    public boolean execute(Player player, Position pos) {
        UnitCell targetUc = Main.gameLogic.board.getBoardPos(pos);
        if(player.getMana() < this.getMana()) {
            return false;
        }
        if(targetUc == null) return false;
        if(targetUc.owner != player) return false;
        // hova?
        System.out.println("Hová teleportáljon az egység?");
        Position toPos = TuiHandler.askPosition();
        if(Main.gameLogic.board.getBoardPos(toPos) != null) return false;
        player.setMana(player.getMana() - this.getMana());
        Main.gameLogic.setUnitToNull(targetUc);
        Main.gameLogic.board.setToBoard(targetUc, toPos);
        return true;
    }

    @Override
    public boolean execute(Player player, UnitCell uc) throws ExceptionUnsupported {
        throw new ExceptionUnsupported();
    }

    @Override
    public boolean execute(Player player, UnitCell uc, Position pos) throws ExceptionUnsupported {
        throw new ExceptionUnsupported();
    }
}
