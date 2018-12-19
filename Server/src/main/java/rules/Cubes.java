package rules;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Cubes {
    private final List<Cube> cubes;
    private final List<Cube> noUsedCubes;

    public Cubes(List<Cube> cubes) {
        this.cubes = cubes;
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
}
