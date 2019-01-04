package rules;

import communication.observer.CubesObserver;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.UUID;

import static org.mockito.Mockito.mock;

public class CubesTest {
    @Test
    public void shouldCheckIfCubesAreNotAvailable() {
        //Given
        Cube cube = new Cube(UUID.randomUUID().toString());
        Cubes cubes = new Cubes(Arrays.asList(cube), mock(CubesObserver.class));

        //When - THEN
        Assert.assertFalse(cubes.isCubesExist());
    }

    @Test
    public void shouldCubesAreAvailable() {
        //Given
        Cube cube = new Cube(UUID.randomUUID().toString());
        Cubes cubes = new Cubes(Arrays.asList(cube), mock(CubesObserver.class));

        //When
        cubes.prepareCubes();

        //Then
        Assert.assertTrue(cubes.isCubesExist());
    }

    @Test
    public void shouldReturnNextCubeAndCubeIsNotAvailableNow() {
        //Given
        Cube cube = new Cube(UUID.randomUUID().toString());
        Cubes cubes = new Cubes(Arrays.asList(cube), mock(CubesObserver.class));
        cubes.prepareCubes();

        //When
        Cube result = cubes.getNextCube();

        //Then
        Assert.assertTrue(cube.equals(result) && !cubes.isCubesExist());
    }

}