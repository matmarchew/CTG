package communication.observer;

import communication.CustomJSONObject;
import rules.Messages;
import rules.board.Field;

public class FieldsObserver extends Observer {
    public void createInfoForWeb(Field field, int number) {
        CustomJSONObject json = new CustomJSONObject();
        json.put(Messages.PAWNS, field.getPawns());
        json.put(Messages.OBJECT_TYPE, Messages.PAWNS);
        json.put(Messages.FIELD_NUMBER, String.valueOf(number));
        sendMessageToWeb(json.toString());
    }
}
