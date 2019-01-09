package communication.observer;

import communication.CustomJSONObject;
import communication.WebPageSocket;
import communication.WebPageSocketForTest;
import org.junit.Assert;
import org.junit.Test;
import rules.Messages;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class GameObserverTest {
    @Test
    public void shouldJsonMessageIsCorrectlyCreated() {
        //Given
        WebPageSocket webPageSocket = new WebPageSocketForTest();
        GameObserver gameObserver = new GameObserver(webPageSocket);
        String nick1 = UUID.randomUUID().toString();
        String nick2 = UUID.randomUUID().toString();
        int points1 = new Random().nextInt();
        int points2 = new Random().nextInt();

        CustomJSONObject customJSONObject1= new CustomJSONObject();
        customJSONObject1.put(Messages.PLAYER, nick1);
        customJSONObject1.put(Messages.POINTS, String.valueOf(points1));

        CustomJSONObject customJSONObject2= new CustomJSONObject();
        customJSONObject2.put(Messages.PLAYER, nick2);
        customJSONObject2.put(Messages.POINTS, String.valueOf(points2));

        List<CustomJSONObject> players = new LinkedList<>();
        players.add(customJSONObject1);
        players.add(customJSONObject2);

        //When
        gameObserver.createInfoForWeb(players);

        //Then
        CustomJSONObject json = ((WebPageSocketForTest) webPageSocket).getJson();
        Assert.assertTrue(json.getString(Messages.PLAYERS).replace(",{", ", {").equals(players.toString()) &&
                json.getString(Messages.OBJECT_TYPE).equals(Messages.FINAL_RESULTS));
    }
}
