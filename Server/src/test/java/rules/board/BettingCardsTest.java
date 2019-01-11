package rules.board;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class BettingCardsTest {
    @Test
    public void shouldReturnBetCardInColor() {
        //Given
        String color = UUID.randomUUID().toString();
        BettingCard bettingCard1 = new BettingCard(color, UUID.randomUUID().toString());
        BettingCard bettingCard2 = new BettingCard(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        BettingCards bettingCards = new BettingCards(Arrays.asList(bettingCard1, bettingCard2));

        //When
        BettingCard result = bettingCards.getBettingCardInColor(color);

        //Then
        Assert.assertTrue(result.equals(color));
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

        //When
        List<BettingCard> result = bettingCards.getUsedBettingCards();

        //Then
        Assert.assertTrue(result.get(0).equals(color2));
    }

}