package rules.board.tiles.bet;

import communication.observer.BettingTileObserver;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.mockito.Mockito.mock;

public class StackOfBettingTileTest {
    @Test
    public void shouldPrepareBetTiles() {
        //Given
        Random random = new Random();
        BettingTile bettingTile = new BettingTile(UUID.randomUUID().toString(), random.nextInt(), mock(BettingTileObserver.class));
        StackOfBettingTile stackOfBettingTile = new StackOfBettingTile(Arrays.asList(bettingTile));
        stackOfBettingTile.prepareBettingTiles();

        //When
        BettingTile result = stackOfBettingTile.getTopBettingTile();

        //Then
        Assert.assertTrue(result.equals(bettingTile));
    }

    @Test
    public void shouldReturnTheTopBetTileOfStack() {
        //Given
        Random random = new Random();
        BettingTile bettingTile = new BettingTile(UUID.randomUUID().toString(), random.nextInt(), mock(BettingTileObserver.class));
        StackOfBettingTile stackOfBettingTile = new StackOfBettingTile(Arrays.asList(bettingTile));
        stackOfBettingTile.prepareBettingTiles();

        //When
        BettingTile result = stackOfBettingTile.getTopBettingTileAndMarkedItAsUsed();

        //Then
        Assert.assertTrue(result.equals(bettingTile));
    }

    @Test
    public void shouldReturnTopBetTile() {
        //Given
        Random random = new Random();
        BettingTile bettingTile1 = new BettingTile(UUID.randomUUID().toString(), random.nextInt(), mock(BettingTileObserver.class));
        BettingTile bettingTile2 = new BettingTile(UUID.randomUUID().toString(), random.nextInt(), mock(BettingTileObserver.class));
        List<BettingTile> bettingTiles = new LinkedList<>();
        bettingTiles.add(bettingTile1);
        bettingTiles.add(bettingTile2);
        StackOfBettingTile stackOfBettingTile = new StackOfBettingTile(bettingTiles);
        stackOfBettingTile.prepareBettingTiles();

        //When
        BettingTile result = stackOfBettingTile.getTopBettingTile();

        //Then
        Assert.assertTrue(result.equals(bettingTile1));
    }

    @Test
    public void shouldReturnEmptyBetTile() {
        //Given
        List<BettingTile> bettingTiles = new LinkedList<>();
        bettingTiles.add(new BettingTile(UUID.randomUUID().toString(), 0, mock(BettingTileObserver.class)));
        StackOfBettingTile stackOfBettingTile = new StackOfBettingTile(bettingTiles);
        stackOfBettingTile.prepareBettingTiles();
        stackOfBettingTile.getTopBettingTileAndMarkedItAsUsed();

        //When
        BettingTile result = stackOfBettingTile.getTopBettingTile();

        //Then
        Assert.assertTrue(result.getValue() == 0);
    }
}