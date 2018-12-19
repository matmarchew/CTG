package rules.action;

import communication.observer.BettingTileObserver;
import org.junit.Assert;
import org.junit.Test;
import rules.board.BettingCards;
import rules.board.Pawn;
import rules.board.tiles.bet.BettingTile;
import rules.board.tiles.bet.BettingTiles;
import rules.board.tiles.bet.StackOfBettingTile;
import rules.board.tiles.desert.DesertTile;
import rules.players.Player;
import rules.players.PlayerSocket;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.UUID;

import static org.mockito.Mockito.mock;

public class TakeBettingTileFromBoardActionTest {
    @Test
    public void shouldPickUpBetTileByPlayerFromBoard(){
        //Given
        String color = UUID.randomUUID().toString();
        StackOfBettingTile stackOfBettingTile = new StackOfBettingTile(new LinkedList<>(Arrays.asList(new BettingTile(color, 5, mock(BettingTileObserver.class)))));
        BettingTiles bettingTiles = new BettingTiles(new LinkedList<>(Arrays.asList(stackOfBettingTile)));
        Player player = new Player(mock(PlayerSocket.class), UUID.randomUUID().toString(), mock(BettingCards.class), mock(DesertTile.class));
        TakeBettingTileFromBoardAction betTileFromBoardAction = new TakeBettingTileFromBoardAction(bettingTiles, player, color);
        stackOfBettingTile.prepareBettingTiles();

        //When
        betTileFromBoardAction.performAction();
        player.calculatePointsAfterRound(new Pawn(color), new Pawn(UUID.randomUUID().toString()));

        //Then
        Assert.assertTrue(player.getPoints() == 5);
    }
}