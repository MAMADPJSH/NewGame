import java.util.List;
import java.util.Random;

public class GameController {
    private Board board;
    private List<Player> players;
    private int currentPlayerIndex;
    private Random diceRoller;

    public GameController(Board board, List<Player> players) {
        this.board = board;
        this.players = players;
        this.currentPlayerIndex = 0; // Start with the first player
        this.diceRoller = new Random();
    }

    public void startGame() {
        boolean gameWon = false;

        System.out.println("🎲 Starting the Simple Frustration Game! 🎲");

        while (!gameWon) {
            Player currentPlayer = players.get(currentPlayerIndex);
            int diceRoll = rollDice();
            System.out.println(currentPlayer.getColor() + " rolls " + diceRoll);

            movePlayer(currentPlayer, diceRoll);

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
        return diceRoller.nextInt(6) + 1; // Rolls a 6-sided die (1-6)
    }

    private void movePlayer(Player player, int roll) {
        Position currentPos = player.getPosition();
        int newPositionNumber = currentPos.getNumber() + roll;

        // If the player overshoots, move them to their tail start position
        if (newPositionNumber > board.getBoardSize()) {
            newPositionNumber = board.getTailStart(player.getColor());
        }

        Position newPosition = board.getPosition(newPositionNumber);
        player.setPosition(newPosition);

        System.out.println(player.getColor() + " moves to " + newPosition);
    }

    private void nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }
}
