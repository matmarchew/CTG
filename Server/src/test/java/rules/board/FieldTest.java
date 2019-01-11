package rules.board;

import communication.observer.DesertTileObserver;
import org.junit.Assert;
import org.junit.Test;
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
        field.addPawnsFromTheTop(Arrays.asList(pawn1, pawn2));

        //When
        field.addPawnsFromTheBottom(Arrays.asList(pawn3, pawn4));

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
        field.addPawnsFromTheTop(Arrays.asList(pawn1, pawn2));

        //Then
        Assert.assertTrue(field.getPawns().get(0).equals(color));
    }

    @Test
    public void shouldReturnNFirstPawnsReturnNElements() {
        //Given
        Field field = new Field();
        String color = UUID.randomUUID().toString();
        Pawn pawn1 = new Pawn(color);
        Pawn pawn2 = new Pawn(UUID.randomUUID().toString());
        field.addPawnsFromTheTop(Arrays.asList(pawn1, pawn2));

        //When
        List<Pawn> nPawnsFromStack = field.getPawnsAbovePawnInColor(color);

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
        field.addPawnsFromTheTop(Arrays.asList(pawn1, pawn2, pawn3, pawn4));

        //When
        boolean result = field.isPawnInThisField(color);

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
        field.addPawnsFromTheTop(Arrays.asList(pawn1, pawn2, pawn3, pawn4));

        //When
        boolean result = field.isPawnInThisField(UUID.randomUUID().toString());

        //Then
        Assert.assertTrue(!result);
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
        field.addPawnsFromTheTop(Arrays.asList(pawn1, pawn2, pawn3, pawn4));

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
        field.addPawnsFromTheTop(Arrays.asList(pawn1, pawn2, pawn3, pawn4));

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