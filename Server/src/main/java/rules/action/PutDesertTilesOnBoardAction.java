package rules.action;

import rules.Messages;
import rules.board.Fields;
import rules.board.tiles.desert.DesertTile;
import rules.players.Player;

public class PutDesertTilesOnBoardAction implements PlayerAction {
    private final Fields fields;
    private final Player player;
    private final String desertTilePage;
    private final String fieldNumber;

    public PutDesertTilesOnBoardAction(Fields fields, Player player, String desertTilePage, String fieldNumber) {
        this.fields = fields;
        this.player = player;
        this.desertTilePage = desertTilePage;
        this.fieldNumber = fieldNumber;
    }

    @Override
    public void performAction() {
        DesertTile desertTile = player.getPlayerDesertTile();
        desertTile.switchPageToActive(desertTilePage);
        fields.putDesertTileToField(desertTile, Integer.valueOf(fieldNumber));
        desertTile.notifyDesertTileObserver(Integer.valueOf(fieldNumber), Messages.PUT_DESERT_TILE, desertTilePage);
    }
}
