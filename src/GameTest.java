import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class  GameTest {
    public static void main(String[] args) {
        GameConfig configurator = new GameConfig();
        GameController gameController = configurator.configureGameFromInput();
        gameController.startGame();
    }
}
