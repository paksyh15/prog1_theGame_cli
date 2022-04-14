package thegame.gameElements;


import thegame.Main;
import thegame.gameElements.magic.DeleteUnit;
import thegame.gameElements.magic.LightningBolt;
import thegame.gameElements.magic.Revive;
import thegame.gameElements.unit.*;

import java.util.ArrayList;
import java.util.Random;

public class BuyProcessBot {
    Player player;
    Random random;

    public BuyProcessBot(Random rnd) {
        this.player = Main.gameLogic.getPlayer(2);
        this.random = rnd;
    }

    public void doStuff() {
        boolean magicExtra = this.random.nextInt(0, 2) == 1;
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
        for (int i = 0; i < (extra ? 7 : 13); i++) {
            int rndStatIndex = this.random.nextInt(0, (int) this.player.stats.statsList.stream().count());
            if (extra && this.player.stats.statsList.get(rndStatIndex).name.equals(this.player.stats.intelligence.name)) {
                // nem veszünk tudást mer má túl okos
                i--;
                continue;
            } else {
                Main.buyProcess.buyAttr(this.player, this.player.stats.statsList.get(rndStatIndex));
            }
        }
    }

    private class UnitPattern {
        public ArrayList<UnitCell> unitCellArrayList = new ArrayList<>();
        public ArrayList<Position> poses = new ArrayList<>();

        public UnitPattern() {
        }

        public void addUnitCell(UnitCell uc) {
            unitCellArrayList.add(uc);
        }

        public void addPos(Position pos) {
            poses.add(pos);
        }
    }

    private void buyUnits() {
        //pattern-ök beizélése; itt 485 pénze biztos van
        UnitPattern[] unitPatterns = new UnitPattern[2];
        for (int i = 0; i < unitPatterns.length; i++) unitPatterns[i] = new UnitPattern();
        unitPatterns[0].addUnitCell(new UnitCell(new Griffin(), 15)); // 260
        unitPatterns[0].addUnitCell(new UnitCell(new Assassin(), 15)); // 80
        unitPatterns[0].addUnitCell(new UnitCell(new Peasant(), 20)); // 40
        unitPatterns[0].addUnitCell(new UnitCell(new Peasant(), 20)); // 0
        unitPatterns[0].addPos(new Position(11, 7));
        unitPatterns[0].addPos(new Position(11, 4));
        unitPatterns[0].addPos(new Position(12, 3));
        unitPatterns[0].addPos(new Position(12, 8));
        unitPatterns[1].addUnitCell(new UnitCell(new Griffin(), 15)); // 260
        unitPatterns[1].addUnitCell(new UnitCell(new Assassin(), 15)); // 80
        unitPatterns[1].addUnitCell(new UnitCell(new Peasant(), 20)); // 40
        unitPatterns[1].addUnitCell(new UnitCell(new Peasant(), 20)); // 0
        unitPatterns[1].addPos(new Position(11, 5));
        unitPatterns[1].addPos(new Position(11, 6));
        unitPatterns[1].addPos(new Position(12, 4));
        unitPatterns[1].addPos(new Position(12, 7));
        //  :D
        int rndChoice = this.random.nextInt(0, unitPatterns.length);
        int length = unitPatterns[rndChoice].poses.size();
        for (int i = 0; i < length; i++) {
            Main.buyProcess.buyUnit(this.player, unitPatterns[rndChoice].unitCellArrayList.get(i));
            Position pos = unitPatterns[rndChoice].poses.get(i);
            Main.gameLogic.board.boardCells[pos.getX()-1][pos.getY()-1] = unitPatterns[rndChoice].unitCellArrayList.get(i);
        }
    }
}
