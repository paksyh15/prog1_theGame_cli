package thegame.gameElements.unit;

public class Archer extends Unit {
    public static int price = 6;
    public static int damageMin = 2;
    public static int damageMax = 4;
    public static int health = 7;
    public static int speed = 4;
    public static int initiative = 9;
    public static String letter = "I";
    public static String name = "Ijász";

    public Archer() {
        super(price, damageMin, damageMax, health, speed, initiative, letter, name);
    }
}
