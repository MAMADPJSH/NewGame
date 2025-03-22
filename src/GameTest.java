import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class  GameTest {
    public static void main(String[] args) {
        // Define players' colors
        List<Color> playerColors = Arrays.asList(Color.RED, Color.BLUE);

        // Create board (18 positions + tails)
        Board gameBoard = new Board(18, 3, playerColors);

        // Create players dynamically using home positions from Board
        List<Player> players = new ArrayList<>();
        for (Color color : playerColors) {
            players.add(new Player(color, gameBoard.getHomePosition(color)));
        }

        // Create GameController with generated players
        GameController gameController = new GameController(gameBoard, players);

        // Start game simulation
        gameController.startGame();
    }
}
