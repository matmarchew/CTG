package rules.players;

import communication.CustomJSONObject;
import communication.observer.PlayerObserver;
import rules.Cubes;
import rules.Messages;
import rules.action.PlayerAction;
import rules.action.PlayerActionFactory;
import rules.board.BettingCard;
import rules.board.BettingCards;
import rules.board.Board;
import rules.board.Pawn;
import rules.board.tiles.bet.BettingTile;
import rules.board.tiles.desert.DesertTile;

import java.util.LinkedList;
import java.util.List;

public class Player implements Comparable<Player> {
    private final PlayerSocket socket;
    private final String login;
    private final BettingCards bettingCards;
    private final List<BettingTile> bettingTiles;
    private DesertTile desertTile;
    private int points;
    private final PlayerObserver playerObserver;
    private final PlayerActionFactory playerActionFactory;

    public Player(PlayerSocket socket, String login, BettingCards bettingCards,
                  DesertTile desertTile, PlayerObserver playerObserver, PlayerActionFactory playerActionFactory) {
        this.socket = socket;
        this.login = login;
        this.playerObserver = playerObserver;
        this.playerActionFactory = playerActionFactory;
        this.points = 0;
        bettingTiles = new LinkedList<>();
        this.desertTile = desertTile;
        this.bettingCards = bettingCards;
    }

    public void addBettingTileToPlayer(BettingTile bettingTile) {
        bettingTiles.add(bettingTile);
    }

    public void addPoints(int points) {
        this.points += points;
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

    public BettingCard getBettingCardInColor(String color) {
        return bettingCards.getBettingCardInColor(color);
    }

    public PlayerAction getPlayerAction(Board board, Cubes cubes) {
        return playerActionFactory.getPlayerActionFromJson(socket.receiveMessageFromAndroid(), this, board, cubes);
    }

    public DesertTile getPlayerDesertTile() {
        DesertTile tmpDesertTile = desertTile;
        desertTile = null;
        return tmpDesertTile;
    }

    public List<CustomJSONObject> getUsedBetCards() {
        List<CustomJSONObject> jsonList = new LinkedList<>();
        bettingCards.getUsedBettingCards().forEach(card -> {
            CustomJSONObject json = new CustomJSONObject();
            json.put(Messages.COLOR, card.toString());
            jsonList.add(json);
        });
        return jsonList;
    }

    public Boolean isHaveDesertTile() {
        return desertTile != null;
    }

    public void notifyPlayerObserver() {
        playerObserver.createInfoForWeb(login);
    }

    public void sendMessageToMobile(String start) {
        socket.sendMessageToAndroid(start);
    }

    public String getLogin() {
        return login;
    }

    public int getPoints() {
        return points;
    }

    public void setDesertTile(DesertTile desertTile) {
        this.desertTile = desertTile;
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
}
