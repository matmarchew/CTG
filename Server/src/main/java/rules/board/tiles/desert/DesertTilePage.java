package rules.board.tiles.desert;

import rules.board.Field;
import rules.board.Pawn;

import java.util.List;

public interface DesertTilePage {
    int getBonusPoints();

    void addPawnsToField(Field field, List<Pawn> pawns);
}
