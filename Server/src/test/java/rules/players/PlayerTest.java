package rules.players;

import communication.CustomJSONObject;
import communication.observer.BettingTileObserver;
import communication.observer.DesertTileObserver;
import communication.observer.PlayerObserver;
import org.junit.Assert;
import org.junit.Test;
import rules.board.BettingCard;
import rules.board.BettingCards;
import rules.board.Pawn;
import rules.board.tiles.bet.BettingTile;
import rules.board.tiles.desert.DesertTile;
import rules.board.tiles.desert.DesertTileFactory;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.mock;

public class PlayerTest {
    @Test
    public void shouldAddPointsToPlayerUsingBetTilesAfterRound() {
        //Given
        Player player = new Player(mock(PlayerSocket.class), UUID.randomUUID().toString(), mock(BettingCards.class), mock(DesertTile.class), mock(PlayerObserver.class));
        String winningColor = UUID.randomUUID().toString();
        String runnerUpColor = UUID.randomUUID().toString();
        player.addBettingTileToPlayer(new BettingTile(winningColor, 5, mock(BettingTileObserver.class)));
        player.addBettingTileToPlayer(new BettingTile(runnerUpColor, 3, mock(BettingTileObserver.class)));
        player.addBettingTileToPlayer(new BettingTile(UUID.randomUUID().toString(), 2, mock(BettingTileObserver.class)));

        //When
        player.calculatePointsAfterRound(new Pawn(winningColor), new Pawn(runnerUpColor));

        //Then
        Assert.assertTrue(player.getPoints() == 5);
    }

    @Test
    public void shouldReturnBetCardInColor() {
        //Given
        String color = UUID.randomUUID().toString();
        BettingCard bettingCard = new BettingCard(color, UUID.randomUUID().toString());
        Player player = new Player(mock(PlayerSocket.class), UUID.randomUUID().toString(), new BettingCards(Arrays.asList(bettingCard)), mock(DesertTile.class), mock(PlayerObserver.class));

        //When
        BettingCard result = player.getBettingCardInColor(color);

        //Then
        Assert.assertTrue(result.equals(bettingCard));
    }

    @Test
    public void shouldReturnDesertTile() {
        //Given
        String playerLogin = UUID.randomUUID().toString();
        Player player = new Player(mock(PlayerSocket.class), playerLogin, mock(BettingCards.class), new DesertTile(playerLogin, mock(DesertTileObserver.class), new DesertTileFactory()), mock(PlayerObserver.class));

        //When
        DesertTile desertTile = player.getPlayerDesertTile();

        //Then
        Assert.assertTrue(desertTile.equals(playerLogin));
    }

    @Test
    public void shouldSetDesertTile() {
        //Given
        String playerLogin = UUID.randomUUID().toString();
        Player player = new Player(mock(PlayerSocket.class), playerLogin, mock(BettingCards.class), new DesertTile(playerLogin, mock(DesertTileObserver.class), new DesertTileFactory()), mock(PlayerObserver.class));
        DesertTile desertTile = player.getPlayerDesertTile();

        //When
        player.setDesertTile(desertTile);
        desertTile = player.getPlayerDesertTile();

        //Then
        Assert.assertTrue(desertTile.equals(playerLogin));
    }

    @Test
    public void shouldAddPoints() {
        //Given
        Player player = new Player(mock(PlayerSocket.class), UUID.randomUUID().toString(), mock(BettingCards.class), mock(DesertTile.class), mock(PlayerObserver.class));

        //When
        player.addPoints(2);
        int result = player.getPoints();

        //Then
        Assert.assertTrue(result == 2);
    }

    @Test
    public void shouldReturnTrueIfDesertTileIsNotUsed() {
        //Given
        Player player = new Player(mock(PlayerSocket.class), UUID.randomUUID().toString(), mock(BettingCards.class), mock(DesertTile.class), mock(PlayerObserver.class));

        //When
        boolean result = player.isHaveDesertTile();

        //Then
        Assert.assertTrue(result);
    }

    @Test
    public void shouldReturnFalseIfDesertTileIsUsed() {
        //Given
        Player player = new Player(mock(PlayerSocket.class), UUID.randomUUID().toString(), mock(BettingCards.class), mock(DesertTile.class), mock(PlayerObserver.class));

        //When
        player.getPlayerDesertTile();
        boolean result = player.isHaveDesertTile();

        //Then
        Assert.assertTrue(!result);
    }

    @Test
    public void shouldReturnNoUsedBetCards() {
        //Given
        String color1 = UUID.randomUUID().toString();
        String color2 = UUID.randomUUID().toString();
        BettingCard bettingCard1 = new BettingCard(color1, UUID.randomUUID().toString());
        BettingCard bettingCard2 = new BettingCard(color2, UUID.randomUUID().toString());
        BettingCards bettingCards = new BettingCards(Arrays.asList(bettingCard1, bettingCard2));
        bettingCards.getBettingCardInColor(color2);

        Player player = new Player(mock(PlayerSocket.class), UUID.randomUUID().toString(), bettingCards, mock(DesertTile.class), mock(PlayerObserver.class));

        //When
        List<CustomJSONObject> result = player.getUsedBetCards();

        //Then
        Assert.assertTrue(result.size() == 1);
    }
}