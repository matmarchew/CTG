package rules.board;

import rules.Cube;
import rules.board.tiles.desert.DesertTile;

import java.util.LinkedList;
import java.util.List;

public class Field {
    private final List<Pawn> pawns; // first in list is at the top
    private DesertTile desertTile;

    public Field() {
        this.pawns = new LinkedList<>();
        this.desertTile = null;
    }

    public void addPawns(List<Pawn> pawns, int additionalNumbersOfMoves) {
        if (additionalNumbersOfMoves == -1) {
            addPawnsToDown(pawns);
        } else {
            addPawnsToTop(pawns);
        }
    }

    public List<Pawn> getFirstNPawnsFromStack(int n) {
        List<Pawn> nPawns = new LinkedList<>(pawns.subList(0, n));
        pawns.removeAll(nPawns);
        return nPawns;
    }

    public boolean isPawnInThisField(Cube cube) {
        return pawns.stream().anyMatch(pawn -> pawn.equals(cube));
    }

    public int getPawnPositionInStack(Cube cube) {
        return pawns.indexOf(cube);
    }

    public int getBonusFromDesertTile() {
        return desertTile != null ? desertTile.getBonusPoints() : 0;
    }

    public int getNumberOfPawns() {
        return pawns.size();
    }

    public void putDesertTile(DesertTile desertTile) {
        this.desertTile = desertTile;
    }

    public DesertTile getDesertTileAndClearIt() {
        DesertTile desertTile = this.desertTile;
        this.desertTile = null;
        return desertTile;
    }

    public List<Pawn> getPawns() {
        return new LinkedList<>(pawns);
    }

    private void addPawnsToTop(List<Pawn> pawns) {
        this.pawns.addAll(0, pawns);
    }

    private void addPawnsToDown(List<Pawn> pawns) {
        this.pawns.addAll(pawns);
    }

    public boolean containsDesertTile() {
        return desertTile != null;
    }
}
