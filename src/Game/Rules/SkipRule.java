package Game.Rules;


import Board.*;
import Game.Player;

public class SkipRule implements PlayerMovementRules {
    @Override
    public void applyRule(Player player, Position newPosition, Position currentPosition, Board board, boolean isLastStep) {
        int nextPosNum = ((newPosition.getNumber()) % board.getBoardSize()) + 1;
        Position nextPos = board.getPosition(nextPosNum);
        if (nextPos.getType() == PositionType.TAIL && nextPos.getOwner() != player.getColor()) {
            int skipAmount = board.getTailLength();
            System.out.println(player.getColor() + " skips " + nextPos.getOwner() + "'s tail.");
            //set skip amount to tail length
            player.setSkip(skipAmount);
        } else {
            //set skip amount to 0
            player.setSkip(0);
        }
    }
}
