package rules.board.tiles.bet;

import java.util.LinkedList;
import java.util.List;

public class BettingTiles {
    private final List<StackOfBettingTile> stacksOfBettingTile;

    public BettingTiles(List<StackOfBettingTile> stacksOfBettingTile) {
        this.stacksOfBettingTile = new LinkedList<>(stacksOfBettingTile);
    }

    public BettingTile getTopBettingTileFromStackInColor(String color) {
        return getBettingStackInColor(color).getTopBettingTileAndMarkedItAsUsed();
    }

    public void prepareStacksOfBettingTile() {
        stacksOfBettingTile.forEach(stackOfBettingTile -> stackOfBettingTile.prepareBettingTiles());
    }

    private StackOfBettingTile getBettingStackInColor(String color) {
        return stacksOfBettingTile.stream().filter(stackOfBettingTile -> stackOfBettingTile.equals(color)).findFirst().get();
    }

    public List<BettingTile> getTopBettingTileFromEveryStack() {
        List<BettingTile> bettingTiles = new LinkedList<>();
        stacksOfBettingTile.forEach(stackOfBettingTile -> bettingTiles.add(stackOfBettingTile.getTopBettingTile()));
        return bettingTiles;
    }
}
