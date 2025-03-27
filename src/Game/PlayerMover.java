package Game;

import Board.Board;

import java.util.ArrayList;
import java.util.List;;

public class PlayerMover {
    private Board board;
    private List<Player> players;
    private boolean hitRuleActive;
    private List<GameEventListener> listeners = new ArrayList<>();

    public PlayerMover(Board board, List<Player> players, boolean hitRuleActive) {
        this.board = board;
        this.players = players;
        this.hitRuleActive = hitRuleActive;
    }

    // Register observers to be notified when a player moves.
    public void addGameEventListener(GameEventListener listener) {
        listeners.add(listener);
    }

    public void movePlayer(Player player, int roll) {
        Position currentPos = player.getPosition();
        Position newPosition = null;
        int movement = 1;
        int skip = 0;

        for (int i = 0; i < roll; i++) {
            int newPositionNumber = ((currentPos.getNumber() + movement + skip - 1) % board.getBoardSize()) + 1;
            newPosition = board.getPosition(newPositionNumber);
            player.setPosition(newPosition);
            currentPos = newPosition;

            // Overshoot logic: if player overshoots the END, reverse direction.
            if (newPosition.getType() == PositionType.END && roll > i + 1) {
                movement = -1;
                System.out.println(player.getColor() + " overshoots and moves back to " + newPosition);
            }

            // Skip logic: if next position is a tail of an opponent, skip positions.
            int nextPosNum = ((newPositionNumber) % board.getBoardSize()) + 1;
            Position nextPos = board.getPosition(nextPosNum);
            if (nextPos.getType() == PositionType.TAIL && nextPos.getOwner() != player.getColor()) {
                skip = board.getTailLength();
                System.out.println(player.getColor() + " skips " + nextPos.getOwner() + "'s tail");
            } else {
                skip = 0;
            }
        }

        // Hit logic: if another player is on the landing position, send them back to home.
        if (hitRuleActive) {
            for (Player otherPlayer : players) {
                if (otherPlayer != player && otherPlayer.getPosition() == newPosition) {
                    System.out.println(player.getColor() + " hits " + otherPlayer.getColor() + " back to home");
                    otherPlayer.setPosition(board.getHomePosition(otherPlayer.getColor()));
                }
            }
        }

        notifyPlayerMoved(player, newPosition);
    }

    private void notifyPlayerMoved(Player player, Position newPosition) {
        for (GameEventListener listener : listeners) {
            listener.onPlayerMoved(player, newPosition);
        }
    }
}

