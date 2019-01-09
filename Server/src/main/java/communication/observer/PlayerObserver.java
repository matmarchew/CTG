package communication.observer;

import communication.CustomJSONObject;
import rules.Messages;

public class PlayerObserver extends Observer {
    public void notifyWebAboutActualPlayer(String login) {
        CustomJSONObject json = new CustomJSONObject();
        json.put(Messages.OBJECT_TYPE, Messages.PLAYER);
        json.put(Messages.LOGIN, login);
        sendMessageToWeb(json.toString());
    }
}
