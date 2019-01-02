package rules.board.tiles.desert;

import communication.observer.DesertTileObserver;

public class DesertTile {
    private final String playerLogin;
    private DesertTilePage activeDesertTilePage;
    private final DesertTileObserver desertTileObserver;

    public DesertTile(String playerLogin, DesertTileObserver desertTileObserver) {
        this.activeDesertTilePage = new UnclassifiedDesertTilePage();
        this.playerLogin = playerLogin;
        this.desertTileObserver = desertTileObserver;
    }

    public int getBonusPoints() {
        int bonusPoints = activeDesertTilePage.getBonusPoints();
        setActiveDesertTilePage(new UnclassifiedDesertTilePage());
        return bonusPoints;
    }

    public void switchPageToActive(String desertTilePage) {
        setActiveDesertTilePage(DesertTileFactory.getActivePage(desertTilePage));
    }

    public String getPlayerLogin() {
        return playerLogin;
    }

    private void setActiveDesertTilePage(DesertTilePage desertTilePage) {
        activeDesertTilePage = desertTilePage;
    }

    public void notifyDesertTileObserver(int fieldNumber, String message, String desertTilePage) {
        desertTileObserver.createInfoForWeb(fieldNumber, message, desertTilePage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o != null) return o.equals(playerLogin);
        return false;
    }

}
