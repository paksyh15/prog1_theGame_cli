package thegame.gameElements;


import thegame.Main;
import thegame.gameElements.magic.DeleteUnit;
import thegame.gameElements.magic.LightningBolt;
import thegame.gameElements.magic.Revive;
import thegame.gameElements.unit.*;

import java.util.Random;

public class BuyProcessBot {
    Player player;
    Random random;

    public BuyProcessBot(Random rnd) {
        this.player = Main.gameLogic.getPlayer(2);
        this.random = rnd;
    }

    public void doStuff() {
        boolean magicExtra = this.random.nextBoolean();
        this.buyMagics(magicExtra);
        this.buyStats(magicExtra);
        this.buyUnits();
    }



    public void buyMagics(boolean extra) {
        Main.buyProcess.buyMagic(this.player, new LightningBolt());
        Main.buyProcess.buyMagic(this.player, new Revive());
        if (extra) Main.buyProcess.buyMagic(this.player, new DeleteUnit());
    }

    public void buyStats(boolean extra) {
        if (extra) {
            for (int i = 0; i < 6; i++)
                Main.buyProcess.buyAttr(this.player, this.player.stats.intelligence);
        }
        // 7 még ez; 15 g, 20, í, 20 í, 10 paraszt
        for(int i = 0; i < 7; i++) {
            int rndStatIndex = this.random.nextInt(0, (int)this.player.stats.statsList.stream().count() + 1);
            if(this.player.stats.statsList.get(rndStatIndex).name.equals(this.player.stats.intelligence.name)) {
                // nem veszünk tudást mer má túl okos
                i--;
                continue;
            }
        }
    }

    private void buyUnits() {

    }
}
