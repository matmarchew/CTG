package rules.board;

import communication.observer.DesertTileObserver;
import communication.observer.FieldsObserver;
import org.junit.Assert;
import org.junit.Test;
import rules.board.tiles.desert.DesertTile;
import rules.board.tiles.desert.UnclassifiedDesertTilePage;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.mock;

public class FieldsTest {
    @Test
    public void shouldReturnPawnsInOrder() {
        //Given
        Field field = new Field();
        String color = UUID.randomUUID().toString();
        Pawn pawn1 = new Pawn(UUID.randomUUID().toString());
        Pawn pawn2 = new Pawn(UUID.randomUUID().toString());
        Pawn pawn3 = new Pawn(color);
        Pawn pawn4 = new Pawn(UUID.randomUUID().toString());
        new UnclassifiedDesertTilePage().addPawnsToField(field, Arrays.asList(pawn1, pawn2, pawn3, pawn4));
        List<Field> tmpFields = new LinkedList<>();
        tmpFields.add(field);
        Fields fields = new Fields(tmpFields, mock(FieldsObserver.class));

        //When
        List<Pawn> result = fields.getPawnsInOrder();

        //Then
        Assert.assertTrue(result.get(2).equals(color));
    }

    @Test
    public void shouldCheckIfGameIsNoFinished() {
        //Given
        Field field = new Field();
        Fields fields = new Fields(new LinkedList<>(Arrays.asList(field)), mock(FieldsObserver.class));

        //When
        boolean result = fields.isFinished();

        //Then
        Assert.assertTrue(!result);
    }

    @Test
    public void shouldMovePawns() {
        //Given
        Field field = new Field();
        String pawnColor = UUID.randomUUID().toString();
        Pawn pawn = new Pawn(pawnColor);
        Fields fields = new Fields(new LinkedList<>(Arrays.asList(field)), mock(FieldsObserver.class));
        new UnclassifiedDesertTilePage().addPawnsToField(field, Arrays.asList(pawn));

        //When
        fields.moveThePawns(1, pawnColor);

        //Then
        Assert.assertTrue(fields.isFinished());
    }

    @Test
    public void shouldPutDesertTile() {
        //Given
        Field field = new Field();
        Fields fields = new Fields(new LinkedList<>(Arrays.asList(field)), mock(FieldsObserver.class));

        //When
        fields.putDesertTileToField(new DesertTile(UUID.randomUUID().toString(), mock(DesertTileObserver.class)), 0);

        //Then
        Assert.assertTrue(field.containsDesertTile());
    }

    @Test
    public void shouldReturnNumberFieldsWhereArePawnsAndDesertTiles() {
        //Given
        Field field1 = new Field();
        Field field2 = new Field();
        Field field3 = new Field();
        Field field4 = new Field();
        Field field5 = new Field();
        Field field6 = new Field();
        Field field7 = new Field();
        Fields fields = new Fields(new LinkedList<>(Arrays.asList(field1, field2, field3, field4, field5, field6, field7)), mock(FieldsObserver.class));
        field4.putDesertTile(new DesertTile(UUID.randomUUID().toString(), mock(DesertTileObserver.class)));
        new UnclassifiedDesertTilePage().addPawnsToField(field1, Arrays.asList(new Pawn(UUID.randomUUID().toString())));
        new UnclassifiedDesertTilePage().addPawnsToField(field2, Arrays.asList(new Pawn(UUID.randomUUID().toString())));

        //When
        List<String> result = fields.getFieldsWhereNoPutDesertTile();

        //Then
        Assert.assertTrue(result.size() == 5);
    }

    @Test
    public void shouldReturnOneFieldIfFieldsHaveTwoEmptyFields() {
        //Given
        Fields fields = new Fields(new LinkedList<>(Arrays.asList(new Field(), new Field())), mock(FieldsObserver.class));

        //When
        List<String> result = fields.getFieldsWhereNoPutDesertTile();

        //Then
        Assert.assertTrue(result.size() == 1);
    }
}