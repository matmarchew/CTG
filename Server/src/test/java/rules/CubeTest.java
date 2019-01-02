package rules;

import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class CubeTest {
    @Test
    public void shouldReturnValueOneTwoOrThree() {
        //Given
        Cube cube = new Cube(UUID.randomUUID().toString());

        for (int i = 0; i < 10; i++) {
            //When
            int result = cube.roll();

            //Then
            Assert.assertTrue(result > 0 && result <= 3);
        }

    }

    @Test
    public void shouldReturnColor() {
        //Given
        String color = UUID.randomUUID().toString();
        Cube cube = new Cube(color);

        //When
        String result = cube.getColor();

        //Then
        Assert.assertTrue(color.equals(result));
    }
}