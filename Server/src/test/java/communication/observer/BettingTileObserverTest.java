package communication.observer;

import communication.CustomJSONObject;
import communication.WebPageSocket;
import communication.WebPageSocketForTest;
import org.junit.Assert;
import org.junit.Test;
import rules.Messages;

import java.util.Random;
import java.util.UUID;

public class BettingTileObserverTest {
    @Test
    public void shouldJsonMessageIsCorrectlyCreated() {
        //Given
        WebPageSocket webPageSocket = new WebPageSocketForTest();
        BettingTileObserver bettingTileObserver = new BettingTileObserver(webPageSocket);
        int value = new Random().nextInt();
        String color = UUID.randomUUID().toString();

        //When
        bettingTileObserver.createInfoForWeb(value, color);

        //Then
        CustomJSONObject json = ((WebPageSocketForTest) webPageSocket).getJson();
        Assert.assertTrue(json.getString(Messages.COLOR).equals(color) &&
                                    json.getString(Messages.VALUE).equals(String.valueOf(value)) &&
                                    json.getString(Messages.OBJECT_TYPE).equals(Messages.BET_TILE));
    }
}
