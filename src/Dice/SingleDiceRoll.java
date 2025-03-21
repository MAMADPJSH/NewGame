package Dice;

import java.util.Random;

public class SingleDiceRoll implements DiceShaker {
    Random random = new Random();

    @Override
    public int roll() {
        return random.nextInt(6) + 1;
    }
}
