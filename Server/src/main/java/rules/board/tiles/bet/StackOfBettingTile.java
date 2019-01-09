package rules.board.tiles.bet;

import java.util.LinkedList;
import java.util.List;

public class StackOfBettingTile {
    private final List<BettingTile> bettingTiles; //max 3 elements, the top is the most value
    private final List<BettingTile> unusedBettingTiles;
    private final String bettingTileColorInStack;

    public StackOfBettingTile(List<BettingTile> bettingTiles) {
        this.bettingTiles = new LinkedList<>(bettingTiles);
        this.unusedBettingTiles = new LinkedList<>();
        bettingTileColorInStack = bettingTiles.get(0).getColor();
    }

    public BettingTile getTopBettingTile() {
        if (unusedBettingTiles.size() > 0)
            return unusedBettingTiles.get(0);
        else
            return new BettingTile(bettingTileColorInStack, 0, null);
    }

    public BettingTile getTopBettingTileAndMarkedItAsUsed() {
        if (unusedBettingTiles.size() > 0)
            return unusedBettingTiles.remove(0);
        else
            return new BettingTile(bettingTileColorInStack, 0, null);
    }

    public void prepareBettingTiles() {
        unusedBettingTiles.clear();
        unusedBettingTiles.addAll(bettingTiles);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o != null) return o.equals(bettingTileColorInStack);
        return false;
    }
}
