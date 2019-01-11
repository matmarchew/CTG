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
        return activeDesertTilePage.getBonusPoints();
    }

    public void notifyDesertTileObserver(int fieldNumber, String message, DesertTilePage desertTilePage) {
        desertTileObserver.createInfoForWeb(fieldNumber, message, desertTilePage);
    }

    public void switchPageToActive(DesertTilePage desertTilePage) {
        setActiveDesertTilePage(desertTilePage);
    }

    public void switchPageToNonActive() {
        setActiveDesertTilePage(new UnclassifiedDesertTilePage());
    }

    public DesertTilePage getDesertTilePage() {
        return activeDesertTilePage;
    }

    public String getPlayerLogin() {
        return playerLogin;
    }

    private void setActiveDesertTilePage(DesertTilePage desertTilePage) {
        activeDesertTilePage = desertTilePage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o != null) return o.equals(playerLogin);
        return false;
    }
}
