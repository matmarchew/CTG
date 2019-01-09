package communication.observer;

import communication.CustomJSONObject;
import communication.WebPageSocket;
import rules.Messages;

public class PlayerObserver extends Observer {
    public PlayerObserver(WebPageSocket webPageSocket) {
        super(webPageSocket);
    }

    public void createInfoForWeb(String login) {
        CustomJSONObject json = new CustomJSONObject();
        json.put(Messages.OBJECT_TYPE, Messages.PLAYER);
        json.put(Messages.LOGIN, login);
        sendMessageToWeb(json.toString());
    }
}
