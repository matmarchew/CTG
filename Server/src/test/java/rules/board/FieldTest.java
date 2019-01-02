package rules.board;

import communication.observer.DesertTileObserver;
import org.junit.Assert;
import org.junit.Test;
import rules.Cube;
import rules.Messages;
import rules.board.tiles.desert.DesertTile;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.mock;

public class FieldTest {
    @Test
    public void shouldAddPawnsToDown() {
        //Given
        Field field = new Field();
        String color = UUID.randomUUID().toString();
        Pawn pawn1 = new Pawn(UUID.randomUUID().toString());
        Pawn pawn2 = new Pawn(UUID.randomUUID().toString());
        Pawn pawn3 = new Pawn(color);
        Pawn pawn4 = new Pawn(UUID.randomUUID().toString());
        field.addPawns(Arrays.asList(pawn1, pawn2), 1);

        //When
        field.addPawns(Arrays.asList(pawn3, pawn4), -1);

        //Then
        Assert.assertTrue(field.getPawns().get(2).equals(color));
    }

    @Test
    public void shouldAddPawnsToTop() {
        //Given
        Field field = new Field();
        String color = UUID.randomUUID().toString();
        Pawn pawn1 = new Pawn(color);
        Pawn pawn2 = new Pawn(UUID.randomUUID().toString());

        //When
        field.addPawns(Arrays.asList(pawn1, pawn2), 1);

        //Then
        Assert.assertTrue(field.getPawns().get(0).equals(color));
    }

    @Test
    public void shouldReturnNFirstPawnsReturnNElements() {
        //Given
        Field field = new Field();
        Pawn pawn1 = new Pawn(UUID.randomUUID().toString());
        Pawn pawn2 = new Pawn(UUID.randomUUID().toString());
        field.addPawns(Arrays.asList(pawn1, pawn2), 1);

        //When
        List<Pawn> nPawnsFromStack = field.getFirstNPawnsFromStack(1);

        //Then
        Assert.assertTrue(nPawnsFromStack.size() == 1);
    }

    @Test
    public void shouldReturnTrueIfPawnIsInTheField() {
        //Given
        Field field = new Field();
        String color = UUID.randomUUID().toString();
        Pawn pawn1 = new Pawn(UUID.randomUUID().toString());
        Pawn pawn2 = new Pawn(UUID.randomUUID().toString());
        Pawn pawn3 = new Pawn(color);
        Pawn pawn4 = new Pawn(UUID.randomUUID().toString());
        field.addPawns(Arrays.asList(pawn1, pawn2, pawn3, pawn4), 1);

        //When
        boolean result = field.isPawnInThisField(new Cube(color));

        //Then
        Assert.assertTrue(result);
    }

    @Test
    public void shouldReturnFalseIfPawnIsNotInTheField() {
        //Given
        Field field = new Field();
        Pawn pawn1 = new Pawn(UUID.randomUUID().toString());
        Pawn pawn2 = new Pawn(UUID.randomUUID().toString());
        Pawn pawn3 = new Pawn(UUID.randomUUID().toString());
        Pawn pawn4 = new Pawn(UUID.randomUUID().toString());
        field.addPawns(Arrays.asList(pawn1, pawn2, pawn3, pawn4), 1);

        //When
        boolean result = field.isPawnInThisField(new Cube(UUID.randomUUID().toString()));

        //Then
        Assert.assertTrue(!result);
    }

    @Test
    public void shouldReturnPawnPositionInField() {
        //Given
        Field field = new Field();
        String color = UUID.randomUUID().toString();
        Pawn pawn1 = new Pawn(UUID.randomUUID().toString());
        Pawn pawn2 = new Pawn(UUID.randomUUID().toString());
        Pawn pawn3 = new Pawn(color);
        Pawn pawn4 = new Pawn(UUID.randomUUID().toString());
        field.addPawns(Arrays.asList(pawn1, pawn2, pawn3, pawn4), 1);

        //When
        int result = field.getPawnPositionInStack(new Cube(color));

        //Then
        Assert.assertTrue(result == 2);
    }

