package rules.board.tiles.desert;

import rules.Messages;

public class DesertTileFactory {
    public DesertTilePage getActivePage(String desertTilePage) {
        switch (desertTilePage) {
            case Messages.OASIS_PAGE : return new OasisDesertTilePage();
            case Messages.MIRAGE_PAGE : return new MirageDesertTilePage();
            default : return new UnclassifiedDesertTilePage();
        }
    }
}
