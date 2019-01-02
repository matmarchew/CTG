package rules.board.tiles.desert;

import org.junit.Assert;
import org.junit.Test;

public class UnclassifiedDesertTilePageTest {
    @Test
    public void shouldReturn0() {
        //Given
        UnclassifiedDesertTilePage unclassifiedDesertTilePage = new UnclassifiedDesertTilePage();
        //When
        int result = unclassifiedDesertTilePage.getBonusPoints();

        //Then
        Assert.assertTrue(result == 0);
    }

}