package thegame.gameElements;

public class PlayerStats {
    Stat attack = new Stat("Támadás", 1);
    Stat defense = new Stat("Védekezés", 1);
    Stat magic = new Stat("Varázserő", 1);
    Stat intelligence = new Stat("Tudás", 1);
    Stat moral = new Stat("Morál", 1);
    Stat luck = new Stat("Szerencse", 1);

    private class Stat {
        String name = null;
        Integer value = null;

        public Stat(String name, Integer value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }

    public PlayerStats() {
        //ok
    }
}
