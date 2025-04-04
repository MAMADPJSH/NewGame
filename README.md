# [A Simple Frustration Game]

## Overview

In this game you can have 2-4 players racing to reach their end positions. There can be upto a lot of positions and upto 2 dices each game. First person to reach the end will finish the game.

### Installing the game:

You have to clone the repo and compile and run GameTest.java.

### How to play:

When you run the code, the game will go into configuration mode so it will ask you a few questions on how many positions, tails and players you want. Then It will ask you to toggle the two extra rules. The Hit rule is when a player lands on another player so the targeted player will move back to their home. The Overshoot rule is so the player has to roll the exact amount to reach their END position if they dont they will move back and re-roll.

#### Default 2 player settings:

1. 18 Positions
2. 3 Tails
3. Hit turned on
4. Overshoot turned on
5. Single Dice

#### Default 4 player settings:

1. 36 Positions
2. 6 Tails
3. Hit turned on
4. Overshoot turned on
5. Double Dice

## Project Structure

```
SimpleGame/
├── src/
│   ├── GameTest.java        // Main class to run the game
│   ├── Main.java            // Does Nothing
│   ├── Game/
│   │   ├── Logger/
│   │   │   ├── GameEventListener.java        // Interface for listening for events
│   │   │   └── LoggingGameEventListener.java // Logs the events
│   │   ├── Rules/
│   │   │   ├── PlayerMovementRules.java      // Interface for the rules
│   │   │   ├── HitRule.java                  // Interface for the rules
│   │   │   ├── SkipRule.java                 // Interface for the rules
│   │   │   └── OvershootRule.java            // Logs the events
│   │   ├── GameConfig.java                   // Takes in information from user to begin the game
│   │   ├── GameController.java               // Handles the data and stores game logic
│   │   ├── Player.java                       // Player object
│   │   ├── PlayerMover.java                  // Stores player movement logic
│   │   └── TurnManager.java                  // Manages the turn logic
│   ├── Dice/
│   │   ├── DiceShaker.java                   // Interface for handeling dice rolls.
│   │   ├── SingleDiceFactory.java            // Single dice roller
│   │   ├── DoubleDiceFactory.java            // Double dice roller
│   │   ├── TestDiceFactory.java              // Test dice roller
│   │   └── DiceFactory.java                  // Factory for creating dice
│   ├── Board/
│   │   ├── Board.java                        // Board object
│   │   ├── BoardBuilder.java                 // Uses data from user to create a Board
│   │   ├── Color.java                        // Color Enum
│   │   ├── Position.java                     // Position Object
│   │   └── PositionType.java                 // Type Enum
│   └── .java
├── bin/                     // Compiled classes
├── diagrams/                // Contains UML and flow diagrams
│   ├── class_diagram.png    // UML diagram of the classes
│   └── flow_diagram.png     // Flow diagram of the game process
└── README.md
```

# Software Design Techniques

1. Observer Pattern

```
//GameEventListener
package Game.Logger;

import Board.Position;
import Game.Player;

public interface GameEventListener {
    void onDiceRolled(int result);
    void onPlayerMoved(Player player, Position newPosition);
    void onGameWon(Player winner);
}

//LoggingGameEventListener
package Game.Logger;

import Board.Position;
import Game.Player;

public class LoggingGameEventListener implements GameEventListener {
    @Override
    public void onDiceRolled(int result) {
        System.out.println("[Observer] Dice rolled with result: " + result);
    }

    @Override
    public void onPlayerMoved(Player player, Position newPosition) {
        System.out.println("[Observer] " + player.getColor() + " moved to position: " + newPosition.getDisplayableNumber());
    }

    @Override
    public void onGameWon(Player winner) {
        System.out.println("[Observer] Game won by: " + winner.getColor());
    }
}
```

## Usage example:

```
if (currentPlayer.getPosition().getType() == PositionType.END
                    && currentPlayer.getPosition().getOwner() == currentPlayer.getColor()) {
                notifyGameWon(currentPlayer); // USED ONE OF THE METHODS HERE
                gameWon = true;
            }
```

## Why:

This decouples the logging process therefore making it easier to change the output.

2. Factory Pattern

