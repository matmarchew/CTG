package rules.board;

import communication.observer.BettingTileObserver;
import communication.observer.BoardObserver;
import communication.observer.FieldsObserver;
import org.junit.Assert;
import org.junit.Test;
import rules.board.tiles.bet.BettingTile;
import rules.board.tiles.bet.BettingTiles;
import rules.board.tiles.bet.StackOfBettingTile;
import rules.board.tiles.desert.UnclassifiedDesertTilePage;

import java.util.*;

import static org.mockito.Mockito.mock;

public class BoardTest {
    @Test
    public void shouldSetPawnToStartingPositions() {
        //Given
        Field field = new Field();
        Fields fields = new Fields(new LinkedList<>(Arrays.asList(field)), mock(FieldsObserver.class));
        Board board = new Board(fields, mock(BettingTiles.class), mock(BoardObserver.class));
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
        Board board = new Board(fields, mock(BettingTiles.class), mock(BoardObserver.class));
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
        Board board = new Board(fields, mock(BettingTiles.class), mock(BoardObserver.class));
        String color = UUID.randomUUID().toString();
        board.moveThePawns(1, color);

        //When
        board.moveThePawns(1, color);

        //Then
        Assert.assertTrue(field2.getNumberOfPawns() == 1);
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
        Board board = new Board(mock(Fields.class), bettingTiles, mock(BoardObserver.class));

        //When
        board.prepareBoard();
        BettingTile result = bettingTiles.getTopBettingTileFromStackInColor(color);

        //Then
        Assert.assertTrue(result.getValue() == value);
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
        Board board = new Board(fields, mock(BettingTiles.class), mock(BoardObserver.class));

        //When
        List<Pawn> result = board.getPawnsInOrder();

        //Then
        Assert.assertTrue(result.get(2).equals(color));
    }
}