package rules.board;

import communication.observer.BettingTileObserver;
import communication.observer.DesertTileObserver;
import communication.observer.FieldsObserver;
import communication.observer.PlayerObserver;
import org.junit.Assert;
import org.junit.Test;
import rules.Messages;
import rules.board.tiles.bet.BettingTile;
import rules.board.tiles.bet.BettingTiles;
import rules.board.tiles.bet.StackOfBettingTile;
import rules.board.tiles.desert.DesertTile;
import rules.board.tiles.desert.DesertTileFactory;
import rules.board.tiles.desert.UnclassifiedDesertTilePage;
import rules.players.Player;
import rules.players.PlayerSocket;
import rules.players.Players;

import java.util.*;

import static org.mockito.Mockito.mock;

public class BoardTest {
    @Test
    public void shouldSetPawnToStartingPositions() {
        //Given
        Field field = new Field();
        Fields fields = new Fields(new LinkedList<>(Arrays.asList(field)), mock(FieldsObserver.class));
        Board board = new Board(fields, mock(BettingTiles.class));
        String color = UUID.randomUUID().toString();

        //When
        board.moveThePawns(1, color);

        //Then
        Assert.assertTrue(field.getNumberOfPawns() == 1);
    }

    @Test
    public void shouldSetPawnsToFinishingPosition() {
        //Given
        Field field1 = new Field();
        Field field2 = new Field();
        Fields fields = new Fields(new LinkedList<>(Arrays.asList(field1, field2)), mock(FieldsObserver.class));
        Board board = new Board(fields, mock(BettingTiles.class));
        String color = UUID.randomUUID().toString();
        board.moveThePawns(1, color);

        //When
        board.moveThePawns(3, color);
        boolean result = board.isGameFinished();

        //Then
        Assert.assertTrue(result);
    }

    @Test
    public void shouldSetPawnsToNormalPosition() {
        //Given
        Field field1 = new Field();
        Field field2 = new Field();
        Fields fields = new Fields(new LinkedList<>(Arrays.asList(field1, field2)), mock(FieldsObserver.class));
        Board board = new Board(fields, mock(BettingTiles.class));
        String color = UUID.randomUUID().toString();
        board.moveThePawns(1, color);

        //When
        board.moveThePawns(1, color);

        //Then
        Assert.assertTrue(field2.getNumberOfPawns() == 1);
    }

    @Test
    public void shouldAddCardToWinnerStack() {
        //Given
        Board board = new Board(mock(Fields.class), mock(BettingTiles.class));
        String color = UUID.randomUUID().toString();
        String playerLogin = UUID.randomUUID().toString();
        BettingCard bettingCard = new BettingCard(color, playerLogin);
        Player player = new Player(mock(PlayerSocket.class), playerLogin, mock(BettingCards.class), mock(DesertTile.class), mock(PlayerObserver.class));

        //When
        board.addBettingCardToStack(bettingCard, Messages.WINNER_STACK);
        board.calculatePointsAfterGame(new Pawn(color), new Pawn(UUID.randomUUID().toString()), new Players(Arrays.asList(player)));

        //Then
        Assert.assertTrue(player.getPoints() == 8);
    }

    @Test
    public void shouldAddCardToLoserStack() {
        //Given
        Board board = new Board(mock(Fields.class), mock(BettingTiles.class));
        String color = UUID.randomUUID().toString();
        String playerLogin = UUID.randomUUID().toString();
        BettingCard bettingCard = new BettingCard(color, playerLogin);
        Player player = new Player(mock(PlayerSocket.class), playerLogin, mock(BettingCards.class), mock(DesertTile.class), mock(PlayerObserver.class));

        //When
        board.addBettingCardToStack(bettingCard, Messages.LOSER_STACK);
        board.calculatePointsAfterGame(new Pawn(UUID.randomUUID().toString()), new Pawn(color), new Players(Arrays.asList(player)));

        //Then
        Assert.assertTrue(player.getPoints() == 8);
    }

    @Test
    public void shouldPrepareBoard() {
        //Given
        Random random = new Random();
        String color = UUID.randomUUID().toString();
        int value = random.nextInt();
        BettingTile bettingTile = new BettingTile(color, value, mock(BettingTileObserver.class));
        StackOfBettingTile stackOfBettingTile = new StackOfBettingTile(Arrays.asList(bettingTile));
        BettingTiles bettingTiles = new BettingTiles(Arrays.asList(stackOfBettingTile));
        Board board = new Board(mock(Fields.class), bettingTiles);

        //When
        board.prepareBoard();
        BettingTile result = bettingTiles.getTopBettingTileFromStackInColor(color);

        //Then
        Assert.assertTrue(result.getValue() == value);
    }

    @Test
    public void shouldReturnDesertTileIfPawnsStandAtFieldContainsDesertTile() {
        //Given
        Field field = new Field();
        Fields fields = new Fields(Arrays.asList(field), mock(FieldsObserver.class));
        Board board = new Board(fields, mock(BettingTiles.class));
        String playerLogin = UUID.randomUUID().toString();
        fields.putDesertTileToField(new DesertTile(playerLogin, mock(DesertTileObserver.class), new DesertTileFactory()), 0);
        board.moveThePawns(1, UUID.randomUUID().toString());

        //When
        DesertTile result = board.getReturnedDesertTile();

        //Then
        Assert.assertTrue(result.equals(playerLogin));
    }

    @Test
    public void shouldReturnPawnsInDescendingOrder() {
        //Given
        Field field1 = new Field();
        Field field2 = new Field();
        String color = UUID.randomUUID().toString();
        new UnclassifiedDesertTilePage().addPawnsToField(field1,
                Arrays.asList(new Pawn(UUID.randomUUID().toString()), new Pawn(UUID.randomUUID().toString())));
        new UnclassifiedDesertTilePage().addPawnsToField(field2,
                Arrays.asList(new Pawn(color), new Pawn(UUID.randomUUID().toString())));
        Fields fields = new Fields(Arrays.asList(field2, field1), mock(FieldsObserver.class));
        Board board = new Board(fields, mock(BettingTiles.class));

        //When
        List<Pawn> result = board.getPawnsInOrder();

        //Then
        Assert.assertTrue(result.get(2).equals(color));
    }
}