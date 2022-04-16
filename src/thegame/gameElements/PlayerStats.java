package thegame.gameElements;

import java.util.ArrayList;
import java.util.List;

/**
 * ezzel az osztállyal lehet kezelni tulajdonságpontokat, illetve azokat Hőshöz (játékoshoz) rendelni
 */
public class PlayerStats {
    public List<Stat> statsList = new ArrayList<>();

    public Stat attack = new Stat("Támadás", 1);
    public Stat defense = new Stat("Védekezés", 1);
    public Stat magic = new Stat("Varázserö", 1);
    public Stat intelligence = new Stat("Tudás", 1);
    public Stat moral = new Stat("Morál", 1);
    public Stat luck = new Stat("Szerencse", 1);


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
