import Board.Board;
import Board.Color;
import Board.Position;

import java.util.Arrays;

public class BoardTest {
    public static void main(String[] args) {
//        System.out.println("Testing 2-player game with 18 positions + tails...");
//        Board.Board board2Players = new Board.Board(18, 3, Arrays.asList(Board.Color.RED, Board.Color.BLUE));
//        printBoard(board2Players);
//        System.out.println("\n");
//        System.out.println("\n");

        System.out.println("Testing 3-player game with 36 positions + tails...");
        Board Board3Players = new Board(18, 3, Arrays.asList(Color.RED, Color.BLUE, Color.GREEN));
        printBoard(Board3Players);
        System.out.println("\n");
        System.out.println("\n");

//        System.out.println("Testing 4-player game with 36 positions + tails...");
//        Board.Board board4Players = new Board.Board(36, 6, Arrays.asList(Board.Color.RED, Board.Color.BLUE, Board.Color.GREEN, Board.Color.YELLOW));
//        printBoard(board4Players);
//        System.out.println("\n");
//        System.out.println("\n");
    }

    private static void printBoard(Board board) {
        System.out.println("Main Board.Board Positions:");
        for (int i = 1; i <= board.getBoardSize(); i++) { // Adding 6 to account for tails
            Position pos = board.getPosition(i);
            System.out.println(pos.toString());
        }
    }
}