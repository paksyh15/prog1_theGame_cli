package thegame.gameElements.magic;

public class Magic {
    public String name = null;
    public Integer price = null;
    public Integer mana = null;

    public Magic(String name, Integer price, Integer mana) {
        this.name = name;
        this.price = price;
        this.mana = mana;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public int getMana() {
        return this.mana;
    }
}
