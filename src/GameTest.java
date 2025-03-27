import Game.GameConfig;
import Game.GameController;
import Game.LoggingGameEventListener;

public class  GameTest {
    public static void main(String[] args) {
        GameConfig configurator = new GameConfig();
        GameController gameController = configurator.configureGameFromInput();
        gameController.addGameEventListener(new LoggingGameEventListener());
        gameController.startGame();
    }
}
