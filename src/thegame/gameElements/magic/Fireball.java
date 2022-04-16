package thegame.gameElements.magic;

import thegame.errors.ExceptionUnsupported;
import thegame.gameElements.Player;
import thegame.gameElements.Position;
import thegame.gameElements.unit.UnitCell;

public class Fireball extends Magic {
    public static String name = "Tüzgolyó";
    public static Integer price = 120;
    public static Integer mana = 9;
    public static final Position[] searchPattern = new Position[]{
            new Position(1, 0),
            new Position(1, 1),
            new Position(0, 1),
            new Position(-1, 1),
            new Position(-1, 0),
            new Position(-1, -1),
            new Position(0, -1),
            new Position(1, -1),

            new Position(2, 0),
            new Position(2, 1),
            new Position(2, 2),
            new Position(1, 2),
            new Position(0, 2),
            new Position(-1, 2),
            new Position(-2, 2),
            new Position(-2, 1),
            new Position(-2, 0),
            new Position( -2, -1),
            new Position(-2, -2),
            new Position(-1, -2),
            new Position(0, -2),
            new Position(1, -2),
            new Position(2, -2),
            new Position(2, -1)
    };

    public Fireball() {
        super(name, price, mana, searchPattern);
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
    public boolean execute(Player player, UnitCell uc_which, Position toWhere) throws ExceptionUnsupported {
        throw new ExceptionUnsupported();
    }
}
