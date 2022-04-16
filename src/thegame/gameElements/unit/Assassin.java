package thegame.gameElements.unit;

import thegame.gameElements.Position;

/**
 * egység; bérgyilkos alosztály
 */
public class Assassin extends Unit {


    public static int price = 12;
    public static int damageMin = 8;
    public static int damageMax = 9;
    public static int health = 6;
    public static int speed = 8;
    public static int initiative = 13;
    public static String letter = "B";
    public static String name = "Bérgyilkos";
    public static boolean infBlowback = false;

    public Assassin() {
        super(price, damageMin, damageMax, health, speed, initiative, letter, name, infBlowback);
    }

    @Override
    public boolean specialAttack(UnitCell me, Position where) {
        return false;
    }
}
