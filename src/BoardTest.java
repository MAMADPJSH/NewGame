import java.util.Arrays;
import java.util.List;

public class BoardTest {
    public static void main(String[] args) {
        System.out.println("Testing 2-player game with 18 positions + tails...");
        Board board2Players = new Board(18, 3, Arrays.asList(Color.RED, Color.BLUE));
        printBoard(board2Players);
        System.out.println("\n");
        System.out.println("\n");

//        System.out.println("Testing 2-player game with 36 positions + tails...");
//        Board largeBoard2Players = new Board(36, 6, Arrays.asList(Color.RED, Color.BLUE));
//        printBoard(largeBoard2Players);
//        System.out.println("\n");
//        System.out.println("\n");

//        System.out.println("Testing 4-player game with 36 positions + tails...");
//        Board board4Players = new Board(36, 6, Arrays.asList(Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW));
//        printBoard(board4Players);
//        System.out.println("\n");
//        System.out.println("\n");
    }

    private static void printBoard(Board board) {
        System.out.println("Main Board Positions:");
        for (int i = 1; i <= board.getBoardSize(); i++) { // Adding 6 to account for tails
            Position pos = board.getPosition(i);
            System.out.println(pos.toString());
        }
    }
}