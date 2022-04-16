package thegame.gameElements.unit;

//import javafx.scene.image.Image;

import thegame.Main;
import thegame.gameElements.Position;

import java.util.Random;

/**
 * Egység ősosztály; ebből származnak a különféle egységek, egyfalyta egységazonosítóként is szolgál objektum formában
 */
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
    public int lastActionRound = 0;
    public boolean infBlowback;

    /**
     * Ez a Unit osztály constructor metódusa, amely átveszi az utódosztályok adatait.
     * @param price egység ára aranyban
     * @param damageMin egység sebzésének alsóhatára
     * @param damageMax egység sebzésének felsőhatára
     * @param health egység életereje
     * @param speed egység sebessége
     * @param initiative egység kezdeményezése
     * @param letter egység betűjele a terminálban
     * @param name egység neve
     * @param infBlowback végtelenségig támadhat-e vissza az egység
     */
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

    /**
     * Visszaadja az egység árát
     * @return int ár aranyban
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Visszaadja az egység sebzésének alsóhatárát
     * @return int sebzés alsóhatár
     */
    public int getDamageMin() {
        return this.damageMin;
    }
    /**
     * Visszaadja az egység sebzésének felsőhatárát
     * @return int sebzés felsőhatár
     */
    public int getDamageMax() {
        return this.damageMax;
    }

    /**
     * Az egység sebzésének alsó és felső határa által bezárt értékekből visszaad egy véletlenszerű értéket
     * @param rnd a Random objektum, amit használ a metódus
     * @return int véletlenszerű, határolt sebzés
     */
    public int getDamageRnd(Random rnd) {
        return rnd.nextInt(this.getDamageMin(), this.getDamageMax() + 1);
    }

    /**
     * Visszaadja az egység életerejét.
     * @return int egység életereje
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Visszaadja az egység sebességét
     * @return int egység sebessége
     */
    public int getSpeed() {
        return this.speed;
    }

    /**
     * Visszaadja az egység kezdeményezését
     * @return int egység kezdeményezése
     */
    public int getInitiative() {
        return this.initiative;
    }

    /**
     * Egy metódus, melyet a UnitCell wrapper-osztály szükségszerűen meghív, miután az egység sebzést szenved el.
     * @param me UnitCell wrapper-osztály
     * @param damagerUc sebzést okozó UnitCell objektum
     * @return boolean sikerült-e a cselekvés
     */
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
    }

    /**
     * Alosztályok által megvalósítandó metódus, mely az adott egységek speciális támadását viszi véghez.
     * @param me UnitCell wrapper-osztály
     * @param where
     * @return boolean sikerült-e a cselekvés
     */
    public abstract boolean specialAttack(UnitCell me, Position where);
}
