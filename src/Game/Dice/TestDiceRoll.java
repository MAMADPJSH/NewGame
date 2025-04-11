package Game.Dice;

public class TestDiceRoll implements DiceShaker{

    int[] rolls = {6,6,6,6,3,4,3,4};
    int index = 0;

    @Override
    public int roll() {
        return rolls[index++];
    }
}
