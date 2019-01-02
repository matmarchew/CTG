package rules.action;

import org.junit.Assert;
import org.junit.Test;

public class EmptyActionTest {
    @Test
    public void shouldDoNothing() {
        //Given
        EmptyAction emptyAction = new EmptyAction();

        //When
        emptyAction.performAction();

        //Then
        Assert.assertTrue(true);
    }

}
