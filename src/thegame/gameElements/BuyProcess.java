package thegame.gameElements;

import thegame.Main;
import thegame.gameElements.magic.*;
import thegame.gameElements.unit.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BuyProcess {


    public int askDifficulty() {  // 0 / 1 / 2 / -1 err
        System.out.print("Nehézség\n\t[0] (könnyü)\n\t[1] (közepes)\n\t[2] Nehéz\n: ");
        Scanner sc = new Scanner(System.in);
        int inDiff = -1;
        try {
            inDiff = sc.nextInt();
        } catch (InputMismatchException ime) {
        }
        if (inDiff > 2) inDiff = -1;
        else if (inDiff < 0) inDiff = -1;
        return inDiff;
    }



    @Deprecated
    public void printPlayerStats(Player player, boolean isMenu) {
        if (!isMenu)
            System.out.printf("Támadás: %d\nVédekezés: %d\nVarázserő: %d\nTudás: %d\nMorál: %d\nSzerencse: %d\n", player.stats.attack.value, player.stats.defense.value, player.stats.magic.value, player.stats.intelligence.value, player.stats.moral.value, player.stats.luck.value);
        else
            System.out.printf("[1] Támadás: %d\n[2] Védekezés: %d\n[3] Varázserő: %d\n[4] Tudás: %d\n[5] Morál: %d\n[6] Szerencse: %d\n", player.stats.attack.value, player.stats.defense.value, player.stats.magic.value, player.stats.intelligence.value, player.stats.moral.value, player.stats.luck.value);
    }

    public void askChooseBuy() {
        int uIn = -1;
        Player player = Main.gameLogic.getPlayer(1);
        while (uIn != 4) {
            TuiHandler.clearSceen();
            System.out.println("Válassz!\n\nAranyad: " + Main.gameLogic.getPlayer(1).getBalance() + "\n\n[1] Egységek vásárlása\n[2] Varázslatok vásárlása\n[3] Képességpontok vásárlása\n[4] Kész\n: ");
            Scanner sc = new Scanner(System.in);
            try {
                uIn = sc.nextInt();
            } catch (Exception e) {
                uIn = -1;
                continue;
            }
            switch (uIn) {
                case 1 -> this.askBuyUnitsProcess();
                case 2 -> this.askBuyMagicProcess();
                case 3 -> this.askBuyAttrsProcess();
                case 4 -> {
                    if(player.ownedCells.isEmpty()) {
                        System.out.println("Hiba! Egység(ek) nélkül nem kezdhetsz játékot!");
                        TuiHandler.pressEnterKey();
                        uIn = -1;
                        continue;
                    }
                }
            }
        }
    }

    private void askBuyUnitsProcess() {
        Player player = Main.gameLogic.getPlayer(1);
        int uIn = -1;
        boolean isUserDone = false;
        while (uIn == -1) {
            TuiHandler.clearSceen();
            System.out.println("Meglévö egységek:");
            for (UnitCell uc : player.ownedCells) {
                System.out.printf("%dx %s\n", uc.amount, uc.unit.name);
            }
            System.out.printf("Mit akarsz?\n\nAranyad: " + Main.gameLogic.getPlayer(1).getBalance() + "\n\n[1] Földmüves (%d pénz)\n[2] Ijász (%d pénz)\n[3] Griff (%d pénz)\n\n[4] Vissza\n: ",
                    Peasant.price,
                    Archer.price,
                    Griffin.price);
            Scanner sc = new Scanner(System.in);
            try {
                uIn = sc.nextInt();
            } catch (Exception e) {
                uIn = -1;
            }
            if (!(uIn <= 4 && uIn >= 1)) uIn = -1;
            if (uIn == 4) isUserDone = true;
        }
        if (isUserDone) return;
        Unit unitToBuy = (new Unit[]{new Peasant(), new Archer(), new Griffin()})[uIn - 1];
        uIn = -1;
        while (uIn == -1) {
            TuiHandler.clearSceen();
            System.out.printf("Mennyit akarsz ebből: %s? (nyilván 0-nál többet)\n: ", unitToBuy.name);
            Scanner sc = new Scanner(System.in);
            try {
                uIn = sc.nextInt();
            } catch (Exception e) {
                uIn = -1;
            }
        }
        int amountToBuy = uIn;
        UnitCell unitCell = new UnitCell(unitToBuy, amountToBuy);
        if (this.buyUnits(player, unitCell)) {
            System.out.println("Siker!");
        } else {
            System.out.println("Hiba! Erre nem telik!");
            TuiHandler.pressEnterKey();
        }
        //pressEnterKey();
    }

    private boolean buyUnits(Player player, UnitCell unitCell) {
        Integer purchasePrice = unitCell.amount * unitCell.unit.getPrice();
        if (player.getBalance() >= purchasePrice) {
            player.setBalance(player.getBalance() - purchasePrice);
            unitCell.owner = player;
            player.ownedCells.add(unitCell);
            return true;
        }
        return false;
    }


    private void askBuyMagicProcess() {
        Player player = Main.gameLogic.getPlayer(1);
        int uIn = -1;
        boolean isUserDone = false;
        while (uIn == -1) {
            TuiHandler.clearSceen();
            System.out.println("Meglévö varázslatok:");
            for (Magic magic : player.ownedMagic) {
                System.out.printf("%s (%d mana)\n", magic.getName(), magic.getMana());
            }
            System.out.printf("Mit akarsz?\n\nAranyad: " + Main.gameLogic.getPlayer(1).getBalance() + "\n\n[1] Villámcsapás (%d pénz, %d mana)\n[2] Tüzlabda (%d pénz, %d mana)\n[3] Feltámadás (%d pénz, %d mana)\n[4] Egységtörlés (%d pénz, %d mana)\n[5] Saját egység teleportálása (%d pénz, %d mana)\n\n[6] Vissza\n: ",
                    LightningBolt.price,
                    LightningBolt.mana,
                    Fireball.price,
                    Fireball.mana,
                    Revive.price,
                    Revive.mana,
                    DeleteUnit.price,
                    DeleteUnit.mana,
                    TeleportUnit.price,
                    TeleportUnit.mana);
            Scanner sc = new Scanner(System.in);
            try {
                uIn = sc.nextInt();
            } catch (Exception e) {
                uIn = -1;
            }
            if (!(uIn <= 6 && uIn >= 1)) uIn = -1;
            if (uIn == 6) isUserDone = true;
        }
        if (isUserDone) return;
        Magic magicToBuy = (new Magic[]{new LightningBolt(), new Fireball(), new Revive(), new DeleteUnit(), new TeleportUnit()})[uIn - 1];
        if (this.buyMagic(player, magicToBuy)) {
            System.out.println("Siker!");
        } else {
            System.out.println("Hiba! Erre nem telik, vagy már megvetted!");
            TuiHandler.pressEnterKey();
        }
    }

    private boolean buyMagic(Player player, Magic magic) {
        for (Magic m : player.ownedMagic) {
            if (m.getName().equals(magic.getName()))
                return false;
        }
        if (player.getBalance() >= magic.getPrice()) {
            player.setBalance(player.getBalance() - magic.getPrice());
            player.ownedMagic.add(magic);
            return true;
        }
        return false;
    }


    private void askBuyAttrsProcess() {
        Player player = Main.gameLogic.getPlayer(1);
        int uIn;
        boolean isUserDone = false;
        while (!isUserDone) {
            TuiHandler.clearSceen();
            uIn = -1;
            while (uIn == -1) {
            /*System.out.println("Jelenlegi tulajdonságpontjaid:");
            for (PlayerStats.Stat stat : player.stats.statsList) {
                System.out.printf("%s - %d / 10\n", stat.name, stat.value);
            }*/
                System.out.printf("Mit akarsz?\n\nAranyad: " + Main.gameLogic.getPlayer(1).getBalance() + "\nKövetkező tulajdonságpont ára: %d arany\n\n[1] Támadás - jelenlegi szint: %d\n[2] Védekezés - jelenlegi szint: %d\n[3] Varázserő - jelenlegi szint: %d\n[4] Tudás - jelenlegi szint: %d\n[5] Morál - jelenlegi szint: %d\n[6] Szerencse - jelenlegi szint: %d\n\n[7] Kész: ",
                        player.attrPrice,
                        player.stats.attack.value,
                        player.stats.defense.value,
                        player.stats.magic.value,
                        player.stats.intelligence.value,
                        player.stats.moral.value,
                        player.stats.luck.value);
                Scanner sc = new Scanner(System.in);
                try {
                    uIn = sc.nextInt();
                } catch (Exception e) {
                    uIn = -1;
                }
                if (!(uIn <= 7 && uIn >= 1)) uIn = -1;
                if (uIn == 7) isUserDone = true;
            }
            if (isUserDone) return;
            PlayerStats.Stat chosenStat = (new PlayerStats.Stat[]{
                    player.stats.attack,
                    player.stats.defense,
                    player.stats.magic,
                    player.stats.intelligence,
                    player.stats.moral,
                    player.stats.luck
            })[uIn - 1];
            if (this.buyAttr(player, chosenStat)) {
                System.out.println("Siker!");
            } else {
                System.out.printf("Hiba! Erre nem telik, vagy a(z) %s tulajdonságod már 10-es szintü!\n",
                        chosenStat.name);
                TuiHandler.pressEnterKey();
            }
        }
    }

    private boolean buyAttr(Player player, PlayerStats.Stat stat) {
        PlayerStats.Stat playerStat = null;
        for (PlayerStats.Stat stat2 : player.stats.statsList) {
            if (stat2.name.equals(stat.name)) {
                playerStat = stat2;
                break;
            }
        }
        if (player.getBalance() - player.attrPrice < 0 || stat.value >= 10)
            return false;
        player.setBalance(player.getBalance() - player.attrPrice);
        playerStat.setValue(playerStat.getValue() + 1);
        player.attrPrice = (int) (Math.ceil(player.attrPrice.doubleValue() * 1.1) + 0.5);
        return true;
    }



}
