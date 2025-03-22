import Dice.DiceShaker;
import Dice.SingleDiceRoll;

import java.util.List;
import Dice.*;

public class GameController {
    private Board board;
    private List<Player> players;
    private int currentPlayerIndex;
    private DiceShaker diceRoller;

    public GameController(Board board, List<Player> players) {
        this.board = board;
        this.players = players;
        this.currentPlayerIndex = 0; // Start with the first player
        this.diceRoller = new TestDiceRoll();
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
        int newPositionNumber;
        int movement = 1;
        int skip = 0;

        for (int i = 0; i < roll; i++) {
            newPositionNumber = (currentPos.getNumber() + movement + skip) % board.getBoardSize();

            // If the player overshoots, move them to their tail start position
            if (newPositionNumber > board.getBoardSize()) {
                newPositionNumber = board.getTailStart(player.getColor());
            }

            int nextPosNum = ((newPositionNumber) % board.getBoardSize()) + 1;
            Position nextPos = board.getPosition(nextPosNum);
            if (nextPos.getType() == PositionType.TAIL && nextPos.getOwner() != player.getColor()) {
                skip = 3;
                System.out.println(player.getColor() + " skips " + nextPos.getOwner() + "'s tail");
            } else {
                skip = 0;
            }

            Position newPosition = board.getPosition(newPositionNumber);
            player.setPosition(newPosition);

            // Update currentPos so the next iteration moves from the new position.
            currentPos = newPosition;

            System.out.println(player.getColor() + " moves to " + newPosition);
        }

    }

    private void nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }
}
