package thegame.gameElements.magic;

import thegame.errors.ExceptionUnsupported;
import thegame.gameElements.Player;
import thegame.gameElements.Position;
import thegame.gameElements.unit.UnitCell;

public class TeleportUnit extends Magic {
    public static String name = "Egység teleportálása";
    public static Integer price = 130;
    public static Integer mana = 30;

    public TeleportUnit() {
        super(name, price, mana);
    }

    @Override
    public boolean execute(Player player, Position pos) throws ExceptionUnsupported {
        throw new ExceptionUnsupported();
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
