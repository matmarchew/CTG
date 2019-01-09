package rules;

import communication.observer.CubeObserver;

import java.util.Random;

public class Cube {
    private final String color;
    private final CubeObserver cubeObserver;

    public Cube(String color, CubeObserver cubeObserver) {
        this.color = color;
        this.cubeObserver = cubeObserver;
    }

    public int roll() {
        return new Random().nextInt(3) + 1;
    }

    public String getColor() {
        return color;
    }

    public void notifyCubeObserver(int value) {
        cubeObserver.createInfoForWeb(color, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o != null) return o.equals(color);
        else return false;
    }
}
