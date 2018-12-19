package rules.board.tiles.desert;

import org.junit.Assert;
import org.junit.Test;

public class OasisDesertTilePageTest {
    @Test
    public void shouldReturn1() {
        //Given
        OasisDesertTilePage oasisDesertTilePage = new OasisDesertTilePage();

        //When
        int result = oasisDesertTilePage.getBonusPoints();

        //Then
        Assert.assertTrue(result == 1);
    }
}