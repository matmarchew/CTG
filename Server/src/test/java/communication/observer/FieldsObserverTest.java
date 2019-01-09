package communication.observer;

import communication.CustomJSONObject;
import communication.WebPageSocket;
import communication.WebPageSocketForTest;
import org.junit.Assert;
import org.junit.Test;
import rules.Messages;
import rules.board.Pawn;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class FieldsObserverTest {
    @Test
    public void shouldJsonMessageIsCorrectlyCreated() {
        //Given
        WebPageSocket webPageSocket = new WebPageSocketForTest();
        FieldsObserver fieldsObserver = new FieldsObserver(webPageSocket);
        int number = new Random().nextInt();
        String color1 = UUID.randomUUID().toString();
        String color2 = UUID.randomUUID().toString();
        List<Pawn> pawns = new LinkedList<>();
        pawns.add(new Pawn(color1));
        pawns.add(new Pawn(color2));

        //When
        fieldsObserver.createInfoForWeb(pawns, number);

        //Then
        CustomJSONObject json = ((WebPageSocketForTest) webPageSocket).getJson();
        Assert.assertTrue(json.getString(Messages.PAWNS).equals("[\"" + color1 + "\",\"" + color2 + "\"]") &&
                json.getString(Messages.FIELD_NUMBER).equals(String.valueOf(number)) &&
                json.getString(Messages.OBJECT_TYPE).equals(Messages.PAWNS));
    }
}
