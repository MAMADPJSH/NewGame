package Game;

import java.util.ArrayList;
import java.util.List;

import Board.Board;
import Board.PositionType;
import Dice.DiceShaker;
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
        System.out.println("Starting the Simple Frustration Game!");

        while (!gameWon) {
            Player currentPlayer = turnManager.getCurrentPlayer();
            int diceRoll = diceRoller.roll();
            notifyDiceRolled(diceRoll);

            playerMover.movePlayer(currentPlayer, diceRoll);

            // Check win condition.
            if (currentPlayer.getPosition().getType() == PositionType.END) {
                notifyGameWon(currentPlayer);
                gameWon = true;
            } else {
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






//import Dice.DiceShaker;
//
//import java.util.List;
//
//public class Game.GameController {
//    private Board.Board board;
//    private List<Game.Player> players;
//    private int currentPlayerIndex;
//    private DiceShaker diceRoller;
//    private boolean hitRuleActive = false;
//
//    public Game.GameController(Board.Board board, List<Game.Player> players, boolean hitRuleActive, DiceShaker diceRoller) {
//        this.board = board;
//        this.players = players;
//        this.currentPlayerIndex = 0; // Start with the first player
//        this.hitRuleActive = hitRuleActive;
//        this.diceRoller = diceRoller;
//    }
//
//    public void startGame() {
//        boolean gameWon = false;
//
//        System.out.println("🎲 Starting the Simple Frustration Game! 🎲");
//
//        while (!gameWon) {
//            Game.Player currentPlayer = players.get(currentPlayerIndex);
//            int diceRoll = rollDice();
//            System.out.println(currentPlayer.getColor() + " rolls " + diceRoll);
//
//            movePlayer(currentPlayer, diceRoll);
//            System.out.println("\n");
//
//            // Check if the player has reached the end position
//            if (currentPlayer.getPosition().getType() == Board.PositionType.END) {
//                System.out.println("🏆 " + currentPlayer.getColor() + " wins the game!");
//                gameWon = true;
//            } else {
//                nextTurn();
//            }
//        }
//    }
//
//    private int rollDice() {
//        return diceRoller.roll();
//    }
//
//    private void movePlayer(Game.Player player, int roll) {
//        Board.Position currentPos = player.getPosition();
//        Board.Position newPosition= null;
//        int newPositionNumber;
//        int movement = 1;
//        int skip = 0;
//
//        for (int i = 0; i < roll; i++) {
//            // Adjusting to always get a value between 1 and board.getBoardSize()
//            newPositionNumber = ((currentPos.getNumber() + movement + skip - 1) % board.getBoardSize()) + 1;
//
//            // Moving the player
//            newPosition = board.getPosition(newPositionNumber);
//            player.setPosition(newPosition);
//            currentPos = newPosition;
//
//            //overshoot logic
//            if (newPosition.getType() == Board.PositionType.END && roll > i + 1) {
//                movement = -1;
//                System.out.println(player.getColor() + " overshoots and moves back to " + newPosition);
//            }
//
//            // Skip logic
//            int nextPosNum = ((newPositionNumber) % board.getBoardSize()) + 1;
//            Board.Position nextPos = board.getPosition(nextPosNum);
//            if (nextPos.getType() == Board.PositionType.TAIL && nextPos.getOwner() != player.getColor()) {
//                skip = board.getTailLength();
//                System.out.println(player.getColor() + " skips " + nextPos.getOwner() + "'s tail");
//            } else {
//                skip = 0;
//            }
//        }
//
//        // Hit logic
//        if (hitRuleActive) {
//            for (Game.Player otherPlayer : players) {
//                if (otherPlayer != player && otherPlayer.getPosition() == newPosition) {
//                    System.out.println(player.getColor() + " hits " + otherPlayer.getColor() + " back to home");
//                    otherPlayer.setPosition(board.getHomePosition(otherPlayer.getColor()));
//                }
//            }
//        }
//
//        System.out.println(player.getColor() + " moves to " + newPosition);
//    }
//
//
//    private void nextTurn() {
//        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
//    }
//}
