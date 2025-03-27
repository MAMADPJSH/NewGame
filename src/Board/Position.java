package Board;

public class Position {
    private final int number;
    private final String displayableNumber;
    private final PositionType type;
    private Color owner;

    public Position(int number, String displayableNumber, PositionType type, Color owner) {
        this.number = number;
        this.displayableNumber = displayableNumber;
        this.type = type;
        this.owner = owner;
    }

    public int getNumber() {
        return number;
    }

    public String getDisplayableNumber() {
        return displayableNumber;
    }

    public PositionType getType() {
        return type;
    }

    public Color getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "Board.Position{" + "number=" + this.getNumber() + " displayableNumber=" + this.getDisplayableNumber() + ", type=" + this.getType() + ", owner=" + this.getOwner() + "}";
    }
}

