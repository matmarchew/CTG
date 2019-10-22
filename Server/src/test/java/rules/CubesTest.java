package rules;

import communication.observer.CubeObserver;
import communication.observer.CubesObserver;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class CubesTest {
    @Test
    public void shouldCheckIfCubesAreNotAvailable() {
        //Given
        Cube cube = new Cube(UUID.randomUUID().toString(), mock(CubeObserver.class));
        Cubes cubes = new Cubes(Arrays.asList(cube), mock(CubesObserver.class));

        //When - THEN
        assertFalse(cubes.isCubesExist());
    }

    @Test
    public void shouldCubesAreAvailable() {
        //Given
        Cube cube = new Cube(UUID.randomUUID().toString(), mock(CubeObserver.class));
        Cubes cubes = new Cubes(Arrays.asList(cube), mock(CubesObserver.class));

        //When
        cubes.prepareCubes();

        //Then
        assertTrue(cubes.isCubesExist());
    }

    @Test
    public void shouldReturnNextCubeAndCubeIsNotAvailableNow() {
        //Given
        Cube cube = new Cube(UUID.randomUUID().toString(), mock(CubeObserver.class));
        Cubes cubes = new Cubes(Arrays.asList(cube), mock(CubesObserver.class));
        cubes.prepareCubes();

        //When
        Cube result = cubes.getNextCube();

        //Then
        assertTrue(cube.equals(result) && !cubes.isCubesExist());
    }

}