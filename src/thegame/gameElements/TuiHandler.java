package thegame.gameElements;

import thegame.Main;
import thegame.gameElements.unit.UnitCell;

public class TuiHandler {
    public static void pressEnterKey(String message) {
        try {
            System.out.println(message);
            System.in.read();
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
        for(int j=0; j < 13; j++) {
            System.out.print("+");
            if(j<12) System.out.print("- -");
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
            for(int j = 0; j < board.boardCells.length; j++) {
                UnitCell uc = board.boardCells[j][i];
                if(uc == null) {
                    System.out.print(TextColors.BLUE + "\s\s|\s" + TextColors.RESET);
                } else {
                    if(uc.owner == Main.gameLogic.getPlayer(1))
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
}
