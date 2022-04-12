package thegame;

import thegame.gameElements.GameLogic;
import thegame.gameElements.MenuNavigation;

public class Main {

    public static GameLogic gameLogic = new GameLogic();
    public static MenuNavigation meNav = new MenuNavigation();

    public static void main(String[] args) {

        meNav.clearSceen();
        // heló, nehézségválasztás
        boolean cont = false;
        while (!cont) {
            int diff = meNav.askDifficulty();
            if(diff == -1) {
                System.out.println("Érvénytelen nehézség!");
                continue;
            }
            gameLogic.setDifficulty(diff);
            cont = true;
        }
        System.out.printf("Nehézség beállítva. Kezdöarany: %d\n\n", gameLogic.getPlayer(1).getBalance());

        // TODO: esetleg a buy meg izé methodokat a GameLogicba, de csak ha ráérek, de igazából nem fontos mert játék közbe nem lehet venni és akkor nem GameLogic kinda
        // mindenféle vásárlásolás
        meNav.askChooseBuy();

    }
}
