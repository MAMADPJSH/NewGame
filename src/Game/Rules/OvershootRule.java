package Game.Rules;

import Board.Board;
import Board.Position;
import Board.PositionType;
import Game.Player;


public class OvershootRule implements PlayerMovementRules {
    @Override
    public void applyRule(Player player, Position newPosition, Position currentPosition, Board board, boolean isLastStep) {
        if (newPosition.getType() == PositionType.END && !isLastStep) {
            System.out.println(player.getColor() + " overshoots and moves back.");
            player.setMovement(-1);
        } else if (player.getMovement() != -1) {
            player.setMovement(1);
        }
    }
}
