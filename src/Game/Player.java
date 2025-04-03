package Game;

import Board.Position;
import Board.Color;

public class Player {
    private final Color color;
    private Position position;
    private final Position homePosition;
    private boolean bounced;
    private int movement;
    private int skip;
    private int turn;

    public Player(Color color, Position startPosition) {
        this.color = color;
        this.position = startPosition;
        this.homePosition = startPosition;
        this.bounced = false;
        this.movement = 1;
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

    public int getMovement() { return movement;}
    public void setMovement(int movement) {this.movement = movement;}

    public int getSkip() { return skip;}
    public void setSkip(int skip) {this.skip = skip;}

    @Override
    public String toString() {
        return "Game.Player " + color + " at " + position;
    }
}

