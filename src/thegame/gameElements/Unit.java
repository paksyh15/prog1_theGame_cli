package thegame.gameElements;

//import javafx.scene.image.Image;

abstract public class Unit {
    int price;
    int damageMin;
    int damageMax;
    int health;
    int speed;
    int initiative;
    String letter = "";
    String name = "";
//    public static String imgPath;
    // special attack


    public int getPrice() {
        return price;
    }

    public int getDamageMin() {
        return damageMin;
    }

    public int getDamageMax() {
        return damageMax;
    }

    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }

    public int getInitiative() {
        return initiative;
    }

    /*public void setHealth(int health) {
        this.health = Math.max(health, 0);
    }*/
}
