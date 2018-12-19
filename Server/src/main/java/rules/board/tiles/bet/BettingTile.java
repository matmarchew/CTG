package rules.board.tiles.bet;

import communication.observer.BettingTileObserver;

public class BettingTile {
    private final String color;
    private final int value;
    private final BettingTileObserver bettingTileObserver;

    public BettingTile(String color, int value, BettingTileObserver bettingTileObserver) {
        this.color = color;
        this.value = value;
        this.bettingTileObserver = bettingTileObserver;
    }

    public int getValue() {
        return value;
    }

    public String getColor() {
        return color;
    }

    public void notifyBetTileObserver() {
        bettingTileObserver.createInfoForWeb(value, color);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o != null) return o.equals(color);
        return false;
    }
}