```
package Dice;

public class DiceFactory {
    public static DiceShaker createDice(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Dice type cannot be null.");
        }
        return switch (type.toLowerCase()) {
            case "single" -> new SingleDiceRoll();
            case "double" -> new DoubleDiceRoll();
            case "test" -> new TestDiceRoll();
            default -> throw new IllegalArgumentException("I dont have this dice cuh: " + type);
        };
    }
}
```

## Usuage Example:

```
// GameConfig.java

        // set dice type
        System.out.print("Select dice type (single, double, test): ");
        String diceType = scanner.nextLine().trim();

        System.out.println("Settings saved\n");



        // Create a DiceShaker using the DiceFactory
        DiceShaker diceShaker = DiceFactory.createDice(diceType);
```

## Why:

I decided to use a Simple factory design as I was taking a string from the user. I can use that string to generate or return an instance of the desired die.

3. Strategy Pattern

```
package Game.Rules;

import Board.Board;
import Board.Position;
import Game.Player;

public interface PlayerMovementRules {
    void applyRule(Player player, Position newPosition, Position currentPosition, Board board);
}
// Hit Rule:
package Game.Rules;

import Board.Board;
import Board.Position;
import Game.Player;

import java.util.List;

public class HitRule implements PlayerMovementRules {
    private final List<Player> players;

    public HitRule(List<Player> players) {
        this.players = players;
    }

    @Override
    public void applyRule(Player player, Position newPosition, Position currentPosition, Board board) {
        for (Player otherPlayer : players) {
            if (otherPlayer != player && otherPlayer.getPosition() == newPosition) {
                System.out.println(player.getColor() + " hits " + otherPlayer.getColor() + " back to home.");
                otherPlayer.setPosition(board.getHomePosition(otherPlayer.getColor()));
            }
        }
    }
}
// many more rules not included to save space
```

## Usuage Example:

```
// PlayerMover.java

for (PlayerMovementRules rule : rules) {
        if (rule instanceof HitRule && i < roll - 1) {
            continue;
        }
        rule.applyRule(player, newPosition, currentPos, board);
}
```

## Why:

I tried to write the rules in the movePlayer method but the method was handeling way too many things at the same time and it was hard to manage. This also helps the user add and remove the rules they want.

3. Builder Pattern

```
package Board;

import java.util.List;

public class BoardBuilder {
    private int boardSize;
    private int tailLength;
    private List<Color> players;

    public BoardBuilder setBoardSize(int boardSize) {
        this.boardSize = boardSize;
        return this;
    }

    public BoardBuilder setTailLength(int tailLength) {
        this.tailLength = tailLength;
        return this;
    }

    public BoardBuilder setPlayers(List<Color> players) {
        this.players = players;
        return this;
    }

    // Build and return a new Board.Board
    public Board build() {
        if (boardSize <= 0) {
            throw new IllegalArgumentException("Board.Board size must be positive.");
        }
        if (tailLength <= 0) {
            throw new IllegalArgumentException("Tail length must be positive.");
        }
        if (players == null || players.isEmpty()) {
            throw new IllegalArgumentException("At least one player is required.");
        }
        return new Board(boardSize, tailLength, players);
    }
}
```

## Usuage Example:

```
//GameConfig.java
// Create the board with the given data from user
        Board board = new BoardBuilder()
                .setBoardSize(boardSize)
                .setTailLength(tailLength)
                .setPlayers(playerColors)
                .build();
```

## Why:

This class just seperates the functionality of creating a single instance of a board from the main Board class making the code more maintainable.

# Solid Principles

1. Single Responsibility: I tried to make each class focus on one function(outcome). Just as an example the Board class manages the board related logic while the GameConfig handles the configuration and the user input.
2. Open/Closed Principle: The design is open for extension but closed for modification. This can be seen used in the PlayerMovementRules interface without changing the original code I can extend on the functionality.
3. Interface Segregation Principle: I have not used many interfaces but I had GameEventListener, DiceShaker, and PlayerMovementRules which helped classes not depend on methods they dont need to use. Clearing the code and improved readablity.
4. Dependency Principle: I tried to make the GameController class to rely mostly on Interfaces because it is easier to switch out components at run time.
