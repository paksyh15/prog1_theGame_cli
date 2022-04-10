package thegame.gameElements;

import thegame.Main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuNavigation {
    public void pressAnyKey() {
        try {
            System.in.read();
            System.out.println("Folytatáshoz nyomj meg egy gombot...");
        } catch (Exception e) {
        }
    }

    public int askDifficulty() {  // 0 / 1 / 2 / -1 err
        System.out.printf("Nehézség\n\t[0] (könnyü)\n\t[1] (közepes)\n\t[2] Nehéz\n: ");
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

    public void printPlayerStats(Player player, boolean isMenu) {
        if (!isMenu)
            System.out.printf("Támadás: %d\nVédekezés: %d\nVarázserő: %d\nTudás: %d\nMorál: %d\nSzerencse: %d\n", player.stats.attack, player.stats.defense, player.stats.magic, player.stats.intelligence, player.stats.moral, player.stats.luck);
        else
            System.out.printf("[1] Támadás: %d\n[2] Védekezés: %d\n[3] Varázserő: %d\n[4] Tudás: %d\n[5] Morál: %d\n[6] Szerencse: %d\n", player.stats.attack, player.stats.defense, player.stats.magic, player.stats.intelligence, player.stats.moral, player.stats.luck);
    }

    public void askChooseBuy() {

        Scanner sc = new Scanner(System.in);
        int uIn = -1;
        while (uIn != 4) {
            System.out.println("Válassz!\n\nPénzed: " + Main.gameLogic.getPlayer(1).getBalance() + "\n\n[1] Egységek vásárlása\n[2] Varázslatok vásárlása\n[3] Képességpontok vásárlása\n[4] Kész\n: ");
            try {
                uIn = sc.nextInt();
            } catch (Exception e) {
                uIn = -1;
            }
            switch (uIn) {
                case 1 -> askBuyUnitsProcess();
                case 2 -> askBuyMagicProcess();
                case 3 -> askBuyAttrsProcess();
            }
        }
    }

    private void askBuyUnitsProcess() {
        Player player = Main.gameLogic.getPlayer(1);
        int uIn = -1;
        boolean isUserDone = false;
        while (uIn == -1) {
            System.out.println("Meglévö egységek:\n");
            for (UnitCell uc : player.ownedCells) {
                System.out.printf("%dx %s\n", uc.amount, uc.unit.name);
            }
            System.out.printf("Mit akarsz?\n[1] Földműves (%d pénz)\n[2] Íjász(%d pénz)\n[3] Griff(%d pénz)\n\n[4] Vissza\n: ", Peasant.price, Archer.price, Griffin.price);
            Scanner sc = new Scanner(System.in);
            try {
                uIn = sc.nextInt();
            } catch (Exception e) {
                uIn = -1;
            }
            if (!(uIn <= 4 && uIn >= 1)) uIn = -1;
            if(uIn == 4) isUserDone = true;
        }
        if(isUserDone) return;
        Unit unitToBuy = new Unit[]{new Peasant(), new Archer(), new Griffin()}[uIn - 1];
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
        if (buyUnits(Main.gameLogic.getPlayer(1), unitCell)) {
            System.out.println("Siker!");
        } else {
            System.out.println("Hiba! Erre nem telik!");
        }
        pressAnyKey();
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
        //
    }


    private void askBuyAttrsProcess() {

    }


    public void printBoard() {

    }
}
