package rules.board.tiles.desert;

import org.junit.Assert;
import org.junit.Test;

public class MirageDesertTilePageTest {
    @Test
    public void shouldReturnMinus1() {
        //Given
        MirageDesertTilePage mirageDesertTilePage = new MirageDesertTilePage();

        //When
        int result = mirageDesertTilePage.getBonusPoints();

        //Then
        Assert.assertTrue(result == -1);
    }
}