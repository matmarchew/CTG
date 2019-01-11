package rules.board.tiles.desert;

import communication.observer.DesertTileObserver;
import org.junit.Assert;
import org.junit.Test;
import rules.Messages;

import java.util.UUID;

import static org.mockito.Mockito.mock;

public class DesertTileTest {
    @Test
    public void shouldReturn0IfDesertTilePageIsUnclassified() {
        //Given
        DesertTile desertTile = new DesertTile(UUID.randomUUID().toString(), mock(DesertTileObserver.class), new DesertTileFactory());

        //When
        int result = desertTile.getBonusPoints();

        //Then
        Assert.assertTrue(result == 0);
    }

    @Test
    public void shouldReturnMinus1IfDesertTilePageIsMiragePage() {
        //Given
        DesertTile desertTile = new DesertTile(UUID.randomUUID().toString(), mock(DesertTileObserver.class), new DesertTileFactory());
        desertTile.switchPageToActive(Messages.MIRAGE_PAGE);

        //When
        int result = desertTile.getBonusPoints();

        //Then
        Assert.assertTrue(result == -1);
    }

    @Test
    public void shouldReturn1IfDesertTilePageIsOasisPage() {
        //Given
        DesertTile desertTile = new DesertTile(UUID.randomUUID().toString(), mock(DesertTileObserver.class), new DesertTileFactory());
        desertTile.switchPageToActive(Messages.OASIS_PAGE);

        //When
        int result = desertTile.getBonusPoints();

        //Then
        Assert.assertTrue(result == 1);
    }

    @Test
    public void shouldReturnPlayerLogin() {
        //Given
        String login = UUID.randomUUID().toString();
        DesertTile desertTile = new DesertTile(login, mock(DesertTileObserver.class), new DesertTileFactory());

        //When
        String result = desertTile.getPlayerLogin();

        //Then
        Assert.assertTrue(result.equals(login));
    }
}