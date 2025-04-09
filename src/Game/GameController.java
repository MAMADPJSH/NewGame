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

    // Register observers and pass them on to components that notify events.
    public void addGameEventListener(GameEventListener listener) {
        listeners.add(listener);
        playerMover.addGameEventListener(listener);
    }

    public void startGame() {
        boolean gameWon = false;
        boolean undo = false;

        System.out.println("Starting the Simple Frustration Game!");

        while (!gameWon) {
            Player currentPlayer = turnManager.getCurrentPlayer();
            System.out.println("Turn: " + turnManager.getIndividualTurn() + "\n" + currentPlayer.getColor() + ", it's your turn.");

            int diceRoll = diceRoller.roll();
            notifyDiceRolled(diceRoll);

            playerMover.movePlayer(currentPlayer, diceRoll, undo);
            turnManager.incrementTurnCount();

            // Check win condition
            if (currentPlayer.getPosition().getType() == PositionType.END
                    && currentPlayer.getPosition().getOwner() == currentPlayer.getColor()) {
                notifyGameWon(currentPlayer);
                System.out.println("Amount of turns: " + turnManager.getTurnCount());
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
}
