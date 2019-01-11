package rules;

import communication.observer.CubeObserver;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.UUID;

import static org.mockito.Mockito.mock;

public class CubesTest {
    @Test
    public void shouldCheckIfCubesAreNotAvailable() {
        //Given
        Cube cube = new Cube(UUID.randomUUID().toString(), mock(CubeObserver.class));
        Cubes cubes = new Cubes(Arrays.asList(cube));

        //When - THEN
        Assert.assertFalse(cubes.isCubeExists());
    }

    @Test
    public void shouldCubesAreAvailable() {
        //Given
        Cube cube = new Cube(UUID.randomUUID().toString(), mock(CubeObserver.class));
        Cubes cubes = new Cubes(Arrays.asList(cube));

        //When
        cubes.prepareCubes();

        //Then
        Assert.assertTrue(cubes.isCubeExists());
    }

    @Test
    public void shouldReturnNextCubeAndCubeIsNotAvailableNow() {
        //Given
        Cube cube = new Cube(UUID.randomUUID().toString(), mock(CubeObserver.class));
        Cubes cubes = new Cubes(Arrays.asList(cube));
        cubes.prepareCubes();

        //When
        Cube result = cubes.getNextCube();

        //Then
        Assert.assertTrue(cube.equals(result) && !cubes.isCubeExists());
    }

}