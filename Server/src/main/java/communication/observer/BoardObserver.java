package communication.observer;

import communication.CustomJSONObject;
import communication.WebPageSocket;
import rules.Messages;

public class BoardObserver extends Observer {
    public BoardObserver(WebPageSocket webPageSocket) {
        super(webPageSocket);
    }

    public void createInfoForWeb() {
        CustomJSONObject json = new CustomJSONObject();
        json.put(Messages.OBJECT_TYPE, Messages.REFRESH);
        sendMessageToWeb(json.toString());
    }
}
