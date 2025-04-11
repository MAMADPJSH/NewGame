package Game;

import java.util.ArrayList;
import java.util.List;

import Board.Board;
import Board.PositionType;
import Game.Dice.DiceShaker;
import Game.Logger.GameEventListener;
import Game.Rules.PlayerMovementRules;

public class GameController {
    private final DiceShaker diceRoller;
    private final TurnManager turnManager;
    private final PlayerMover playerMover;
    private final List<GameEventListener> listeners = new ArrayList<>();

    public GameController(Board board, List<Player> players, List<PlayerMovementRules> rules, DiceShaker diceRoller) {
        this.diceRoller = diceRoller;
        this.turnManager = new TurnManager(players);
        this.playerMover = new PlayerMover(board, players, rules);
    }

    public void addGameEventListener(GameEventListener listener) {
        listeners.add(listener);
        playerMover.addGameEventListener(listener);
    }

    public void startGame() {
        boolean gameWon = false;

        notifyGameStart();

        while (!gameWon) {
            Player currentPlayer = turnManager.getCurrentPlayer();
            notifyPlayerTurnStart(currentPlayer, turnManager.getIndividualTurn());

            int diceRoll = diceRoller.roll();
            notifyDiceRolled(diceRoll);

            playerMover.movePlayer(currentPlayer, diceRoll);
            turnManager.incrementTurnCount();

            // Check win condition
            if (currentPlayer.getPosition().getType() == PositionType.END
                    && currentPlayer.getPosition().getOwner() == currentPlayer.getColor()) {
                notifyGameWon(currentPlayer);
                notifyTurnCountUpdated(turnManager.getTurnCount());
                gameWon = true;
            } else {
                if (turnManager.isLastPlayer()) {
                    turnManager.incrementIndividualTurnCount();
                }
                turnManager.nextTurn();
            }
            System.out.println();
        }
    }


    private void notifyDiceRolled(int result) {
        for (GameEventListener listener : listeners) {
            listener.onDiceRolled(result);
        }
    }

    private void notifyGameWon(Player winner) {
        for (GameEventListener listener : listeners) {
            listener.onGameWon(winner);
        }
    }

    private void notifyGameStart() {
        for (GameEventListener listener : listeners) {
            listener.onGameStart();
        }
    }

    private void notifyPlayerTurnStart(Player player, int individualTurnCount) {
        for (GameEventListener listener : listeners) {
            listener.onPlayerTurnStart(player, individualTurnCount);
        }
    }

    private void notifyTurnCountUpdated(int totalTurns) {
        for (GameEventListener listener : listeners) {
            listener.onTurnCountUpdated(totalTurns);
        }
    }
}
