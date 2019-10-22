package rules.action;

import communication.observer.PlayerObserver;
import org.junit.jupiter.api.Test;
import rules.Messages;
import rules.board.*;
import rules.board.tiles.bet.BettingTiles;
import rules.board.tiles.desert.DesertTile;
import rules.players.Player;
import rules.players.PlayerSocket;
import rules.players.Players;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class PutBettingCardToFinalBettingStackActionTest {
    @Test
    public void shouldPutBetCardToWinnerFinalStack() {
        //Given
        String color = UUID.randomUUID().toString();
        String playerLogin = UUID.randomUUID().toString();
        Board board = new Board(mock(Fields.class), mock(BettingTiles.class));
        Player player = new Player(mock(PlayerSocket.class), playerLogin, new BettingCards(new LinkedList<>(Arrays.asList(new BettingCard(color, playerLogin)))), mock(DesertTile.class), mock(PlayerObserver.class));
        PutBettingCardToFinalBettingStackAction putBettingCardToFinalBettingStackAction = new PutBettingCardToFinalBettingStackAction(board, player, color, Messages.WINNER_STACK);
        Players players = new Players(Arrays.asList(player));

        //When
        putBettingCardToFinalBettingStackAction.performAction();
        board.calculatePointsAfterGame(new Pawn(color), new Pawn(UUID.randomUUID().toString()), players);
        int result = player.getPoints();

        //Then
        assertTrue(result == 8);
    }

    @Test
    public void shouldPutBetCardToLoserFinalStack() {
        //Given
        String color = UUID.randomUUID().toString();
        String playerLogin = UUID.randomUUID().toString();
        Board board = new Board(mock(Fields.class), mock(BettingTiles.class));
        Player player = new Player(mock(PlayerSocket.class), playerLogin, new BettingCards(new LinkedList<>(Arrays.asList(new BettingCard(color, playerLogin)))), mock(DesertTile.class), mock(PlayerObserver.class));
        PutBettingCardToFinalBettingStackAction putBettingCardToFinalBettingStackAction = new PutBettingCardToFinalBettingStackAction(board, player, color, Messages.LOSER_STACK);
        Players players = new Players(Arrays.asList(player));

        //When
        putBettingCardToFinalBettingStackAction.performAction();
        board.calculatePointsAfterGame(new Pawn(UUID.randomUUID().toString()), new Pawn(color), players);
        int result = player.getPoints();

        //Then
        assertTrue(result == 8);
    }

}