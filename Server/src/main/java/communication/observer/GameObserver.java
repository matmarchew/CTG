package communication.observer;

import communication.CustomJSONObject;
import rules.Messages;

import java.util.List;

public class GameObserver extends Observer {
    public void notifyWebAboutFinalResults(List<CustomJSONObject> playerList) {
        CustomJSONObject json = new CustomJSONObject();
        json.put(Messages.PLAYERS, playerList);
        json.put(Messages.OBJECT_TYPE, Messages.FINAL_RESULTS);
        sendMessageToWeb(json.toString());
    }
}
