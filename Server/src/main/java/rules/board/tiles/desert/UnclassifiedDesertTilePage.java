package rules.board.tiles.desert;

import rules.board.Field;
import rules.board.Pawn;

import java.util.List;

public class UnclassifiedDesertTilePage implements DesertTilePage {
    @Override
    public int getBonusPoints() {
        return 0;
    }

    @Override
    public void addPawnsToField(Field field, List<Pawn> pawns) {
        field.addPawnsFromTheTop(pawns);
    }
}
