package thegame.gameElements;

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
}
