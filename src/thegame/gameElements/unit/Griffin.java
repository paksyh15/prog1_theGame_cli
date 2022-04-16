package thegame.gameElements.unit;

import thegame.gameElements.Position;

/**
 * egység; griff alosztály
 */
public class Griffin extends Unit {
    public static int price = 15;
    public static int damageMin = 5;
    public static int damageMax = 10;
    public static int health = 30;
    public static int speed = 7;
    public static int initiative = 15;
    public static String letter = "G";
    public static String name = "Griff";
    public static boolean infBlowback = true;

    public Griffin() {
        super(price, damageMin, damageMax, health, speed, initiative, letter, name, infBlowback);
    }

    @Override
    public boolean specialAttack(UnitCell me, Position where) {
        return false;
    }
}
