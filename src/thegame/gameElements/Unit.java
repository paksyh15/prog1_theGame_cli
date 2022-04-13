package thegame.gameElements;

//import javafx.scene.image.Image;

import java.util.Random;

public abstract class Unit {
    int price;
    int damageMin;
    int damageMax;
    int health;
    int speed;
    int initiative;
    String letter = null;
    String name = null;

    public Unit(int price, int damageMin, int damageMax, int health, int speed, int initiative, String letter, String name) {
        this.price = price;
        this.damageMin = damageMin;
        this.damageMax = damageMax;
        this.health = health;
        this.speed = speed;
        this.initiative = initiative;
        this.letter = letter;
        this.name = name;
    }

    public int getPrice() {
        return this.price;
    }

    public int getDamageMin() {
        return this.damageMin;
    }

    public int getDamageMax() {
        return this.damageMax;
    }

    public int getDamageRnd(Random rnd) {
        return rnd.nextInt(this.getDamageMin(), this.getDamageMax() + 1);
    }

    public int getHealth() {
        return this.health;
    }

    public int getSpeed() {
        return this.speed;
    }

    public int getInitiative() {
        return this.initiative;
    }

    /*public void setHealth(int health) {
        this.health = Math.max(health, 0);
    }*/
}
