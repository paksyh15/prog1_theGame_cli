package thegame.gameElements;

public class PlayerStats {
    byte attack = 1;
    byte defense = 1;
    byte magic = 1;
    byte intelligence = 1;
    byte moral = 1;
    byte luck = 1;

    public PlayerStats() {
        //ok
    }

    public PlayerStats(byte attack, byte defense, byte magic, byte intelligence, byte moral, byte luck) {
        this.attack = attack;
        this.defense = defense;
        this.magic = magic;
        this.intelligence = intelligence;
        this.moral = moral;
        this.luck = luck;
    }
}
