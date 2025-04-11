package Game.Rules;

import Board.Board;
import Board.Position;
import Board.PositionType;
import Game.Player;

public class NoOvershootRule implements PlayerMovementRules{

    @Override
    public void applyRule(Player player, Position newPosition, Position currentPosition, Board board, boolean isLastStep) {
        if (player.getColor() == currentPosition.getOwner() && newPosition.getType() == PositionType.END && !isLastStep) {
            player.setMovement(0);
        }
    }
}
