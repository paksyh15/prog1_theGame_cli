package thegame.gameElements;

public class Peasant extends Unit {
    static int price = 2;
    static int damageMin = 1;
    static int damageMax = 1;
    static int health = 3;
    static int speed = 4;
    static int initiative = 8;
    static String letter = "P";
    static String name = "Földmüves";

    public Peasant() {
        super(price, damageMin, damageMax, health, speed, initiative, letter, name);
    }
}
