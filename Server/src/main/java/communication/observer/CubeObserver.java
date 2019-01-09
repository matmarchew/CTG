package communication.observer;

import communication.CustomJSONObject;
import communication.WebPageSocket;
import rules.Messages;

public class CubeObserver extends Observer {
    public CubeObserver(WebPageSocket webPageSocket) {
        super(webPageSocket);
    }

    public void createInfoForWeb(String color, int value) {
        CustomJSONObject json = new CustomJSONObject();
        json.put(Messages.OBJECT_TYPE, Messages.CUBE);
        json.put(Messages.COLOR, color);
        json.put(Messages.VALUE, value + "");
        sendMessageToWeb(json.toString());
    }
}
