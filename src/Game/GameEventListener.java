package Game;

import Board.Position;

public interface GameEventListener {
    void onDiceRolled(int result);
    void onPlayerMoved(Player player, Position newPosition);
    void onGameWon(Player winner);
}

