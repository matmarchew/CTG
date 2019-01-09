package communication.observer;

import communication.CustomJSONObject;
import communication.WebPageSocket;
import rules.Messages;

public class BettingTileObserver extends Observer {
    public BettingTileObserver(WebPageSocket webPageSocket) {
        super(webPageSocket);
    }

    public void createInfoForWeb(int value, String color) {
        CustomJSONObject json = new CustomJSONObject();
        json.put(Messages.COLOR, color);
        json.put(Messages.VALUE, String.valueOf(value));
        json.put(Messages.OBJECT_TYPE, Messages.BET_TILE);
        sendMessageToWeb(json.toString());
    }
}
