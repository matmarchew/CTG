package rules.board;

import communication.observer.CubeObserver;
import communication.observer.DesertTileObserver;
import communication.observer.FieldsObserver;
import org.junit.jupiter.api.Test;
import rules.Cube;
import rules.board.tiles.desert.DesertTile;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class FieldsTest {
    @Test
    public void shouldReturnNumberFieldContainsPawnInColor() {
        //Given
        Field field = new Field();
        String color = UUID.randomUUID().toString();
        Pawn pawn1 = new Pawn(UUID.randomUUID().toString());
        Pawn pawn2 = new Pawn(UUID.randomUUID().toString());
        Pawn pawn3 = new Pawn(color);
        Pawn pawn4 = new Pawn(UUID.randomUUID().toString());
        field.addPawns(Arrays.asList(pawn1, pawn2, pawn3, pawn4), 1);
        List<Field> tmpFields = new LinkedList<>();
        tmpFields.add(field);
        Fields fields = new Fields(tmpFields, mock(FieldsObserver.class));

        //When
        int result = fields.searchFieldOnThePawnIsStandingUsingCube(new Cube(color, mock(CubeObserver.class)));

        //Then
        assertTrue(result == 0);
    }

    @Test
    public void shouldReturnPawnsInOrder() {
        //Given
        Field field = new Field();
        String color = UUID.randomUUID().toString();
        Pawn pawn1 = new Pawn(UUID.randomUUID().toString());
        Pawn pawn2 = new Pawn(UUID.randomUUID().toString());
        Pawn pawn3 = new Pawn(color);
        Pawn pawn4 = new Pawn(UUID.randomUUID().toString());
        field.addPawns(Arrays.asList(pawn1, pawn2, pawn3, pawn4) ,1);
        List<Field> tmpFields = new LinkedList<>();
        tmpFields.add(field);
        Fields fields = new Fields(tmpFields, mock(FieldsObserver.class));

        //When
        List<Pawn> result = fields.getPawnsInOrder();

        //Then
        assertTrue(result.get(2).equals(color));
    }

    @Test
    public void shouldAddPawnsToFinishedField() {
        //Given
        Field field = new Field();
        Fields fields = new Fields(new LinkedList<>(Arrays.asList(field)), mock(FieldsObserver.class));
        String pawnColor = UUID.randomUUID().toString();
        Pawn pawn = new Pawn(pawnColor);
        List<Pawn> pawns = new LinkedList<>(Arrays.asList(pawn));

        //When
        fields.makePawnMoveToDestinationField(2, pawns);

        //Then
        assertTrue(fields.isFinished());
    }

    @Test
    public void shouldCheckIfGameIsFinished() {
        //Given
        Field field = new Field();
        Fields fields = new Fields(new LinkedList<>(Arrays.asList(field)), mock(FieldsObserver.class));
        String pawnColor = UUID.randomUUID().toString();
        Pawn pawn = new Pawn(pawnColor);
        List<Pawn> pawns = new LinkedList<>(Arrays.asList(pawn));

        //When
        fields.makePawnMoveToDestinationField(1, pawns);

        //Then
        assertTrue(fields.isFinished());
    }

    @Test
    public void shouldCheckIfGameIsNoFinished() {
        //Given
        Field field = new Field();
        Fields fields = new Fields(new LinkedList<>(Arrays.asList(field)), mock(FieldsObserver.class));

        //When
        boolean result = fields.isFinished();

        //Then
        assertTrue(!result);
    }

    @Test
    public void shouldReturnNumberOfFields() {
        //Given
        Field field = new Field();
        Fields fields = new Fields(new LinkedList<>(Arrays.asList(field)), mock(FieldsObserver.class));

        //When
        int result = fields.numberOfFields();

        //Then
        assertTrue(result == 1);
    }

    @Test
    public void shouldReturnStartingPawn() {
        //Given
        Cube cube = new Cube(UUID.randomUUID().toString(), mock(CubeObserver.class));
        Field field = new Field();
        Fields fields = new Fields(new LinkedList<>(Arrays.asList(field)), mock(FieldsObserver.class));

        //When
        List<Pawn> result = fields.getPawnsToMoveFromField(-1, cube);

        //Then
        assertTrue(result.size() == 1);
    }

    @Test
    public void shouldReturnPawnsFromField() {
        //Given
        String color = UUID.randomUUID().toString();
        Cube cube = new Cube(color, mock(CubeObserver.class));
        Pawn pawn1 = new Pawn(UUID.randomUUID().toString());
        Pawn pawn2 = new Pawn(UUID.randomUUID().toString());
        Pawn pawn3 = new Pawn(color);
        Pawn pawn4 = new Pawn(UUID.randomUUID().toString());
        Field field = new Field();
        field.addPawns(Arrays.asList(pawn1, pawn2, pawn3, pawn4), 1);
        Fields fields = new Fields(new LinkedList<>(Arrays.asList(field)), mock(FieldsObserver.class));

        //When
        List<Pawn> result = fields.getPawnsToMoveFromField(0, cube);

        //Then
        assertTrue(result.size() == 3);
    }

    @Test
    public void shouldReturnReturnedDesertTile() {
        //Given
        Field field = new Field();
        String playerLogin = UUID.randomUUID().toString();
        field.putDesertTile(new DesertTile(playerLogin, mock(DesertTileObserver.class)));
        Pawn pawn = new Pawn(UUID.randomUUID().toString());
        Fields fields = new Fields(new LinkedList<>(Arrays.asList(field)), mock(FieldsObserver.class));
        fields.makePawnMoveToDestinationField(0, Arrays.asList(pawn));

        //When
        DesertTile result = fields.getReturnedDesertTile();

        //Then
        assertTrue(result.equals(playerLogin));
    }

    @Test
    public void shouldMovePawns() {
        //Given
        Field field = new Field();
        Pawn pawn = new Pawn(UUID.randomUUID().toString());
        Fields fields = new Fields(new LinkedList<>(Arrays.asList(field)), mock(FieldsObserver.class));

        //When
        fields.makePawnMoveToDestinationField(1, Arrays.asList(pawn));

        //Then
        assertTrue(fields.isFinished());
    }

    @Test
    public void shouldPutDesertTile() {
        //Given
        Field field = new Field();
        Fields fields = new Fields(new LinkedList<>(Arrays.asList(field)), mock(FieldsObserver.class));

        //When
        fields.putDesertTileToField(new DesertTile(UUID.randomUUID().toString(), mock(DesertTileObserver.class)), 0);

        //Then
        assertTrue(field.containsDesertTile());
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
        fields.makePawnMoveToDestinationField(0, Arrays.asList(new Pawn(UUID.randomUUID().toString())));
        fields.makePawnMoveToDestinationField(1, Arrays.asList(new Pawn(UUID.randomUUID().toString())));

        //When
        List<String> result = fields.getFieldsWhereNoPutDesertTile();

        //Then
        assertTrue(result.size() == 5);
    }

    @Test
    public void shouldReturnOneFieldIfFieldsHaveTwoEmptyFields() {
        //Given
        Fields fields = new Fields(new LinkedList<>(Arrays.asList(new Field(), new Field())), mock(FieldsObserver.class));

        //When
        List<String> result = fields.getFieldsWhereNoPutDesertTile();

        //Then
        assertTrue(result.size() == 1);
    }
}