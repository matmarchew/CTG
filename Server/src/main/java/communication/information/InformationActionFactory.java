package communication.information;

import communication.CustomJSONObject;
import rules.Messages;
import rules.board.Board;
import rules.players.Player;

public class InformationActionFactory {
    public static InformationAction getInformationAction(CustomJSONObject json, Player player, Board board) {
        switch (json.getString(Messages.ACTION_TYPE)) {
            case Messages.GET_BET_TILE : return new BetTileInformationAction(player, board.getStacksOfBetTile());
            case Messages.PUT_BET_CARD : return new PutBetCardInformationAction(player);
            case Messages.PUT_DESERT_TILE : return new PutDesertTileInformationAction(player, board.getFields());
            default : return new EmptyInformationAction();
        }
    }
}
