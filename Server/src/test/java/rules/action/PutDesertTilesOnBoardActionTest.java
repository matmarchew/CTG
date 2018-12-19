package rules.action;

import communication.observer.DesertTileObserver;
import communication.observer.FieldsObserver;
import org.junit.Assert;
import org.junit.Test;
import rules.Messages;
import rules.board.BettingCards;
import rules.board.Field;
import rules.board.Fields;
import rules.board.tiles.desert.DesertTile;
import rules.players.Player;
import rules.players.PlayerSocket;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.mock;

public class PutDesertTilesOnBoardActionTest {
    @Test
    public void shouldPutOasisPageOfDesertTileOnBoard() {
        //Given
        Field field = new Field();
        List<Field> fields = new LinkedList<>();
        fields.add(field);
        String playerLogin = UUID.randomUUID().toString();
        Player player = new Player(mock(PlayerSocket.class), playerLogin, mock(BettingCards.class), new DesertTile(playerLogin, mock(DesertTileObserver.class)));
        PutDesertTilesOnBoardAction putDesertTilesOnBoardAction = new PutDesertTilesOnBoardAction(new Fields(fields, mock(FieldsObserver.class)), player, Messages.OASIS_PAGE, "0");

        //When
        putDesertTilesOnBoardAction.performAction();

        //Then
        Assert.assertTrue(field.getDesertTileAndClearIt().equals(player));
    }

    @Test
    public void shouldPutMiragePageOfDesertTileOnBoard() {
        //Given
        Field field = new Field();
        List<Field> fields = new LinkedList<>();
        fields.add(field);
        String playerLogin = UUID.randomUUID().toString();
        Player player = new Player(mock(PlayerSocket.class), playerLogin, mock(BettingCards.class), new DesertTile(playerLogin, mock(DesertTileObserver.class)));
        PutDesertTilesOnBoardAction putDesertTilesOnBoardAction = new PutDesertTilesOnBoardAction(new Fields(fields, mock(FieldsObserver.class)), player, Messages.MIRAGE_PAGE, "0");

        //When
        putDesertTilesOnBoardAction.performAction();

        //Then
        Assert.assertTrue(field.getDesertTileAndClearIt().equals(player));
    }

}