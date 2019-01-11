package rules;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Cubes {
    private final List<Cube> cubes;
    private final List<Cube> cubesInGame;

    public Cubes(List<Cube> cubes) {
        this.cubes = cubes;
        this.cubesInGame = new LinkedList<>();
    }

    public boolean isCubeExists() {
        return cubesInGame.size() > 0;
    }

    public void prepareCubes() {
        cubesInGame.addAll(cubes);
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cubesInGame);
    }

    public Cube getNextCube() {
        return cubesInGame.remove(0);
    }
}
