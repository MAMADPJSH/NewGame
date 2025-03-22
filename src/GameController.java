import Dice.DiceShaker;
import Dice.SingleDiceRoll;

import java.util.ArrayList;
import java.util.List;
import Dice.*;

public class GameController {
    private Board board;
    private List<Player> players;
    private int currentPlayerIndex;
    private DiceShaker diceRoller;
    private boolean hitRuleActive = false;

    public GameController(Board board, List<Player> players, boolean hitRuleActive, DiceShaker diceRoller) {
        this.board = board;
        this.players = players;
        this.currentPlayerIndex = 0; // Start with the first player
        this.hitRuleActive = hitRuleActive;
        this.diceRoller = diceRoller;
    }

    public void startGame() {
        boolean gameWon = false;

        System.out.println("🎲 Starting the Simple Frustration Game! 🎲");

        while (!gameWon) {
            Player currentPlayer = players.get(currentPlayerIndex);
            int diceRoll = rollDice();
            System.out.println(currentPlayer.getColor() + " rolls " + diceRoll);

            movePlayer(currentPlayer, diceRoll);
            System.out.println("\n");

            // Check if the player has reached the end position
            if (currentPlayer.getPosition().getType() == PositionType.END) {
                System.out.println("🏆 " + currentPlayer.getColor() + " wins the game!");
                gameWon = true;
            } else {
                nextTurn();
            }
        }
    }

    private int rollDice() {
        return diceRoller.roll();
    }

    private void movePlayer(Player player, int roll) {
        Position currentPos = player.getPosition();
        Position newPosition= null;
        int newPositionNumber;
        int movement = 1;
        int skip = 0;

        for (int i = 0; i < roll; i++) {
            // Adjusting to always get a value between 1 and board.getBoardSize()
            newPositionNumber = ((currentPos.getNumber() + movement + skip - 1) % board.getBoardSize()) + 1;

            // Moving the player
            newPosition = board.getPosition(newPositionNumber);
            player.setPosition(newPosition);
            currentPos = newPosition;

            //overshoot logic
            if (newPosition.getType() == PositionType.END && roll > i + 1) {
                movement = -1;
                System.out.println(player.getColor() + " overshoots and moves back to " + newPosition);
            }

            // Skip logic
            int nextPosNum = ((newPositionNumber) % board.getBoardSize()) + 1;
            Position nextPos = board.getPosition(nextPosNum);
            if (nextPos.getType() == PositionType.TAIL && nextPos.getOwner() != player.getColor()) {
                skip = board.getTailLength();
                System.out.println(player.getColor() + " skips " + nextPos.getOwner() + "'s tail");
            } else {
                skip = 0;
            }
        }

        // Hit logic
        if (hitRuleActive) {
            for (Player otherPlayer : players) {
                if (otherPlayer != player && otherPlayer.getPosition() == newPosition) {
                    System.out.println(player.getColor() + " hits " + otherPlayer.getColor() + " back to home");
                    otherPlayer.setPosition(board.getHomePosition(otherPlayer.getColor()));
                }
            }
        }

        System.out.println(player.getColor() + " moves to " + newPosition);
    }


    private void nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }
}
