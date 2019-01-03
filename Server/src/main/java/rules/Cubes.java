package rules;

import communication.observer.CubesObserver;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Cubes {
    private final List<Cube> cubes;
    private final List<Cube> noUsedCubes;
    private final CubesObserver cubesObserver;

    public Cubes(List<Cube> cubes, CubesObserver cubesObserver) {
        this.cubes = cubes;
        this.cubesObserver = cubesObserver;
        this.noUsedCubes = new LinkedList<>();
    }

    public void prepareCubes() {
        noUsedCubes.addAll(cubes);
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(noUsedCubes);
    }

    public Cube getNextCube() {
        return noUsedCubes.remove(0);
    }

    public boolean isCubesExist() {
        return noUsedCubes.size() > 0;
    }

    public void notifyCubesObserver() {
        cubesObserver.refreshBoard();
    }
}
