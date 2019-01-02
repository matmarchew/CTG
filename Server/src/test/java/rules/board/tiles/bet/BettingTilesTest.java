package rules.board.tiles.bet;

import communication.CustomJSONObject;
import communication.observer.BettingTileObserver;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.mock;

public class BettingTilesTest {
    @Test
    public void shouldReturnSuitableBetTile() {
        //Given
        String color = UUID.randomUUID().toString();
        BettingTile bettingTile = new BettingTile(color, 3, mock(BettingTileObserver.class));
        StackOfBettingTile stackOfBettingTile = new StackOfBettingTile(Arrays.asList(bettingTile));
        BettingTiles bettingTiles = new BettingTiles(Arrays.asList(stackOfBettingTile));
        bettingTiles.prepareStacksOfBettingTile();

        //When
        BettingTile result = bettingTiles.getTopBettingTileFromStackInColor(color);

        //Then
        Assert.assertTrue(result.equals(bettingTile));
    }

    @Test
    public void shouldReturnListWithTopBetTiles() {
        //Given
        String color1 = UUID.randomUUID().toString();
        String color2 = UUID.randomUUID().toString();
        BettingTile bettingTile1 = new BettingTile(color1, 3, mock(BettingTileObserver.class));
        BettingTile bettingTile2 = new BettingTile(color2, 3, mock(BettingTileObserver.class));
        BettingTile bettingTile3 = new BettingTile(color2, 2, mock(BettingTileObserver.class));
        StackOfBettingTile stackOfBettingTile1 = new StackOfBettingTile(Arrays.asList(bettingTile1));
        StackOfBettingTile stackOfBettingTile2 = new StackOfBettingTile(Arrays.asList(bettingTile2, bettingTile3));
        BettingTiles bettingTiles = new BettingTiles(Arrays.asList(stackOfBettingTile1, stackOfBettingTile2));
        bettingTiles.prepareStacksOfBettingTile();

        //When
        List<CustomJSONObject> result = bettingTiles.getTopBettingTileFromEveryStack();

        //Then
        Assert.assertTrue(result.size() == 2);
    }
}