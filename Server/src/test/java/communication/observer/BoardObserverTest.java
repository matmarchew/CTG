package communication.observer;

import communication.CustomJSONObject;
import communication.WebPageSocket;
import communication.WebPageSocketForTest;
import org.junit.Assert;
import org.junit.Test;
import rules.Messages;

public class BoardObserverTest {
    @Test
    public void shouldJsonMessageIsCorrectlyCreated() {
        //Given
        WebPageSocket webPageSocket = new WebPageSocketForTest();
        BoardObserver boardObserver = new BoardObserver(webPageSocket);

        //When
        boardObserver.createInfoForWeb();

        //Then
        CustomJSONObject json = ((WebPageSocketForTest) webPageSocket).getJson();
        Assert.assertTrue(json.getString(Messages.OBJECT_TYPE).equals(Messages.REFRESH));
    }
}
