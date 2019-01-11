package rules.board.tiles.desert;

import rules.board.Field;
import rules.board.Pawn;

import java.util.List;

import static rules.Messages.UNCLASSIFIED_PAGE;

public class UnclassifiedDesertTilePage implements DesertTilePage {
    @Override
    public int getBonusPoints() {
        return 0;
    }

    @Override
    public void addPawnsToField(Field field, List<Pawn> pawns) {
        field.addPawnsFromTheTop(pawns);
    }

    @Override
    public String toString() {
        return UNCLASSIFIED_PAGE;
    }
}
