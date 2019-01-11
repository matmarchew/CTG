package rules.action;

import communication.observer.*;
import org.junit.Assert;
import org.junit.Test;
import rules.Cube;
import rules.Cubes;
import rules.board.*;
import rules.board.tiles.bet.BettingTiles;
import rules.board.tiles.desert.DesertTile;
import rules.players.Player;
import rules.players.PlayerSocket;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.mock;

public class ThrowCubeActionTest {
    @Test
    public void shouldDrawnPawnIsMove() {
        //Given
        String color = UUID.randomUUID().toString();
        Cube cube = new Cube(color, mock(CubeObserver.class));
        Field field1 = new Field();
        Field field2 = new Field();
        Field field3 = new Field();
        Field field4 = new Field();
        List<Field> tmpFields = new LinkedList<>();
        tmpFields.add(field1);
        tmpFields.add(field2);
        tmpFields.add(field3);
        tmpFields.add(field4);
        Fields fields = new Fields(tmpFields, mock(FieldsObserver.class));
        Board board = new Board(fields, mock(BettingTiles.class));
        String playerLogin = UUID.randomUUID().toString();
        Player player = new Player(mock(PlayerSocket.class), playerLogin, new BettingCards(new LinkedList<>(Arrays.asList(new BettingCard(color, playerLogin)))), mock(DesertTile.class), mock(PlayerObserver.class));
        Cubes cubes = new Cubes(Arrays.asList(cube), mock(CubesObserver.class));
        cubes.prepareCubes();
        ThrowCubeAction throwCubeAction = new ThrowCubeAction(board, cubes, player);
        board.moveThePawns(1, color);
        cubes.prepareCubes();

        //When
        throwCubeAction.performAction();

        //Then
        Assert.assertTrue(field1.isPawnInThisField(color) || field2.isPawnInThisField(color) ||
                field3.isPawnInThisField(color) || field4.isPawnInThisField(color));
    }

}