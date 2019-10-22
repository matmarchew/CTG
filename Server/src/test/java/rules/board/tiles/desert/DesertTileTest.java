package rules.board.tiles.desert;

import communication.observer.DesertTileObserver;
import org.junit.jupiter.api.Test;
import rules.Messages;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class DesertTileTest {
    @Test
    public void shouldReturn0IfDesertTilePageIsUnclassified() {
        //Given
        DesertTile desertTile = new DesertTile(UUID.randomUUID().toString(), mock(DesertTileObserver.class));

        //When
        int result = desertTile.getBonusPoints();

        //Then
        assertTrue(result == 0);
    }

    @Test
    public void shouldReturnMinus1IfDesertTilePageIsMiragePage() {
        //Given
        DesertTile desertTile = new DesertTile(UUID.randomUUID().toString(), mock(DesertTileObserver.class));
        desertTile.switchPageToActive(Messages.MIRAGE_PAGE);

        //When
        int result = desertTile.getBonusPoints();

        //Then
        assertTrue(result == -1);
    }

    @Test
    public void shouldReturn1IfDesertTilePageIsOasisPage() {
        //Given
        DesertTile desertTile = new DesertTile(UUID.randomUUID().toString(), mock(DesertTileObserver.class));
        desertTile.switchPageToActive(Messages.OASIS_PAGE);

        //When
        int result = desertTile.getBonusPoints();

        //Then
        assertTrue(result == 1);
    }

    @Test
    public void shouldReturnPlayerLogin() {
        //Given
        String login = UUID.randomUUID().toString();
        DesertTile desertTile = new DesertTile(login, mock(DesertTileObserver.class));

        //When
        String result = desertTile.getPlayerLogin();

        //Then
        assertTrue(result.equals(login));
    }
}