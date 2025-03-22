package Dice;

public class TestDiceRoll implements DiceShaker{

    int[] rolls = {12,5,2,12,7,4,2,12,3,5,2,12,5,4,4,8,2,2,12,3};
    int index = 0;

    @Override
    public int roll() {
        return rolls[index++];
    }
}
