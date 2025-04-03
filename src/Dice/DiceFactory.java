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