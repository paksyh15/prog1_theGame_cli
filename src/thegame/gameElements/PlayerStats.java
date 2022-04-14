package thegame.gameElements;

import java.util.ArrayList;
import java.util.List;

public class PlayerStats {
    List<Stat> statsList = new ArrayList<>();

    Stat attack = new Stat("Támadás", 1);
    Stat defense = new Stat("Védekezés", 1);
    Stat magic = new Stat("Varázserö", 1);
    Stat intelligence = new Stat("Tudás", 1);
    Stat moral = new Stat("Morál", 1);
    Stat luck = new Stat("Szerencse", 1);


    public class Stat {
        String name;
        Integer value;

        public Stat(String name, Integer value) {
            this.name = name;
            this.value = value;
            statsList.add(this);
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
