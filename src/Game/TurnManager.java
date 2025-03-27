package Game;

import java.util.List;

public class TurnManager {
    private List<Player> players;
    private int currentIndex;

    public TurnManager(List<Player> players) {
        if (players == null || players.isEmpty()) {
            throw new IllegalArgumentException("Game.Player list cannot be null or empty.");
        }
        this.players = players;
        this.currentIndex = 0;
    }

    public Player getCurrentPlayer() {
        return players.get(currentIndex);
    }

    public void nextTurn() {
        currentIndex = (currentIndex + 1) % players.size();
    }
}
