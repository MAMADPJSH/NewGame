package Game.Logger;

import Board.Position;
import Game.Player;

public interface GameEventListener {
    void onDiceRolled(int result);
    void onPlayerMoved(Player player, Position newPosition);
    void onGameWon(Player winner);
}

