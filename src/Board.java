import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private List<Position> mainPositions;
    private Map<Color, Integer> homePositions;
    private Map<Color, Integer> tailStartPositions;
    private Map<Color, Integer> endPositions;
    private int boardSize;
    private int tailLength;
    int totalPositions;

    public Board(int boardSize, int tailLength, List<Color> players) {
        this.boardSize = boardSize;
        this.tailLength = tailLength;
        this.homePositions = new HashMap<>();
        this.tailStartPositions = new HashMap<>();
        this.endPositions = new HashMap<>();
        this.totalPositions = boardSize + (tailLength * players.size());
        initialiseBoard(players);
    }

    private void initialiseBoard(List<Color> players) {
        mainPositions = new ArrayList<>();

        // First, fill all positions as REGULAR
        for (int i = 1; i <= boardSize + (tailLength * players.size()); i++) {
            mainPositions.add(new Position(i, PositionType.REGULAR, null));
        }


        int segmentSize = (totalPositions) / players.size(); // Spacing for home positions

        for (int i = 0; i < players.size(); i++) {
            Color player = players.get(i);
            int homePos = (i * segmentSize) + 1;
            homePositions.put(player, homePos);
            mainPositions.set(homePos - 1, new Position(homePos, PositionType.HOME, player));

            // Assign Tail and End positions
            int tailStart = (homePos - tailLength + totalPositions - 1) % totalPositions + 1; // Start tail near end of segment
            int endPos = tailStart + tailLength - 1;
            tailStartPositions.put(player, tailStart);
            endPositions.put(player, endPos);

            for (int j = 0; j < tailLength - 1; j++) {
                mainPositions.set(tailStart + j - 1, new Position(tailStart + j, PositionType.TAIL, player));
            }

            mainPositions.set(endPos - 1, new Position(endPos, PositionType.END, player));
        }
    }

    public Position getPosition(int index) {
        return mainPositions.get(index - 1);
    }

    public Position getHomePosition(Color player) {
        if (!homePositions.containsKey(player)) {
            throw new IllegalArgumentException("Player " + player + " does not exist on this board.");
        }
        int homePosNumber = homePositions.get(player);
        return getPosition(new Position(homePosNumber, PositionType.HOME, player).getNumber());
    }


    public int getBoardSize() {
        return totalPositions;
    }

    public int getTailStart(Color player) {
        return tailStartPositions.getOrDefault(player, -1);
    }

    public int getEndPosition(Color player) {
        return endPositions.getOrDefault(player, -1);
    }
}
