package Game.Dice;

public class DoubleDiceRoll implements DiceShaker{

    DiceShaker dice = new SingleDiceRoll();

    @Override
    public int roll() {
        return dice.roll()  + dice.roll();
    }
}
