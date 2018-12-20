package rules.board.tiles.bet;

import communication.CustomJSONObject;
import rules.Messages;

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

    public List<CustomJSONObject> getTopBettingTileFromEveryStack() {
        List<CustomJSONObject> bettingTiles = new LinkedList<>();
        stacksOfBettingTile.forEach(stackOfBettingTile -> {
            BettingTile bettingTile = stackOfBettingTile.getTopBettingTile();
            CustomJSONObject json = new  CustomJSONObject();
            json.put(Messages.COLOR, bettingTile.getColor());
            json.put(Messages.VALUE, bettingTile.getValue() + "");
            bettingTiles.add(json);
        });
        return bettingTiles;
    }
}
