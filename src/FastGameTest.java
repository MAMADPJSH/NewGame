import java.io.ByteArrayInputStream;
import Game.GameConfig;
import Game.GameController;
import Game.Logger.LoggingGameEventListener;

public class FastGameTest {
    public static void main(String[] args) {
        // Test data for Scanner (regular board positions, tail length, number of players, hit rule flag, overshoot rule flag, and dice type)
        String simulatedInput =
                        "36\n"  + // number of regular board positions
                        "6\n"   + // tail length
                        "4\n"   + // number of players
                        "true\n" + // enable hit rule
                        "true\n" + // enable overshoot rule
                        "test\n";  // dice type

        // Redirect System.in to use the simulated input
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Create a new GameConfig instance and configure the game using the simulated input
        GameConfig gameConfig = new GameConfig();
        GameController gameController = gameConfig.configureGameFromInput();

        // Continue with further game setup or testing if needed.
        System.out.println("Game configuration complete.");

        // Start the game
        gameController.addGameEventListener(new LoggingGameEventListener());
        gameController.startGame();
    }
}