package Game.Rules;

import Board.Board;
import Board.Position;
import Game.Player;

import java.util.List;

public class HitRule implements PlayerMovementRules {
    private final List<Player> players;

    public HitRule(List<Player> players) {
        this.players = players;
    }

    @Override
    public void applyRule(Player player, Position newPosition, Board board) {
        for (Player otherPlayer : players) {
            if (otherPlayer != player && otherPlayer.getPosition() == newPosition) {
                System.out.println(player.getColor() + " hits " + otherPlayer.getColor() + " back to home.");
                otherPlayer.setPosition(board.getHomePosition(otherPlayer.getColor()));
            }
        }
    }
}

