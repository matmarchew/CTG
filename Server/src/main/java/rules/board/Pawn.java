package rules.board;

public class Pawn {
    private final String color;

    public Pawn(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o != null) return o.equals(color);
        else return false;
    }
}
