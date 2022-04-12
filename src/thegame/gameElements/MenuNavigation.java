package thegame.gameElements;

import thegame.Main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuNavigation {
    public void pressEnterKey() {
        try {
            System.out.println("Folytatáshoz nyomj ENTER-t...");
            System.in.read();
        } catch (Exception e) {
        }
    }

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

    public void clearSceen() {
        ProcessBuilder pb;
        String osname = System.getProperty("os.name");
        try {
            if (osname.toLowerCase().contains("windows"))
                pb = new ProcessBuilder("cmd", "/c", "cls");
            else
                pb = new ProcessBuilder("clear");
            pb.inheritIO().start().waitFor();
            Thread.sleep(16L);
            System.out.flush();
        } catch (Exception e) {
            System.err.println(e);
        }
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
        while (uIn != 4) {
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
            }
        }
    }

    private void askBuyUnitsProcess() {
        Player player = Main.gameLogic.getPlayer(1);
        int uIn = -1;
        boolean isUserDone = false;
        while (uIn == -1) {
            System.out.println("Meglévö egységek:");
            for (UnitCell uc : player.ownedCells) {
                System.out.printf("%dx %s\n", uc.amount, uc.unit.name);
            }
            System.out.printf("Mit akarsz?\n\nAranyad: " + Main.gameLogic.getPlayer(1).getBalance() + "\n\n[1] Földműves (%d pénz)\n[2] Íjász (%d pénz)\n[3] Griff (%d pénz)\n\n[4] Vissza\n: ",
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
            System.out.println("Mennyit akarsz? (nyílván 0-nál többet)\n: ");
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
            pressEnterKey();
        }
        //pressEnterKey();
    }

    private boolean buyUnits(Player player, UnitCell unitCell) {
        Integer purchasePrice = unitCell.amount * unitCell.unit.getPrice();
        if (player.getBalance() >= purchasePrice) {
            player.setBalance(player.getBalance() - purchasePrice);
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
            System.out.println("Meglévö varázslatok:");
            for (Magic magic : player.ownedMagic) {
                System.out.printf("%s (%d mana)\n", magic.getName(), magic.getMana());
            }
            System.out.printf("Mit akarsz?\n\nAranyad: " + Main.gameLogic.getPlayer(1).getBalance() + "\n\n[1] Villámcsapás (%d pénz, %d mana)\n[2] Tűzlabda (%d pénz, %d mana)\n[3] Feltámadás (%d pénz, %d mana)\n\n[4] Vissza\n: ",
                    LightningBolt.price,
                    LightningBolt.mana,
                    Fireball.price,
                    Fireball.mana,
                    Revive.price,
                    Revive.mana);
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
        Magic magicToBuy = (new Magic[]{new LightningBolt(), new Fireball(), new Revive()})[uIn - 1];
        if (this.buyMagic(player, magicToBuy)) {
            System.out.println("Siker!");
        } else {
            System.out.println("Hiba! Erre nem telik, vagy már megvetted!");
            pressEnterKey();
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
                System.out.printf("Hiba! Erre nem telik, vagy a(z) %s tulajdonságod már 10-es szintű!\n",
                        chosenStat.name);
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


    public void printBoard(Board board) {
        System.out.print("0\s\s");
        for(int i = 1; i <= board.boardCells[0].length; i++) {
            if(i <= 9)
                System.out.print("\s" + i + "\s\s");
            else
                System.out.print("\s" + i + "\s");
        }
        System.out.println();
        int i = 1;
        for (UnitCell[] row : board.boardCells) {
            if(i <= 9)
                System.out.print(i + "\s|\s");
            else
                System.out.print(i + "|\s");
            for (UnitCell cell : row) {
                if(cell == null) {
                    System.out.print("\s\s|\s");
                } else {
                    System.out.print(cell.unit.letter + " |");
                }

            }
            System.out.println();
            i++;
        }
    }
}
