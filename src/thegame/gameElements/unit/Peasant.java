package thegame.gameElements.unit;

import thegame.gameElements.Position;

/**
 * egység; földműves alosztály
 */
public class Peasant extends Unit {
    public static int price = 2;
    public static int damageMin = 1;
    public static int damageMax = 1;
    public static int health = 3;
    public static int speed = 4;
    public static int initiative = 8;
    public static String letter = "F";
    public static String name = "Földmüves";
    public static boolean infBlowback = false;

    public Peasant() {
        super(price, damageMin, damageMax, health, speed, initiative, letter, name, infBlowback);
    }

    @Override
    public boolean specialAttack(UnitCell me, Position where) {
        return false;
    }
}
