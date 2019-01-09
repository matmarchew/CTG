package communication.observer;

import communication.CustomJSONObject;
import communication.WebPageSocket;
import communication.WebPageSocketForTest;
import org.junit.Assert;
import org.junit.Test;
import rules.Messages;

import java.util.Random;
import java.util.UUID;

public class CubeObserverTest {
    @Test
    public void shouldJsonMessageIsCorrectlyCreated() {
        //Given
        WebPageSocket webPageSocket = new WebPageSocketForTest();
        CubeObserver cubeObserver = new CubeObserver(webPageSocket);
        int value = new Random().nextInt();
        String color = UUID.randomUUID().toString();

        //When
        cubeObserver.createInfoForWeb(color, value);

        //Then
        CustomJSONObject json = ((WebPageSocketForTest) webPageSocket).getJson();
        Assert.assertTrue(json.getString(Messages.COLOR).equals(color) &&
                json.getString(Messages.VALUE).equals(String.valueOf(value)) &&
                json.getString(Messages.OBJECT_TYPE).equals(Messages.CUBE));
    }
}
