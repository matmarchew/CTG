package communication.information;

import communication.CustomJSONObject;
import rules.Messages;
import rules.board.tiles.bet.BettingTiles;
import rules.players.Player;

public class BetTileInformationAction implements InformationAction {
    private final Player player;
    private final BettingTiles bettingTiles;

    public BetTileInformationAction(Player player, BettingTiles bettingTiles) {
        this.player = player;
        this.bettingTiles = bettingTiles;
    }

    @Override
    public void sendInformation() {
        CustomJSONObject json = new CustomJSONObject();
        json.put(Messages.BET_TILES, bettingTiles.getTopBettingTileFromEveryStack());
        player.sendMessageToMobile(json.toString());
    }
}
