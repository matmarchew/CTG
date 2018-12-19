package rules;

import java.util.Random;

public class Cube {
    private final String color;

    public Cube(String color) {
        this.color = color;
    }

    public int roll() {
        return new Random().nextInt(3) + 1;
    }

    public String getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o != null) return o.equals(color);
        else return false;
    }
}
