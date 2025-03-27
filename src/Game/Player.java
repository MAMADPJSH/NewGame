package Game;

import Board.Position;
import Board.Color;

public class Player {
    private Color color;
    private Position position;
    private Position homePosition;
    private boolean bounced;

    public Player(Color color, Position startPosition) {
        this.color = color;
        this.position = startPosition;
        this.homePosition = startPosition;
        this.bounced = false;
    }

    public Color getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }
    public void setPosition(Position newPosition) {
        this.position = newPosition;
    }

    public void setBackToHomePosition() {
        this.position = homePosition;
    }

    public boolean isBounced() { return bounced;}
    public void setBounced(boolean bounced) {this.bounced = bounced;}

    @Override
    public String toString() {
        return "Game.Player " + color + " at " + position;
    }
}

