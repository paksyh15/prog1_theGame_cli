package thegame.gameElements;

import org.w3c.dom.Text;
import thegame.Main;
import thegame.errors.ExceptionNotOnBoard;
import thegame.gameElements.magic.Magic;
import thegame.gameElements.unit.UnitCell;

import java.util.ArrayList;
import java.util.Scanner;

public class TuiHandler {
    public static void pressEnterKey(String message) {
        try {
            System.out.println(message);
            Scanner sc = new Scanner(System.in);
            sc.nextLine();
        } catch (Exception e) {
        }
    }

    public static void pressEnterKey() {
        pressEnterKey("Folytatáshoz nyomj ENTER-t...");
    }

    public static void clearSceen() {
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
            //  :D
            System.out.print("\n\n\n\n\n\n\n\n");
        }
    }

    private static void printBoardLine() {
        // TODO: board szélessége alapján (hvi)
        System.out.print(TextColors.BLUE);
        for (int j = 0; j < 13; j++) {
            System.out.print("+");
            if (j < 12) System.out.print("- -");
        }
        System.out.print(TextColors.RESET);
    }

    public static void printBoard(Board board) {
        System.out.print("\s\s\s\s");
        for (int i = 1; i <= board.boardCells.length; i++) {
            if (i <= 9)
                System.out.print("\s" + i + "\s\s");
            else
                System.out.print("\s" + i + "\s");
        }
        System.out.print("\n   ");
        printBoardLine();
        System.out.print("\n");
        for (int i = 0; i < board.boardCells[0].length; i++) {
            if (i + 1 <= 9) System.out.print(i + 1 + TextColors.BLUE + "\s\s|\s" + TextColors.RESET);
            else System.out.print(i + 1 + TextColors.BLUE + "\s|\s" + TextColors.RESET);
            for (int j = 0; j < board.boardCells.length; j++) {
                UnitCell uc = board.boardCells[j][i];
                if (uc == null) {
                    System.out.print(TextColors.BLUE + "\s\s|\s" + TextColors.RESET);
                } else {
                    if (uc.owner == Main.gameLogic.getPlayer(1))
                        System.out.print(TextColors.GREEN);
                    else
                        System.out.print(TextColors.RED);
                    System.out.print(uc.unit.letter + TextColors.BLUE + "\s|\s" + TextColors.RESET);
                }
                System.out.print(TextColors.RESET);
            }
            System.out.print("\n   ");
            printBoardLine();
            System.out.print("\n");
        }
    }

    public static void printPlayerStats(Player player) {
        for (PlayerStats.Stat stat : player.stats.statsList) {
            System.out.printf("\s%s: %d\n", stat.getName(), stat.getValue());
        }
    }

    public static void printPlayerMana(Player player) {
        String strColor = (player == Main.gameLogic.getPlayer(1) ?
                TextColors.GREEN : TextColors.RED);
        System.out.print(strColor + "Mana: " + TextColors.RESET + player.getMana() + "\n");
    }

    public static void printPlayerMagics(Player player) {
        for (Magic magic : player.ownedMagic) {
            System.out.printf("\s%s (%d mana)\n", magic.getName(), magic.getMana());
        }
    }

    public static void printRoundLine() {
        System.out.printf("%d. kör\n", Main.gameLogic.numRound);
    }

    public static void printOrderedUnits(ArrayList<UnitCell> oUCs) {
        for (UnitCell uc : oUCs) {
            String colorStr;
            if (uc.owner == Main.gameLogic.getPlayer(1))
                colorStr = TextColors.GREEN;
            else
                colorStr = TextColors.RED;
            try {
                System.out.printf(colorStr + "%dx %s (utolsó hp: %d) [%d,%d]; ",
                        uc.amount,
                        uc.unit.name,
                        uc.edgeHP,
                        uc.getPosOnBoard(Main.gameLogic.board).getX() + 1,
                        uc.getPosOnBoard(Main.gameLogic.board).getY() + 1
                );
            } catch (ExceptionNotOnBoard e) {
                throw new RuntimeException(e); // elvileg soha
            }
        }
        System.out.print(TextColors.RESET + "\n");
    }

    public static int askWhatDo(UnitCell nextUnit) {
        System.out.println("Mit akarsz?");
        try {
            System.out.printf("[0] Cselekvés ezzel: %s\n[1] Varázslás\n[2] Támadás\n: ",
                    String.format("%dx %s (utolsó hp: %d) [%d,%d]; ",
                            nextUnit.amount,
                            nextUnit.unit.name,
                            nextUnit.edgeHP,
                            nextUnit.getPosOnBoard(Main.gameLogic.board).getX(),
                            nextUnit.getPosOnBoard(Main.gameLogic.board).getY()));
        } catch (ExceptionNotOnBoard e) {
            throw new RuntimeException(e); // elvileg soha
        }
        int uIn = -1;
        while (uIn == -1) {
            try {
                Scanner sc = new Scanner(System.in);
                uIn = sc.nextInt();
            } catch (Exception e) {
                uIn = -1;
            }
            if (!(uIn <= 1 && uIn >= 0)) {
                uIn = -1;
            }
        }
        return uIn;
    }

    public static int askWhatDoUnitCell(UnitCell uc) {
        System.out.print("Mit szeretnél csinálni az egységgel?\n");
        System.out.print("[0] Támadás\n[1] Speciális képesség\n[2] Mozgás\n[3] Semmi (várakozás)\n: ");
        int uIn = -1;
        while (uIn == -1) {
            try {
                Scanner sc = new Scanner(System.in);
                uIn = sc.nextInt();
            } catch (Exception e) {
                uIn = -1;
            }
            if (!(uIn <= 3 && uIn >= 0)) {
                uIn = -1;
            }
        }
        return uIn;
    }

    public static Position askPosition() {
        int uX = -1;
        while (uX == -1) {
            System.out.print("Oszlopszám: ");
            try {
                Scanner sc = new Scanner(System.in);
                uX = sc.nextInt();
            } catch (Exception e) {
                uX = -1;
            }
            if (!(uX <= 12 && uX >= 1)) {
                uX = -1;
            }
        }
        int uY = -1;
        while (uY == -1) {
            System.out.print("Sorszám: ");
            try {
                Scanner sc = new Scanner(System.in);
                uY = sc.nextInt();
            } catch (Exception e) {
                uY = -1;
            }
            if (!(uY <= 10 && uY >= 1)) {
                uY = -1;
            }
        }
        return new Position(uX - 1, uY - 1);
    }
}
