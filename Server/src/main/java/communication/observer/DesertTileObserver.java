package communication.observer;

import communication.CustomJSONObject;
import communication.WebPageSocket;
import rules.Messages;
import rules.board.tiles.desert.DesertTilePage;

public class DesertTileObserver extends Observer {
    public DesertTileObserver(WebPageSocket webPageSocket) {
        super(webPageSocket);
    }

    public void createInfoForWeb(int number, String desertTileAction, DesertTilePage page) {
        CustomJSONObject json = new CustomJSONObject();
        json.put(Messages.OBJECT_TYPE, Messages.DESERT_TILE);
        json.put(Messages.FIELD_NUMBER, String.valueOf(number));
        json.put(Messages.DESERT_TILE, desertTileAction);
        json.put(Messages.PAGE, page);
        sendMessageToWeb(json.toString());
    }
}
