package rules.action;

import rules.board.tiles.bet.BettingTile;
import rules.board.tiles.bet.BettingTiles;
import rules.players.Player;

public class TakeBettingTileFromBoardAction implements PlayerAction {
    private final Player player;
    private final BettingTiles bettingTiles;
    private final String bettingTileColor;

    public TakeBettingTileFromBoardAction(BettingTiles bettingTiles, Player player, String bettingTileColor) {
        this.player = player;
        this.bettingTiles = bettingTiles;
        this.bettingTileColor = bettingTileColor;
    }

    @Override
    public void performAction() {
        BettingTile bettingTile =  bettingTiles.getTopBettingTileFromStackInColor(bettingTileColor);
        bettingTile.notifyBetTileObserver();
        player.addBettingTileToPlayer(bettingTile);
    }
}
