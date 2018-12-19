package rules.board.tiles.bet;

import communication.observer.BettingTileObserver;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;

import static org.mockito.Mockito.mock;

public class BettingTileTest {
    @Test
    public void shouldReturnColor() {
        //Given
        String color = UUID.randomUUID().toString();
        Random random = new Random();
        BettingTile bettingTile = new BettingTile(color, random.nextInt(), mock(BettingTileObserver.class));

        //When
        String result = bettingTile.getColor();

        //Then
        Assert.assertTrue(result.equals(color));
    }

    @Test
    public void shouldReturnValue() {
        //Given
        Random random = new Random();
        int value = random.nextInt();
        BettingTile bettingTile = new BettingTile(UUID.randomUUID().toString(), value, mock(BettingTileObserver.class));

        //When
        int result = bettingTile.getValue();

        //Then
        Assert.assertTrue(result == value);
    }

}