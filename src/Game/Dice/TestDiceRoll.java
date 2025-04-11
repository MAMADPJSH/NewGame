package Game.Dice;

public class TestDiceRoll implements DiceShaker{

    int[] rolls = {12,12,7,11,3,3};
    int index = 0;

    @Override
    public int roll() {
        return rolls[index++];
    }
}
