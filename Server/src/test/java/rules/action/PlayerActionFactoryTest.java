package rules.action;

import communication.CustomJSONObject;
import org.junit.jupiter.api.Test;
import rules.Cubes;
import rules.Messages;
import rules.board.Board;
import rules.players.Player;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class PlayerActionFactoryTest {
    @Test
    public void shouldReturnThrowCubeAction() {
        //Given
        CustomJSONObject jsonObject = new CustomJSONObject();
        jsonObject.put(Messages.ACTION_TYPE, Messages.THROW_CUBE);

        //When
        PlayerAction result = PlayerActionFactory.getPlayerActionFromJson(jsonObject, mock(Player.class), mock(Board.class), mock(Cubes.class));

        //Then
        assertTrue(result instanceof ThrowCubeAction);
    }

    @Test
    public void shouldReturnPutBetCardAction() {
        //Given
        CustomJSONObject jsonObject = new CustomJSONObject();
        jsonObject.put(Messages.ACTION_TYPE, Messages.PUT_BET_CARD);
        jsonObject.put(Messages.COLOR, "");
        jsonObject.put(Messages.STACK, Messages.WINNER_STACK);

        //Given
        PlayerAction result = PlayerActionFactory.getPlayerActionFromJson(jsonObject, mock(Player.class), mock(Board.class), mock(Cubes.class));

        //Then
        assertTrue(result instanceof PutBettingCardToFinalBettingStackAction);
    }

    @Test
    public void shouldReturnPutDesertTileAction() {
        //Given
        CustomJSONObject jsonObject = new CustomJSONObject();
        jsonObject.put(Messages.ACTION_TYPE, Messages.PUT_DESERT_TILE);
        jsonObject.put(Messages.PAGE, Messages.MIRAGE_PAGE);
        jsonObject.put(Messages.FIELD_NUMBER, "3");

        //When
        PlayerAction result = PlayerActionFactory.getPlayerActionFromJson(jsonObject, mock(Player.class), mock(Board.class), mock(Cubes.class));

        //Then
        assertTrue(result instanceof PutDesertTilesOnBoardAction);
    }

    @Test
    public void shouldReturnGetBetTileAction() {
        //Given
        CustomJSONObject jsonObject = new CustomJSONObject();
        jsonObject.put(Messages.ACTION_TYPE, Messages.GET_BET_TILE);
        jsonObject.put(Messages.COLOR, "");

        //When
        PlayerAction result = PlayerActionFactory.getPlayerActionFromJson(jsonObject, mock(Player.class), mock(Board.class), mock(Cubes.class));

        //Then
        assertTrue(result instanceof TakeBettingTileFromBoardAction);
    }

    @Test
    public void shouldReturnEmptyAction() {
        //Given
        CustomJSONObject jsonObject = new CustomJSONObject();
        jsonObject.put(Messages.ACTION_TYPE, UUID.randomUUID().toString());

        //When
        PlayerAction result = PlayerActionFactory.getPlayerActionFromJson(jsonObject, mock(Player.class), mock(Board.class), mock(Cubes.class));

        //Then
        assertTrue(result instanceof EmptyAction);
    }
}