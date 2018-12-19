package rules.board.tiles.desert;

import org.junit.Assert;
import org.junit.Test;
import rules.Messages;

import java.util.UUID;

public class DesertTileFactoryTest {
    @Test
    public void shouldReturnOasisPage() {

        ////Given-When
        DesertTilePage result = DesertTileFactory.getActivePage(Messages.OASIS_PAGE);
        //Then
        Assert.assertTrue(result instanceof OasisDesertTilePage);
    }

    @Test
    public void shouldReturnEmptyPage() {

        ////Given-When
        DesertTilePage result = DesertTileFactory.getActivePage(UUID.randomUUID().toString());

        //Then
        Assert.assertTrue(result instanceof UnclassifiedDesertTilePage);
    }

    @Test
    public void shouldReturnMiragePage() {

        ////Given-When
        DesertTilePage result = DesertTileFactory.getActivePage(Messages.MIRAGE_PAGE);
        //Then
        Assert.assertTrue(result instanceof MirageDesertTilePage);
    }

}