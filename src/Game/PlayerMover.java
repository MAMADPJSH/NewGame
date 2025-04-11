package Game;

import Board.Board;
import Board.Position;
import Game.Logger.GameEventListener;
import Game.Rules.HitRule;
import Game.Rules.PlayerMovementRules;

import java.util.ArrayList;
import java.util.List;;

public class PlayerMover {
    private final Board board;
    private final List<PlayerMovementRules> rules;
    private final List<GameEventListener> listeners = new ArrayList<>();

    public PlayerMover(Board board, List<Player> players, List<PlayerMovementRules> rules) {
        this.board = board;
        this.rules = rules;
    }

    public void movePlayer(Player player, int roll) {
        Position newPosition = null;

        for (int i = roll; i > 0; i--) {
            Position currentPos = player.getPosition();
            int newPositionNumber = ((currentPos.getNumber() + player.getMovement() + player.getSkip() - 1) % board.getBoardSize()) + 1;
            newPosition = board.getPosition(newPositionNumber);
            player.setPosition(newPosition);

            boolean isLastStep = (i == 1);
            for (PlayerMovementRules rule : rules) {
                rule.applyRule(player, newPosition, currentPos, board, isLastStep);
            }
            notifyPlayerMoved(player, newPosition);
        }

        player.setMovement(1);

    }

    public void addGameEventListener(GameEventListener listener) {
        listeners.add(listener);
    }

    private void notifyPlayerMoved(Player player, Position newPosition) {
        for (GameEventListener listener : listeners) {
            listener.onPlayerMoved(player, newPosition);
        }
    }
}

