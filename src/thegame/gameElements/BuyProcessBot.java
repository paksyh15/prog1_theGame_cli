package thegame.gameElements;


import thegame.Main;

import java.util.Random;

public class BuyProcessBot {
    Player player;
    Random random;

    public BuyProcessBot(Random rnd) {
        this.player = Main.gameLogic.getPlayer(2);
        this.random = rnd;
    }

    public void buyMagics(int amount) {

    }
}
