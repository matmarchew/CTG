package communication.observer;

import communication.CustomJSONObject;
import communication.WebPageSocket;
import communication.WebPageSocketForTest;
import org.junit.Assert;
import org.junit.Test;
import rules.Messages;
import rules.board.tiles.desert.MirageDesertTilePage;
import rules.board.tiles.desert.OasisDesertTilePage;
import rules.board.tiles.desert.UnclassifiedDesertTilePage;

import java.util.Random;
import java.util.UUID;

public class DesertTileObserverTest {
    @Test
    public void shouldJsonMessageIsCorrectlyCreatedForUnclassifiedDesertTilePage() {
        //Given
        WebPageSocket webPageSocket = new WebPageSocketForTest();
        DesertTileObserver desertTileObserver = new DesertTileObserver(webPageSocket);
        int number = new Random().nextInt();
        String desertTileAction = UUID.randomUUID().toString();

        //When
        desertTileObserver.createInfoForWeb(number, desertTileAction, new UnclassifiedDesertTilePage());

        //Then
        CustomJSONObject json = ((WebPageSocketForTest) webPageSocket).getJson();
        Assert.assertTrue(json.getString(Messages.DESERT_TILE).equals(desertTileAction) &&
                json.getString(Messages.FIELD_NUMBER).equals(String.valueOf(number)) &&
                json.getString(Messages.PAGE).equals(Messages.UNCLASSIFIED_PAGE) &&
                json.getString(Messages.OBJECT_TYPE).equals(Messages.DESERT_TILE));
    }

    @Test
    public void shouldJsonMessageIsCorrectlyCreatedForOasisDesertTilePage() {
        //Given
        WebPageSocket webPageSocket = new WebPageSocketForTest();
        DesertTileObserver desertTileObserver = new DesertTileObserver(webPageSocket);
        int number = new Random().nextInt();
        String desertTileAction = UUID.randomUUID().toString();

        //When
        desertTileObserver.createInfoForWeb(number, desertTileAction, new OasisDesertTilePage());

        //Then
        CustomJSONObject json = ((WebPageSocketForTest) webPageSocket).getJson();
        Assert.assertTrue(json.getString(Messages.DESERT_TILE).equals(desertTileAction) &&
                json.getString(Messages.FIELD_NUMBER).equals(String.valueOf(number)) &&
                json.getString(Messages.PAGE).equals(Messages.OASIS_PAGE) &&
                json.getString(Messages.OBJECT_TYPE).equals(Messages.DESERT_TILE));
    }

    @Test
    public void shouldJsonMessageIsCorrectlyCreatedForMirageDesertTilePage() {
        //Given
        WebPageSocket webPageSocket = new WebPageSocketForTest();
        DesertTileObserver desertTileObserver = new DesertTileObserver(webPageSocket);
        int number = new Random().nextInt();
        String desertTileAction = UUID.randomUUID().toString();

        //When
        desertTileObserver.createInfoForWeb(number, desertTileAction, new MirageDesertTilePage());

        //Then
        CustomJSONObject json = ((WebPageSocketForTest) webPageSocket).getJson();
        Assert.assertTrue(json.getString(Messages.DESERT_TILE).equals(desertTileAction) &&
                json.getString(Messages.FIELD_NUMBER).equals(String.valueOf(number)) &&
                json.getString(Messages.PAGE).equals(Messages.MIRAGE_PAGE) &&
                json.getString(Messages.OBJECT_TYPE).equals(Messages.DESERT_TILE));
    }
}
