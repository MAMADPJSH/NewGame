package Game;

import java.util.List;

public class TurnManager {
    private final List<Player> players;
    private int currentIndex;
    private int turnCount;
    private int individualTurn;

    public TurnManager(List<Player> players) {
        if (players == null || players.isEmpty()) {
            throw new IllegalArgumentException("Game.Player list cannot be null or empty.");
        }
        this.players = players;
        this.currentIndex = 0;
        this.turnCount = 1;
        this.individualTurn = 0;
    }

    public Player getCurrentPlayer() {
        return players.get(currentIndex);
    }

    public void nextTurn() {
        currentIndex = (currentIndex + 1) % players.size();
    }

    public int getTurnCount() {
        return turnCount;
    }
    public void incrementTurnCount() {
        turnCount++;
    }

    public int getIndividualTurn() {
        return individualTurn;
    }
    public void incrementIndividualTurnCount() {
        individualTurn++;
    }

    public boolean isLastPlayer() {
        return currentIndex == players.size() - 1;
    }
}
