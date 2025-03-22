import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import Dice.*;

public class GameConfig {

    public GameController configureGameFromInput() {
        Scanner scanner = new Scanner(System.in);

        // Get board configuration.
        System.out.print("Enter the number of regular board positions: ");
        int boardSize = scanner.nextInt();

        System.out.print("Enter the tail length: ");
        int tailLength = scanner.nextInt();

        // Get player configuration.
        System.out.print("Enter the number of players (max 4): ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine();

        List<Color> availableColors = Arrays.asList(Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW);
        if (numPlayers < 1 || numPlayers > availableColors.size()) {
            System.out.println("Invalid number of players. Must be between 1 and " + availableColors.size());
            throw new IllegalArgumentException("Invalid number of players.");
        }
        List<Color> playerColors = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            playerColors.add(availableColors.get(i));
        }

        System.out.print("Enable hit rule? (true/false): ");
        boolean hitRuleEnabled = scanner.nextBoolean();
        scanner.nextLine();

        System.out.print("Select dice type (single, double, test): ");
        String diceType = scanner.nextLine().trim();

        Board board = new BoardBuilder()
                .setBoardSize(boardSize)
                .setTailLength(tailLength)
                .setPlayers(playerColors)
                .build();

        List<Player> players = new ArrayList<>();
        for (Color color : playerColors) {
            players.add(new Player(color, board.getHomePosition(color)));
        }

        // Create a DiceShaker using the DiceFactory.
        DiceShaker diceShaker = DiceFactory.createDice(diceType);

        // Create the GameController with the board, players, and hit rule flag.
        GameController gameController = new GameController(board, players, hitRuleEnabled, diceShaker);

        return gameController;
    }
}
