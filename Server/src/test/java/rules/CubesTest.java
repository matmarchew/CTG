package rules;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.UUID;

public class CubesTest {
    @Test
    public void shouldCheckIfCubesAreNotAvailable() {
        //Given
        Cube cube = new Cube(UUID.randomUUID().toString());
        Cubes cubes = new Cubes(Arrays.asList(cube));

        //When - THEN
        Assert.assertFalse(cubes.isCubesExist());
    }

    @Test
    public void shouldCubesAreAvailable() {
        //Given
        Cube cube = new Cube(UUID.randomUUID().toString());
        Cubes cubes = new Cubes(Arrays.asList(cube));

        //When
        cubes.prepareCubes();

        //Then
        Assert.assertTrue(cubes.isCubesExist());
    }

    @Test
    public void shouldReturnNextCubeAndCubeIsNotAvailableNow() {
        //Given
        Cube cube = new Cube(UUID.randomUUID().toString());
        Cubes cubes = new Cubes(Arrays.asList(cube));
        cubes.prepareCubes();

        //When
        Cube result = cubes.getNextCube();

        //Then
        Assert.assertTrue(cube.equals(result) && !cubes.isCubesExist());
    }

}