package rules.action;

import rules.Cubes;
import communication.CustomJSONObject;
import rules.Messages;
import rules.board.Board;
import rules.players.Player;

public class PlayerActionFactory {
    public static PlayerAction getPlayerActionFromJson(CustomJSONObject json, Player player, Board board, Cubes cubes) {
        switch (json.getString(Messages.ACTION_TYPE)) {
            case Messages.THROW_CUBE : return new ThrowCubeAction(board, cubes, player);
            case Messages.PUT_DESERT_TILE : return new PutDesertTilesOnBoardAction(board.getFields(), player, json.getString(Messages.PAGE), json.getString(Messages.FIELD_NUMBER));
            case Messages.GET_BET_TILE : return new TakeBettingTileFromBoardAction(board.getStacksOfBetTile(), player, json.getString(Messages.COLOR));
            case Messages.PUT_BET_CARD : return new PutBettingCardToFinalBettingStackAction(board, player, json.getString(Messages.COLOR), json.getString(Messages.STACK));
            default : return new EmptyAction();
        }
    }
}
