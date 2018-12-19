package communication.information;

import communication.CustomJSONObject;
import rules.Messages;
import rules.board.Fields;
import rules.players.Player;

public class PutDesertTileInformationAction implements InformationAction {
    private final Player player;
    private final Fields fields;

    public PutDesertTileInformationAction(Player player, Fields fields) {
        this.player = player;
        this.fields = fields;
    }

    @Override
    public void sendInformation() {
        CustomJSONObject json = new CustomJSONObject();
        json.put(Messages.FIELD_NUMBER, fields.getFieldsWhereNoPutDesertTile());
        json.put(Messages.DESERT_TILE, player.isHaveDesertTile().toString());
        player.sendMessageToMobile(json.toString());
    }
}
