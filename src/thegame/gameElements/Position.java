package thegame.gameElements;

import java.util.HashMap;

/**
 * Ez az osztály egyszerű 2D-s pozíciótárolóként szolgál
 */
public class Position {
    private Integer posX = null;
    private Integer posY = null;

    public Position(Integer posX, Integer posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public int getX() {
        return posX;
    }

    public void setX(Integer posX) {
        this.posX = posX;
    }

    public int getY() {
        return posY;
    }

    public void setY(Integer posY) {
        this.posY = posY;
    }

    public void setBoth(Integer posX, Integer posY) {
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * megadja, hogy két Position objektum x és y koordinátái megegyeznek-e, vagyis ugyanoda mutatnak-e egy 2D-s térben
     * @param otherObject a másik, hasonlítandó objektum
     * @return boolean értékeik megegyeznek-e
     */
    @Override
    public boolean equals(Object otherObject) {
        Position otherPos = null;
        if (otherObject instanceof Position)
            otherPos = (Position) otherObject;
        else return false;
        boolean truth = false;
        try {
            truth = this.getX() == otherPos.getX() && this.getY() == otherPos.getY();
        } catch (Exception e) {
            return false;
        }
        return truth;
    }
}
