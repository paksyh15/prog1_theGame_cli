package thegame.gameElements;

public class SearchPattern {
    final Position[] searchPattern;

    public SearchPattern() {
        searchPattern = new Position[]{new Position(1, 0),
                new Position(1, 1),
                new Position(0, 1),
                new Position(-1, 1),
                new Position(-1, 0),
                new Position(-1, -1),
                new Position(0, -1),
                new Position(1, -1)};
    }
}
