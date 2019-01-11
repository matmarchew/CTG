package rules.action;

import rules.Messages;
import rules.board.Fields;
import rules.board.tiles.desert.DesertTile;
import rules.board.tiles.desert.DesertTilePage;
import rules.players.Player;

public class PutDesertTilesOnBoardAction implements PlayerAction {
    private final Fields fields;
    private final Player player;
    private final DesertTilePage desertTilePage;
    private final int fieldNumber;

    public PutDesertTilesOnBoardAction(Fields fields, Player player, DesertTilePage desertTilePage, int fieldNumber) {
        this.fields = fields;
        this.player = player;
        this.desertTilePage = desertTilePage;
        this.fieldNumber = fieldNumber;
    }

    @Override
    public void performAction() {
        DesertTile desertTile = player.getPlayerDesertTile();
        desertTile.switchPageToActive(desertTilePage);
        fields.putDesertTileToField(desertTile, fieldNumber);
        desertTile.notifyDesertTileObserver(fieldNumber, Messages.PUT_DESERT_TILE, desertTilePage);
    }
}
