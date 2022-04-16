package thegame.gameElements.unit;

import thegame.Main;
import thegame.errors.ExceptionNotOnBoard;
import thegame.gameElements.Position;

import java.util.Random;
import java.util.Scanner;

public class Archer extends Unit {
    public static int price = 6;
    public static int damageMin = 2;
    public static int damageMax = 4;
    public static int health = 7;
    public static int speed = 4;
    public static int initiative = 9;
    public static String letter = "I";
    public static String name = "Ijász";
    public static boolean infBlowback = false;

    public Archer() {
        super(price, damageMin, damageMax, health, speed, initiative, letter, name, infBlowback);
    }

    @Override
    public boolean specialAttack(UnitCell me, Position where) {
        final Position[] searchPattern = new Position[]{
                new Position(1, 0),
                new Position(1, 1),
                new Position(0, 1),
                new Position(-1, 1),
                new Position(-1, 0),
                new Position(-1, -1),
                new Position(0, -1),
                new Position(1, -1)
        };
        Position mePos;
        try {
            mePos = me.getPosOnBoard(Main.gameLogic.board);
        } catch (ExceptionNotOnBoard e) {
            System.err.println("Az egység nem található a táblán!");
            return false;
        }
        for(int i = 0; i < searchPattern.length; i++) {
            Position checkPos = new Position(mePos.getX() + searchPattern[i].getX(), mePos.getY() + searchPattern[i].getY());
            if (checkPos.getX() < 0 || checkPos.getX() > 11 || checkPos.getY() < 0 || checkPos.getY() > 9) {
                UnitCell checkUc = Main.gameLogic.board.getBoardPos(checkPos);
                if(checkUc == null) continue;
                if(checkUc.owner != me.owner) return false;
            }
        }
        System.out.print("Hova szeretnél lőni?\n");
        int uX = -1;
        while(uX==-1){
            try {
                System.out.print("Oszlopszám: ");
                Scanner sc = new Scanner(System.in);
                uX = sc.nextInt();
            } catch (Exception e) {
                uX = -1;
                continue;
            }
            if(uX < 1 || uX > 12) continue;
        }
        int uY = -1;
        while(uY==-1){
            try {
                System.out.print("Sorszám: ");
                Scanner sc = new Scanner(System.in);
                uY = sc.nextInt();
            } catch (Exception e) {
                uY = -1;
                continue;
            }
            if(uY < 1 || uY > 12) continue;
        }
        Position pos = new Position(uX - 1, uY - 1);
        UnitCell targetUc = Main.gameLogic.board.getBoardPos(pos);
        if(targetUc == null) return false;
        if(targetUc.owner == me.owner) return false;
        // támadás, de amaz ne támadjon vissza mer esetleg hogy?
        targetUc.receiveDamage(me, this.getDamageRnd(new Random()) * me.amount, true);
        return true;
    }
}
