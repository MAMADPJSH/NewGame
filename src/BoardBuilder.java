import java.util.List;

public class BoardBuilder {
    private int boardSize;
    private int tailLength;
    private List<Color> players;

    public BoardBuilder setBoardSize(int boardSize) {
        this.boardSize = boardSize;
        return this;
    }

    public BoardBuilder setTailLength(int tailLength) {
        this.tailLength = tailLength;
        return this;
    }

    public BoardBuilder setPlayers(List<Color> players) {
        this.players = players;
        return this;
    }

    // Build and return a new Board
    public Board build() {
        if (boardSize <= 0) {
            throw new IllegalArgumentException("Board size must be positive.");
        }
        if (tailLength <= 0) {
            throw new IllegalArgumentException("Tail length must be positive.");
        }
        if (players == null || players.isEmpty()) {
            throw new IllegalArgumentException("At least one player is required.");
        }
        return new Board(boardSize, tailLength, players);
    }
}

