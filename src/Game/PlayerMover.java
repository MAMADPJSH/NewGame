package Game;

import Board.Board;
import Board.Position;
import Board.PositionType;
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
        int totalPlays = 0;
    }

    public void movePlayer(Player player, int roll) {
        Position currentPos = player.getPosition();
        Position newPosition = null;

        for (int i = 0; i < roll; i++) {
            int newPositionNumber = ((currentPos.getNumber() + player.getMovement() + player.getSkip() - 1) % board.getBoardSize()) + 1;
            newPosition = board.getPosition(newPositionNumber);
            player.setPosition(newPosition);

            for (PlayerMovementRules rule : rules) {
                if (rule instanceof HitRule && i < roll - 1) {
                    continue;
                }
                rule.applyRule(player, newPosition, currentPos, board);
            }
        }

        notifyPlayerMoved(player, newPosition);

    }

    // Register observers to be notified when a player moves.
    public void addGameEventListener(GameEventListener listener) {
        listeners.add(listener);
    }

    private void notifyPlayerMoved(Player player, Position newPosition) {
        for (GameEventListener listener : listeners) {
            listener.onPlayerMoved(player, newPosition);
        }
    }
}

