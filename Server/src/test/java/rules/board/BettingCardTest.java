package rules.board;

import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class BettingCardTest {
    @Test
    public void shouldReturnColor() {
        //Given
        String playerLogin = UUID.randomUUID().toString();
        BettingCard bettingCard = new BettingCard(UUID.randomUUID().toString(), playerLogin);

        //When
        String result = bettingCard.getPlayerLogin();

        //Then
        Assert.assertTrue(result.equals(playerLogin));
    }
}