package rules.board;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BettingCardTest {
    @Test
    public void shouldReturnColor() {
        //Given
        String playerLogin = UUID.randomUUID().toString();
        BettingCard bettingCard = new BettingCard(UUID.randomUUID().toString(), playerLogin);

        //When
        String result = bettingCard.getPlayerLogin();

        //Then
        assertTrue(result.equals(playerLogin));
    }
}