package Game.Rules;

import Board.Board;
import Board.Position;
import Board.PositionType;
import Game.Player;


public class OvershootRule implements PlayerMovementRules {
    @Override
    public void applyRule(Player player, Position newPosition, Board board) {
        if (newPosition.getType() == PositionType.END) {
            System.out.println(player.getColor() + " overshoots and moves back.");
            player.setMovement(-1);
        } else {
            player.setMovement(1);
        }
    }
}
