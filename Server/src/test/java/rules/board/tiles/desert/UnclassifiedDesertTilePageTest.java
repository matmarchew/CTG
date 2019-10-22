package rules.board.tiles.desert;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnclassifiedDesertTilePageTest {
    @Test
    public void shouldReturn0() {
        //Given
        UnclassifiedDesertTilePage unclassifiedDesertTilePage = new UnclassifiedDesertTilePage();
        //When
        int result = unclassifiedDesertTilePage.getBonusPoints();

        //Then
        assertTrue(result == 0);
    }

}