    @Test
    public void shouldReturnZeroIfDesertTileIsNull() {
        //Given
        Field field = new Field();

        //When
        int result = field.getBonusFromDesertTile();

        //Then
        Assert.assertTrue(result == 0);
    }

    @Test
    public void shouldReturnOneIfDesertTileIsOnOasisPage() {
        //Given
        Field field = new Field();
        String playerLogin = UUID.randomUUID().toString();
        DesertTile desertTile = new DesertTile(playerLogin, mock(DesertTileObserver.class));
        desertTile.switchPageToActive(Messages.OASIS_PAGE);
        field.putDesertTile(desertTile);

        //When
        int result = field.getBonusFromDesertTile();

        //Then
        Assert.assertTrue(result == 1);
    }

    @Test
    public void shouldReturnMinusOneIfDesertTileIsOnMiragePage() {
        //Given
        Field field = new Field();
        String playerLogin = UUID.randomUUID().toString();
        DesertTile desertTile = new DesertTile(playerLogin, mock(DesertTileObserver.class));
        desertTile.switchPageToActive(Messages.MIRAGE_PAGE);
        field.putDesertTile(desertTile);

        //When
        int result = field.getBonusFromDesertTile();

        //Then
        Assert.assertTrue(result == -1);
    }

    @Test
    public void shouldReturnSizeOfPawnsList() {
        //Given
        Field field = new Field();
        String color = UUID.randomUUID().toString();
        Pawn pawn1 = new Pawn(UUID.randomUUID().toString());
        Pawn pawn2 = new Pawn(UUID.randomUUID().toString());
        Pawn pawn3 = new Pawn(color);
        Pawn pawn4 = new Pawn(UUID.randomUUID().toString());
        field.addPawns(Arrays.asList(pawn1, pawn2, pawn3, pawn4), 1);

        //When
        int result = field.getNumberOfPawns();

        //Then
        Assert.assertTrue(result == 4);
    }

    @Test
    public void shouldPutDesertTile() {
        //Given
        Field field = new Field();
        String playerLogin = UUID.randomUUID().toString();
        DesertTile desertTile = new DesertTile(playerLogin, mock(DesertTileObserver.class));

        //When
        field.putDesertTile(desertTile);
        DesertTile result = field.getDesertTileAndClearIt();

        //Then
        Assert.assertTrue(result.equals(playerLogin));
    }

    @Test
    public void shouldReturnEmptyDesertTile() {
        //Given
        Field field = new Field();

        //When
        DesertTile result = field.getDesertTileAndClearIt();

        //Then
        Assert.assertTrue(result == null);
    }

    @Test
    public void shouldReturnNoEmptyDesertTile() {
        //Given
        Field field = new Field();
        String playerLogin = UUID.randomUUID().toString();
        DesertTile desertTile = new DesertTile(playerLogin, mock(DesertTileObserver.class));
        field.putDesertTile(desertTile);

        //When
        DesertTile result = field.getDesertTileAndClearIt();

        //Then
        Assert.assertTrue(result.equals(playerLogin));
    }

    @Test
    public void shouldReturnPawns() {
        //Given
        Field field = new Field();
        String color = UUID.randomUUID().toString();
        Pawn pawn1 = new Pawn(UUID.randomUUID().toString());
        Pawn pawn2 = new Pawn(UUID.randomUUID().toString());
        Pawn pawn3 = new Pawn(color);
        Pawn pawn4 = new Pawn(UUID.randomUUID().toString());
        field.addPawns(Arrays.asList(pawn1, pawn2, pawn3, pawn4), 1);

        //When
        List<Pawn> pawns = field.getPawns();

        //Then
        Assert.assertTrue(pawns.size() == 4);
    }

    @Test
    public void shouldReturnTrueIfFieldContainsDesertTile() {
        //Given
        Field field = new Field();
        field.putDesertTile(new DesertTile(UUID.randomUUID().toString(), mock(DesertTileObserver.class)));

        //When
        boolean result = field.containsDesertTile();

        //Then
        Assert.assertTrue(result);
    }

    @Test
    public void shouldReturnFalseIfFieldNoContainsDesertTile() {
        //Given
        Field field = new Field();

        //When
        boolean result = field.containsDesertTile();

        //Then
        Assert.assertTrue(!result);
    }
}