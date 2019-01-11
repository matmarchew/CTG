package rules.board;

import rules.board.tiles.desert.DesertTile;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Field {
    private final List<Pawn> pawns; // first in list is at the top
    private DesertTile desertTile;

    public Field() {
        this.pawns = new LinkedList<>();
        this.desertTile = null;
    }

    public void addPawnsFromTheBottom(List<Pawn> pawns) {
        this.pawns.addAll(pawns);
    }

    public void addPawnsFromTheTop(List<Pawn> pawns) {
        this.pawns.addAll(0, pawns);
    }

    public boolean containsDesertTile() {
        return desertTile != null;
    }

    public DesertTile getDesertTileAndClearIt() {
        DesertTile desertTile = this.desertTile;
        this.desertTile = null;
        return desertTile;
    }

    public List<Pawn> getPawnsAbovePawnInColor(String pawnColor) {
        int pawnPositionInStack = getPawnPositionInStack(pawnColor);
        List<Pawn> nPawns = new LinkedList<>(pawns.subList(0, pawnPositionInStack + 1));
        pawns.removeAll(nPawns);
        return nPawns;
    }

    public int getNumberOfPawns() {
        return pawns.size();
    }

    public boolean isPawnInThisField(String pawnColor) {
        return pawns.stream().anyMatch(pawn -> pawn.equals(pawnColor));
    }

    public List<Pawn> getPawns() {
        return new LinkedList<>(pawns);
    }

    public void putDesertTile(DesertTile desertTile) {
        this.desertTile = desertTile;
    }

    private int getPawnPositionInStack(String pawnColor) {
        return IntStream.range(0, getNumberOfPawns()).filter(i -> pawns.get(i).equals(pawnColor)).findFirst().getAsInt();
    }
}
