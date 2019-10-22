package rules.players;

import communication.observer.BettingTileObserver;
import communication.observer.PlayerObserver;
import org.junit.jupiter.api.Test;
import rules.board.BettingCard;
import rules.board.BettingCards;
import rules.board.BettingFinalResult;
import rules.board.Pawn;
import rules.board.tiles.bet.BettingTile;
import rules.board.tiles.desert.DesertTile;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class PlayersTest {
    @Test
    public void shouldReturnFirstPlayer() {
        //Given
        Player player = new Player(mock(PlayerSocket.class), UUID.randomUUID().toString(), mock(BettingCards.class), mock(DesertTile.class), mock(PlayerObserver.class));
        Players players = new Players(Arrays.asList(player));

        //When
        Player result = players.getNextPlayer();

        //Then
        assertTrue(result.equals(player));
    }

    @Test
    public void shouldReturnNextPlayer() {
        //Given
        Player player1 = new Player(mock(PlayerSocket.class), UUID.randomUUID().toString(), mock(BettingCards.class), mock(DesertTile.class), mock(PlayerObserver.class));
        Player player2 = new Player(mock(PlayerSocket.class), UUID.randomUUID().toString(), mock(BettingCards.class), mock(DesertTile.class), mock(PlayerObserver.class));
        Players players = new Players(Arrays.asList(player1, player2));
        players.getNextPlayer();

        //When
        Player result = players.getNextPlayer();

        //Then
        assertTrue(result.equals(player2));
    }

    @Test
    public void shouldReturnFirstPlayerAfterLastPlayerEndRound() {
        //Given
        Player player1 = new Player(mock(PlayerSocket.class), UUID.randomUUID().toString(), mock(BettingCards.class), mock(DesertTile.class), mock(PlayerObserver.class));
        Player player2 = new Player(mock(PlayerSocket.class), UUID.randomUUID().toString(), mock(BettingCards.class), mock(DesertTile.class), mock(PlayerObserver.class));
        Players players = new Players(Arrays.asList(player1, player2));
        players.getNextPlayer();
        players.getNextPlayer();

        //When
        Player result = players.getNextPlayer();

        //Then
        assertTrue(result.equals(player1));
    }

    @Test
    public void shouldReturnPlayersInDescendingOrderByPoints() {
        //Given
        Player player1 = new Player(mock(PlayerSocket.class), UUID.randomUUID().toString(), mock(BettingCards.class), mock(DesertTile.class), mock(PlayerObserver.class));
        Player player2 = new Player(mock(PlayerSocket.class), UUID.randomUUID().toString(), mock(BettingCards.class), mock(DesertTile.class), mock(PlayerObserver.class));
        Players players = new Players(Arrays.asList(player1, player2));
        String winningColor = UUID.randomUUID().toString();
        String runnerUpColor = UUID.randomUUID().toString();
        player1.addBettingTileToPlayer(new BettingTile(winningColor, 5, mock(BettingTileObserver.class)));
        player2.addBettingTileToPlayer(new BettingTile(runnerUpColor, 3, mock(BettingTileObserver.class)));

        //When
        List<Player> result = players.sortPlayersInDescendingOrder();

        //Then
        assertTrue(result.get(0).equals(player1) && result.get(1).equals(player2));
    }

    @Test
    public void shouldCalculatePointsAfterGameForWinnerStack() {
        //Given
        String player1Login = UUID.randomUUID().toString();
        String player2Login = UUID.randomUUID().toString();
        Player player1 = new Player(mock(PlayerSocket.class), player1Login, mock(BettingCards.class), mock(DesertTile.class), mock(PlayerObserver.class));
        Player player2 = new Player(mock(PlayerSocket.class), player2Login, mock(BettingCards.class), mock(DesertTile.class), mock(PlayerObserver.class));
        Players players = new Players(Arrays.asList(player1, player2));
        String winningColor = UUID.randomUUID().toString();
        BettingFinalResult winningBet = new BettingFinalResult();
        winningBet.addBettingCardToFinalStack(new BettingCard(winningColor, player1Login));
        winningBet.addBettingCardToFinalStack(new BettingCard(winningColor, player2Login));
        winningBet.addBettingCardToFinalStack(new BettingCard(UUID.randomUUID().toString(), player2Login));

        //When
        players.calculatePointsAfterGameForStack(new Pawn(winningColor), winningBet);

        //Then
        assertTrue(player1.getPoints() == 8 && player2.getPoints() == 4);
    }

}