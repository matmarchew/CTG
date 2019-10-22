package rules.board.tiles.desert;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MirageDesertTilePageTest {
    @Test
    public void shouldReturnMinus1() {
        //Given
        MirageDesertTilePage mirageDesertTilePage = new MirageDesertTilePage();

        //When
        int result = mirageDesertTilePage.getBonusPoints();

        //Then
        assertTrue(result == -1);
    }
}