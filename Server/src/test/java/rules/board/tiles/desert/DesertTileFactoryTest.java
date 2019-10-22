package rules.board.tiles.desert;

import org.junit.jupiter.api.Test;
import rules.Messages;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DesertTileFactoryTest {
    @Test
    public void shouldReturnOasisPage() {

        ////Given-When
        DesertTilePage result = DesertTileFactory.getActivePage(Messages.OASIS_PAGE);
        //Then
        assertTrue(result instanceof OasisDesertTilePage);
    }

    @Test
    public void shouldReturnEmptyPage() {

        ////Given-When
        DesertTilePage result = DesertTileFactory.getActivePage(UUID.randomUUID().toString());

        //Then
        assertTrue(result instanceof UnclassifiedDesertTilePage);
    }

    @Test
    public void shouldReturnMiragePage() {

        ////Given-When
        DesertTilePage result = DesertTileFactory.getActivePage(Messages.MIRAGE_PAGE);
        //Then
        assertTrue(result instanceof MirageDesertTilePage);
    }

}