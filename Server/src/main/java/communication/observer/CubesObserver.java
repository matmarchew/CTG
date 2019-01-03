package communication.observer;

import communication.CustomJSONObject;
import rules.Messages;

public class CubesObserver extends Observer {
    public void refreshBoard() {
        CustomJSONObject json = new CustomJSONObject();
        json.put(Messages.OBJECT_TYPE, Messages.REFRESH);
        sendMessageToWeb(json.toString());
    }
}
