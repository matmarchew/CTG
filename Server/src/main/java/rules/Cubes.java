package rules;

import communication.observer.CubesObserver;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Cubes {
    private final List<Cube> cubes;
    private final List<Cube> cubesInGame;
    private final CubesObserver cubesObserver;

    public Cubes(List<Cube> cubes, CubesObserver cubesObserver) {
        this.cubes = cubes;
        this.cubesObserver = cubesObserver;
        this.cubesInGame = new LinkedList<>();
    }

    public boolean isCubeExists() {
        return cubesInGame.size() > 0;
    }

    public void notifyCubesObserver() {
        cubesObserver.createInfoForWeb();
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
