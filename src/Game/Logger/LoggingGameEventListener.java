package Game.Logger;

import Board.Position;
import Game.Player;

public class LoggingGameEventListener implements GameEventListener {
    @Override
    public void onDiceRolled(int result) {
        System.out.println("[Observer] Dice rolled with result: " + result);
    }

    @Override
    public void onPlayerMoved(Player player, Position newPosition) {
        System.out.println("[Observer] " + player.getColor() + " moved to position: " + newPosition.getDisplayableNumber());
    }

    @Override
    public void onGameWon(Player winner) {
        System.out.println("[Observer] Game won by: " + winner.getColor());
    }

    @Override
    public void onGameStart() {
        System.out.println("[Observer] Starting the Simple Frustration Game!");
    }

    @Override
    public void onPlayerTurnStart(Player player, int individualTurnCount) {
        System.out.println("[Observer] Turn: " + individualTurnCount);
        System.out.println("[Observer] " + player.getColor() + ", it's your turn.");
    }

    @Override
    public void onTurnCountUpdated(int totalTurns) {
        System.out.println("[Observer] Amount of turns: " + totalTurns);
    }
}

