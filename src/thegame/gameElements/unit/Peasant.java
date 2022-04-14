package thegame.gameElements.unit;

public class Peasant extends Unit {
    public static int price = 2;
    public static int damageMin = 1;
    public static int damageMax = 1;
    public static int health = 3;
    public static int speed = 4;
    public static int initiative = 8;
    public static String letter = "P";
    public static String name = "Földmüves";

    public Peasant() {
        super(price, damageMin, damageMax, health, speed, initiative, letter, name);
    }
}
