package communication.observer;

import communication.CustomJSONObject;
import communication.WebPageSocket;
import communication.WebPageSocketForTest;
import org.junit.Assert;
import org.junit.Test;
import rules.Messages;

import java.util.Random;
import java.util.UUID;

public class CubesObserverTest {
    @Test
    public void shouldJsonMessageIsCorrectlyCreated() {
        //Given
        WebPageSocket webPageSocket = new WebPageSocketForTest();
        CubesObserver cubesObserver = new CubesObserver(webPageSocket);
        int value = new Random().nextInt();
        String color = UUID.randomUUID().toString();

        //When
        cubesObserver.createInfoForWeb();

        //Then
        CustomJSONObject json = ((WebPageSocketForTest) webPageSocket).getJson();
        Assert.assertTrue(json.getString(Messages.OBJECT_TYPE).equals(Messages.REFRESH));
    }
}
