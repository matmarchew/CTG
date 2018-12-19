package rules.players;

import rules.Cubes;
import communication.CustomJSONObject;
import rules.Messages;
import rules.action.PlayerAction;
import rules.action.PlayerActionFactory;
import rules.board.BettingCard;
import rules.board.BettingCards;
import rules.board.Board;
import rules.board.Pawn;
import rules.board.tiles.bet.BettingTile;
import rules.board.tiles.desert.DesertTile;
import communication.information.InformationActionFactory;

import java.util.LinkedList;
import java.util.List;

public class Player implements Comparable<Player> {
    private final PlayerSocket socket;
    private final String login;
    private final BettingCards bettingCards;
    private final List<BettingTile> bettingTiles;
    private DesertTile desertTile;
    private int points;

    public Player(PlayerSocket socket, String login, BettingCards bettingCards, DesertTile desertTile) {
        this.socket = socket;
        this.login = login;
        this.points = 0;
        bettingTiles = new LinkedList<>();
        this.desertTile = desertTile;
        this.bettingCards = bettingCards;
    }

    public void calculatePointsAfterRound(Pawn winner, Pawn runnerUp) {
        bettingTiles.forEach(tile -> {
            if (winner.equals(tile)) {
                addPoints(tile.getValue());
            } else if (runnerUp.equals(tile)) {
                addPoints(1);
            } else addPoints(-1);
        });
        bettingTiles.clear();
    }

    public void sendMessageToMobile(String start) {
        socket.sendMessageToAndroid(start);
    }

    public void addBettingTileToPlayer(BettingTile bettingTile) {
        bettingTiles.add(bettingTile);
    }

    public BettingCard getBettingCardInColor(String color) {
        return bettingCards.getBettingCardInColor(color);
    }

    public DesertTile getPlayerDesertTile() {
        DesertTile tmpDesertTile = desertTile;
        desertTile = null;
        return tmpDesertTile;
    }

    public void setDesertTile(DesertTile desertTile) {
        this.desertTile = desertTile;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public int getPoints() {
        return points;
    }

    public PlayerAction getPlayerAction(Board board, Cubes cubes) {
        while(true) {
            String message = socket.receiveMessageFromAndroid();
            CustomJSONObject json = new CustomJSONObject();
            json.getJSONObjectFromString(message);
            if(json.getString(Messages.STATE).equals(Messages.START))
                return PlayerActionFactory.getPlayerActionFromJson(json, this, board, cubes);
            InformationActionFactory.getInformationAction(json, this, board).sendInformation();
        }
    }

    @Override
    public int compareTo(Player player) {
        return player.points - points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o != null) return o.equals(login);
        else return false;
    }

    public List<BettingCard> getUsedBetCards() {
        return bettingCards.getBettingCards();
    }

    public Boolean isHaveDesertTile() {
        return desertTile != null;
    }
}
