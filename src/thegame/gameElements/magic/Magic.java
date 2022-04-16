package thegame.gameElements.magic;

import thegame.errors.ExceptionUnsupported;
import thegame.gameElements.Player;
import thegame.gameElements.Position;
import thegame.gameElements.unit.UnitCell;

/**
 * a Magic ősosztály; ebből származnak a játékban lévő varázslatok
 */
public abstract class Magic {
    public String name = null;
    public Integer price = null;
    public Integer mana = null;
    public Position[] searchPattern;

    /**
     * Ez a Magic ősosztály constructor metódusa, amely átveszi az utódosztályok adatait.
     * @param name a varázslat neve
     * @param price a varázslat ára aranyban
     * @param mana szükséges manamennyiség a varázslat használatához
     * @param sPattern keresési alak, amely megadja, hogy a pályán mely rublikákon fog egységekre hatni a varázslat
     */
    public Magic(String name, Integer price, Integer mana, Position[] sPattern) {
        this.name = name;
        this.price = price;
        this.mana = mana;
        this.searchPattern = sPattern;
    }

    /**
     * Visszaadja a varázslat nevét.
     * @return String a varázslat nevét
     */
    public String getName() {
        return this.name;
    }

    /**
     * Visszaadja a varázslat árát aranyban.
     * @return int a varázslat ára aranyban
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Visszaadja a varázslathoz szükséges manát.
     * @return int szükséges mana
     */
    public int getMana() {
        return this.mana;
    }

    /**
     * Végrehajtja a varázslatot egy adott pozíción.
     * @param player a varázsló hős
     * @param pos a varázslat pozíciója
     * @return boolean sikerült-e
     * @throws ExceptionUnsupported
     */
    public abstract boolean execute(Player player, Position pos) throws ExceptionUnsupported;

    /**
     * Végrehajtja a varázslatot egy adott pozíción egy egységet és számításba véve.
     * @param player a varázsló hős
     * @param uc a befolyásolt egység
     * @param pos a varázslat pozíciója
     * @return boolean sikerült-e
     * @throws ExceptionUnsupported
     */
    public abstract boolean execute(Player player, UnitCell uc, Position pos) throws ExceptionUnsupported;
    /**
     * Végrehajtja a varázslatot egy adott egységen.
     * @param player a varázsló hős
     * @param uc a befolyásolt egység
     * @return boolean sikerült-e
     * @throws ExceptionUnsupported
     */
    public abstract boolean execute(Player player, UnitCell uc) throws ExceptionUnsupported;


}
