package rules.board.tiles.desert;

import rules.Messages;
import rules.board.Field;
import rules.board.Pawn;

import java.util.List;

public class MirageDesertTilePage implements DesertTilePage {
    @Override
    public int getBonusPoints() {
        return -1;
    }

    @Override
    public void addPawnsToField(Field field, List<Pawn> pawns) {
        field.addPawnsFromTheBottom(pawns);
    }

    @Override
    public String toString() {
        return Messages.MIRAGE_PAGE;
    }
}
