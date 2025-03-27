package Game.Rules;

import Board.Board;
import Board.Position;
import Game.Player;

public interface PlayerMovementRules {
    void applyRule(Player player, Position newPosition, Position currentPosition, Board board);
}
