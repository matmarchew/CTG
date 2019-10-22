package rules.board;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BettingFinalResultTest {
    @Test
    public void shouldReturnPlayersLoginWithGoodBetToBetCards() {
        //Given
        String playerLogin1 = UUID.randomUUID().toString();
        String playerLogin2 = UUID.randomUUID().toString();
        String color = UUID.randomUUID().toString();

        BettingFinalResult bettingFinalResult = new BettingFinalResult();
        bettingFinalResult.addBettingCardToFinalStack(new BettingCard(color, playerLogin1));
        bettingFinalResult.addBettingCardToFinalStack(new BettingCard(color, playerLogin2));
        bettingFinalResult.addBettingCardToFinalStack(new BettingCard(UUID.randomUUID().toString(), UUID.randomUUID().toString()));

        //When
        List<String> result = bettingFinalResult.getPlayersLoginFromBettingCardsInOrder(new Pawn(color), true);

        //Then
        assertTrue(result.get(0).equals(playerLogin1) && result.get(1).equals(playerLogin2));
    }

    @Test
    public void shouldReturnPlayersLoginWithNoGoodBetToBetCards() {
        //Given
        String playerLogin1 = UUID.randomUUID().toString();
        String playerLogin2 = UUID.randomUUID().toString();
        String color = UUID.randomUUID().toString();

        BettingFinalResult bettingFinalResult = new BettingFinalResult();
        bettingFinalResult.addBettingCardToFinalStack(new BettingCard(color, playerLogin1));
        bettingFinalResult.addBettingCardToFinalStack(new BettingCard(color, playerLogin2));
        bettingFinalResult.addBettingCardToFinalStack(new BettingCard(UUID.randomUUID().toString(), UUID.randomUUID().toString()));

        //When
        List<String> result = bettingFinalResult.getPlayersLoginFromBettingCardsInOrder(new Pawn(color), false);

        //Then
        assertTrue(result.size() == 1);
    }

    @Test
    public void shouldAddCardToStack() {
        //Given
        BettingFinalResult bettingFinalResult = new BettingFinalResult();
        String color = UUID.randomUUID().toString();

        //When
        bettingFinalResult.addBettingCardToFinalStack(new BettingCard(color, UUID.randomUUID().toString()));
        List<String> result = bettingFinalResult.getPlayersLoginFromBettingCardsInOrder(new Pawn(color), true);

        //Then
        assertTrue(result.size() == 1);
    }
}