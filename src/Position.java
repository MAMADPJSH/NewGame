public class Position {
    private final int number;
    private final PositionType type;
    private Color owner;

    public Position(int number, PositionType type, Color owner) {
        this.number = number;
        this.type = type;
        this.owner = owner;
    }

    public int getNumber() {
        return number;
    }

    public PositionType getType() {
        return type;
    }

    public Color getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "Position{" +
                "number=" + number +
                ", type=" + type +
                ", owner=" + owner +
                '}';
    }
}

