package rules.action;

import communication.CustomJSONObject;
import rules.Cubes;
import rules.Messages;
import rules.board.Board;
import rules.board.tiles.desert.DesertTileFactory;
import rules.players.Player;

public class PlayerActionFactory {
    public PlayerAction getPlayerActionFromJson(CustomJSONObject json, Player player, Board board, Cubes cubes) {
        switch (json.getString(Messages.ACTION_TYPE)) {
            case Messages.THROW_CUBE : return new ThrowCubeAction(board, cubes, player);

            case Messages.PUT_DESERT_TILE :
                DesertTileFactory desertTileFactory = new DesertTileFactory();
                return new PutDesertTilesOnBoardAction(board.getFields(),
                    player, desertTileFactory.getActivePage(json.getString(Messages.PAGE)),
                        Integer.valueOf(json.getString(Messages.FIELD_NUMBER)));

            case Messages.GET_BET_TILE : return new TakeBettingTileFromBoardAction(board.getStacksOfBetTile(),
                    player, json.getString(Messages.COLOR));

            case Messages.PUT_BET_CARD : return new PutBettingCardToFinalBettingStackAction(player,
                    json.getString(Messages.COLOR),
                    board.getSuitableFinalBettingStack(json.getString(Messages.STACK)));

            default : return new EmptyAction();
        }
    }
}
