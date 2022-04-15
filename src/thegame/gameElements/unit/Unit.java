package thegame.gameElements.unit;

//import javafx.scene.image.Image;

import thegame.Main;

import java.util.ArrayList;
import java.util.Random;

public abstract class Unit {
    public int price;
    public int damageMin;
    public int damageMax;
    public int health;
    public int speed;
    public int initiative;
    public String letter = null;
    public String name = null;
    public int lastBlowbackRound = 0; // utolsó kör, amelyikben visszatámadt
    public boolean infBlowback;

    public Unit(int price, int damageMin, int damageMax, int health, int speed, int initiative, String letter, String name, boolean infBlowback) {
        this.price = price;
        this.damageMin = damageMin;
        this.damageMax = damageMax;
        this.health = health;
        this.speed = speed;
        this.initiative = initiative;
        this.letter = letter;
        this.name = name;
        this.infBlowback = infBlowback;
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

    public boolean afterHit(UnitCell me, UnitCell damagerUc) {
        if(this.infBlowback) {
            me.attackUnitCell(damagerUc, true);
            return true;
        } else {
            if(this.lastBlowbackRound != Main.gameLogic.numRound) {
                me.attackUnitCell(damagerUc, true);
                this.lastBlowbackRound = Main.gameLogic.numRound;
                return true;
            }
        }
        return false;
    };

}
