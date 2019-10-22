package rules.board.tiles.desert;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OasisDesertTilePageTest {
    @Test
    public void shouldReturn1() {
        //Given
        OasisDesertTilePage oasisDesertTilePage = new OasisDesertTilePage();

        //When
        int result = oasisDesertTilePage.getBonusPoints();

        //Then
        assertTrue(result == 1);
    }
}