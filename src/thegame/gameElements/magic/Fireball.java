package thegame.gameElements.magic;

import thegame.Main;
import thegame.errors.ExceptionUnsupported;
import thegame.gameElements.Player;
import thegame.gameElements.Position;
import thegame.gameElements.unit.UnitCell;

public class Fireball extends Magic {
    public static String name = "Tüzgolyó";
    public static Integer price = 120;
    public static Integer mana = 9;
    public static Integer baseDamage = 20;
    public static final Position[] searchPattern = new Position[]{
            //  :D
            new Position(0, 0),
            new Position(1, 0),
            new Position(1, 1),
            new Position(0, 1),
            new Position(-1, 1),
            new Position(-1, 0),
            new Position(-1, -1),
            new Position(0, -1),
            new Position(1, -1)/*,
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
            new Position(2, -1)*/
    };

    public Fireball() {
        super(name, price, mana, searchPattern);
    }

    @Override
    public boolean execute(Player player, Position pos)  {
        if(player.getMana() >= this.getMana()) {
            player.setMana(player.getMana() - this.getMana());
            for(int i = 0; i < this.searchPattern.length; i++) {
                Position curPos = new Position(
                        pos.getX() + this.searchPattern[i].getX(),
                        pos.getY() + this.searchPattern[i].getY());
                if(curPos.getX() < 0 || curPos.getX() > 11 || curPos.getY() < 0 || curPos.getY() > 9) continue;
                if(Main.gameLogic.board.getBoardPos(curPos) == null) continue;
                Main.gameLogic.board.getBoardPos(curPos).receiveRawDamage(Fireball.baseDamage * player.stats.magic.getValue());
            }
            return true;
        }
        return false;
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
