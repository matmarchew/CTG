package rules.action;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmptyActionTest {
    @Test
    public void shouldDoNothing() {
        //Given
        EmptyAction emptyAction = new EmptyAction();

        //When
        emptyAction.performAction();

        //Then
        assertTrue(true);
    }

}
