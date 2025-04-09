package Game;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import Board.Board;
import Board.Color;
import Board.BoardBuilder;
import Game.Dice.*;
import Game.Rules.*;

public class GameConfig {

    public GameController configureGameFromInput() {
        Scanner scanner = new Scanner(System.in);

        // Get board position numbers
        System.out.print("Enter the number of regular board positions: ");
        int boardSize = scanner.nextInt();

        // Get board tail numbers
        System.out.print("Enter the tail length: ");
        int tailLength = scanner.nextInt();

        // Get player numbers and set their colors
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

        // Create the board with the given data from user
        Board board = new BoardBuilder()
                .setBoardSize(boardSize)
                .setTailLength(tailLength)
                .setPlayers(playerColors)
                .build();

        // create the player objects
        List<Player> players = new ArrayList<>();
        for (Color color : playerColors) {
            players.add(new Player(color, board.getHomePosition(color)));
        }


        // create a list for piossible rules
        List<PlayerMovementRules> rules = new ArrayList<>();

        // set skip rule to true it is always enabled
        rules.add(new SkipRule());

        // set hit rule
        System.out.print("Enable hit rule? (true/false): ");
        if (scanner.nextBoolean()) {
            rules.add(new HitRule(players));
        }
        scanner.nextLine();

        // set overshoot rule
        System.out.print("Enable overshoot rule? (true/false): ");
        if (scanner.nextBoolean()) {
            rules.add(new OvershootRule());
        }
        scanner.nextLine();

        // set dice type
        System.out.print("Select dice type (single, double, test): ");
        String diceType = scanner.nextLine().trim();

        System.out.println("Settings saved\n");



        // Create a DiceShaker using the DiceFactory
        DiceShaker diceShaker = DiceFactory.createDice(diceType);

        // Create and return the GameController with the board, players and slected rules.
        return new GameController(board, players, rules, diceShaker);
    }
}
