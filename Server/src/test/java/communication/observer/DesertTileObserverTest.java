package communication.observer;

import communication.CustomJSONObject;
import communication.WebPageSocket;
import communication.WebPageSocketForTest;
import org.junit.Assert;
import org.junit.Test;
import rules.Messages;

import java.util.Random;
import java.util.UUID;

public class DesertTileObserverTest {
    @Test
    public void shouldJsonMessageIsCorrectlyCreated() {
        //Given
        WebPageSocket webPageSocket = new WebPageSocketForTest();
        DesertTileObserver desertTileObserver = new DesertTileObserver(webPageSocket);
        int number = new Random().nextInt();
        String desertTileAction = UUID.randomUUID().toString();
        String page = UUID.randomUUID().toString();

        //When
        desertTileObserver.createInfoForWeb(number, desertTileAction, page);

        //Then
        CustomJSONObject json = ((WebPageSocketForTest) webPageSocket).getJson();
        Assert.assertTrue(json.getString(Messages.DESERT_TILE).equals(desertTileAction) &&
                json.getString(Messages.FIELD_NUMBER).equals(String.valueOf(number)) &&
                json.getString(Messages.PAGE).equals(page) &&
                json.getString(Messages.OBJECT_TYPE).equals(Messages.DESERT_TILE));
    }
}
