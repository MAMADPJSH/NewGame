package Game.Rules;

import Board.Board;
import Board.Position;
import Game.Player;
import Board.Color;

import java.util.HashMap;
import java.util.Map;

public class UndoRule implements PlayerMovementRules{

    private final Map<Integer, Color> moves = new HashMap<>();

    @Override
    public void applyRule(Player player, Position newPosition, Position currentPosition, Board board) {
        recordMove(currentPosition.getNumber(), player.getColor());
    }

    public void recordMove(int move, Color color) {
        moves.put(move, color);
    }
}
