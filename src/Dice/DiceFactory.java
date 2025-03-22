package Dice;

public class DiceFactory {
    public static DiceShaker createDice(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Dice type cannot be null.");
        }
        switch (type.toLowerCase()) {
            case "single":
                return new SingleDiceRoll();
            case "double":
                return new DoubleDiceRoll();
            case "test":
                return new TestDiceRoll();
            default:
                throw new IllegalArgumentException("I dont have this dice cuh: " + type);
        }
    }
}

