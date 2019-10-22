package rules;

import communication.observer.CubeObserver;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class CubeTest {
    @Test
    public void shouldReturnValueOneTwoOrThree() {
        //Given
        Cube cube = new Cube(UUID.randomUUID().toString(), mock(CubeObserver.class));

        for (int i = 0; i < 10; i++) {
            //When
            int result = cube.roll();

            //Then
            assertTrue(result > 0 && result <= 3);
        }

    }

    @Test
    public void shouldReturnColor() {
        //Given
        String color = UUID.randomUUID().toString();
        Cube cube = new Cube(color, mock(CubeObserver.class));

        //When
        String result = cube.getColor();

        //Then
        assertTrue(color.equals(result));
    }
}