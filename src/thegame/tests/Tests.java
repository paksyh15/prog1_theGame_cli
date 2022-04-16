package thegame.tests;

import org.junit.jupiter.api.*;
import thegame.Main;
import thegame.gameElements.BuyProcess;
import thegame.gameElements.GameLogic;
import thegame.gameElements.Player;
import thegame.gameElements.magic.*;
import thegame.gameElements.unit.Peasant;
import thegame.gameElements.unit.Tank;
import thegame.gameElements.unit.Unit;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {
    // 1
    @Test
    void testMagicFireballGetMana() {
        Magic fb = new Fireball();
        assertEquals(9, fb.getMana());
    }
    @Test
    void testMagicFireballGetName() {
        Magic fb = new Fireball();
        assertEquals("Tüzgolyó", fb.getName());
    }
    @Test
    void testMagicFireballGetPrice() {
        Magic fb = new Fireball();
        assertEquals(120, fb.getPrice());
    }
    // 2
    @Test
    void testPlayerGetterOne() {
        assertEquals(Main.gameLogic.getPlayer(1), Main.gameLogic.player1);
    }
    @Test
    void testPlayerGetterTwo() {
        assertEquals(Main.gameLogic.getPlayer(2), Main.gameLogic.player2);
    }
    @Test
    void testPlayerGetterWrong() {
        assertNull(Main.gameLogic.getPlayer(3));
    }
    // 3
    @Test
    void testDifficultySetter1() {
        GameLogic gl = new GameLogic();
        assertTrue(gl.setDifficulty(0));
        assertEquals(1300, gl.player1.money);
    }
    @Test
    void testDifficultySetter2() {
        GameLogic gl = new GameLogic();
        assertTrue(gl.setDifficulty(1));
        assertEquals(1000, gl.player1.money);
    }
    @Test
    void testDifficultySetter3() {
        GameLogic gl = new GameLogic();
        assertTrue(gl.setDifficulty(2));
        assertEquals(700, gl.player1.money);
    }
    @Test
    void testDifficultySetterWrong() {
        GameLogic gl = new GameLogic();
        assertFalse(gl.setDifficulty(3));
    }
    // 4
    @Test
    void testBuyMagics1() { // buyMagic tesztelése
        GameLogic gl = new GameLogic();
        gl.setDifficulty(0);
        BuyProcess bp = new BuyProcess();
        Magic magicToBuy[] = (new Magic[]{new LightningBolt(), new Fireball(), new Revive(), new DeleteUnit(), new TeleportUnit()});
        ArrayList<Magic> ml = new ArrayList<>();
        for(int i = 0; i < magicToBuy.length; i++) {
            bp.buyMagic(gl.player1, magicToBuy[i]);
            ml.add(magicToBuy[i]);
        }
        assertEquals(ml, gl.player1.ownedMagic);
    }
    @Test
    void testBuyMagics2() { // buyMagic tesztelése
        GameLogic gl = new GameLogic();
        gl.setDifficulty(0);
        BuyProcess bp = new BuyProcess();
        Magic magicToBuy[] = (new Magic[]{new LightningBolt(), new Fireball(), new Revive(), new DeleteUnit(), new TeleportUnit()});
        ArrayList<Magic> ml = new ArrayList<>();
        for(int i = 0; i < magicToBuy.length-3; i++) {
            bp.buyMagic(gl.player1, magicToBuy[i]);
            ml.add(magicToBuy[i]);
        }
        assertEquals(ml, gl.player1.ownedMagic);
    }
    // 5
    @Test
    void testUnitGetPrice1() {
        Unit asd = new Peasant();
        assertEquals(2, asd.getPrice());
    }
    @Test
    void testUnitGetPrice2() {
        Unit asd = new Tank();
        assertEquals(10, asd.getPrice());
    }
    // 6
    @Test
    void testUnitGetInitiative1() {
        Unit asd = new Peasant();
        assertEquals(8, asd.getInitiative());
    }
    @Test
    void testUnitGetInitiative2() {
        Unit asd = new Tank();
        assertEquals(8, asd.getInitiative());
    }
    // 7
    @Test
    void testUnitGetSpeed1() {
        Unit asd = new Peasant();
        assertEquals(4, asd.getSpeed());
    }
    @Test
    void testUnitGetSpeed2() {
        Unit asd = new Tank();
        assertEquals(3, asd.getSpeed());
    }
    // 8
    @Test
    void testUnitGetDamageMin1() {
        Unit asd = new Peasant();
        assertEquals(1, asd.getDamageMin());
    }
    @Test
    void testUnitGetDamageMin2() {
        Unit asd = new Tank();
        assertEquals(0, asd.getDamageMin());
    }
    // 9
    @Test
    void testUnitGetDamageMax1() {
        Unit asd = new Peasant();
        assertEquals(1, asd.getDamageMax());
    }
    @Test
    void testUnitGetDamageMax2() {
        Unit asd = new Tank();
        assertEquals(1, asd.getDamageMax());
    }
    // 10
    @Test
    void testUnitGetHealth1() {
        Unit asd = new Peasant();
        assertEquals(3, asd.getHealth());
    }
    @Test
    void testUnitGetHealth2() {
        Unit asd = new Tank();
        assertEquals(42, asd.getHealth());
    }
}
