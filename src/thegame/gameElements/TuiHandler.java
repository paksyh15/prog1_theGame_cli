package thegame.gameElements;

public class TuiHandler {
    public static void pressEnterKey() {
        try {
            System.out.println("Folytatáshoz nyomj ENTER-t...");
            System.in.read();
        } catch (Exception e) {
        }
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
            System.err.println(e.toString());
        }
    }

    public static void printBoard(Board board) {
        System.out.print("\s\s\s\s");
        for (int i = 1; i <= board.boardCells.length; i++) {
            if (i <= 9)
                System.out.print("\s" + i + "\s\s");
            else
                System.out.print("\s" + i + "\s");
        }
        System.out.print("\n");
        for (int i = 0; i < board.boardCells[0].length; i++) {
            if (i + 1 <= 9) System.out.print(i + 1 + "\s\s|\s");
            else System.out.print(i + 1 + "\s|\s");
            for(int j = 0; j < board.boardCells.length; j++) {
                UnitCell uc = board.boardCells[j][i];
                if(uc == null) {
                    System.out.print("\s\s|\s");
                } else {
                    System.out.print(uc.unit.letter + "\s|\s");
                }
            }
            System.out.print("\n");
        }
    }
}
