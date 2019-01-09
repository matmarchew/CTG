package communication.observer;

import communication.CustomJSONObject;
import communication.WebPageSocket;
import communication.WebPageSocketForTest;
import org.junit.Assert;
import org.junit.Test;
import rules.Messages;

import java.util.UUID;

public class PlayerObserverTest {
    @Test
    public void shouldJsonMessageIsCorrectlyCreated() {
        //Given
        WebPageSocket webPageSocket = new WebPageSocketForTest();
        PlayerObserver playerObserver = new PlayerObserver(webPageSocket);
        String login = UUID.randomUUID().toString();

        //When
        playerObserver.createInfoForWeb(login);

        //Then
        CustomJSONObject json = ((WebPageSocketForTest) webPageSocket).getJson();
        Assert.assertTrue(json.getString(Messages.LOGIN).equals(login) &&
                json.getString(Messages.OBJECT_TYPE).equals(Messages.PLAYER));
    }
}
