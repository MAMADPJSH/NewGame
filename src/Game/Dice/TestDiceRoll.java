package Game.Dice;

public class TestDiceRoll implements DiceShaker{

    int[] rolls = {8,2,3,4,11};
    int index = 0;

    @Override
    public int roll() {
        return rolls[index++];
    }
}
