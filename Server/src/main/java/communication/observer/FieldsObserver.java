package communication.observer;

import communication.CustomJSONObject;
import communication.WebPageSocket;
import rules.Messages;
import rules.board.Pawn;

import java.util.List;

public class FieldsObserver extends Observer {
    public FieldsObserver(WebPageSocket webPageSocket) {
        super(webPageSocket);
    }

    public void createInfoForWeb(List<Pawn> pawns, int number) {
        CustomJSONObject json = new CustomJSONObject();
        json.put(Messages.PAWNS, pawns);
        json.put(Messages.OBJECT_TYPE, Messages.PAWNS);
        json.put(Messages.FIELD_NUMBER, String.valueOf(number));
        sendMessageToWeb(json.toString());
    }
}
