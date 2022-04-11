package thegame.gameElements;

public class Archer extends Unit {
    static int price = 6;
    static int damageMin = 2;
    static int damageMax = 4;
    static int health = 7;
    static int speed = 4;
    static int initiative = 9;
    static String letter = "Í";
    static String name = "Íjász";

    public Archer() {
        super(price, damageMin, damageMax, health, speed, initiative, letter, name);
    }
}
