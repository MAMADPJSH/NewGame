package Game.Logger;

import Board.Position;
import Game.Player;

public class LoggingGameEventListener implements GameEventListener {
    @Override
    public void onDiceRolled(int result) {
        System.out.println("[Observer] Game.Dice rolled with result: " + result);
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

