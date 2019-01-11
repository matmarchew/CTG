package rules.players;

import rules.board.BettingFinalResult;
import rules.board.Pawn;
import rules.board.tiles.desert.DesertTile;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Players {
    private final List<Player> players;
    private final int numberOfPlayers;
    private int actualPlayerIndex;

    public Players(List<Player> players) {
        this.players = players;
        numberOfPlayers = players.size();
        actualPlayerIndex = -1;
    }

    public void calculatePointsAfterGameForStack(Pawn pawn, BettingFinalResult bettingFinalResult) {
        List<String> winnerPlayersLogin = bettingFinalResult.getPlayersLoginFromBettingCardsInOrder(pawn,true);
        for (int i = 0; i < winnerPlayersLogin.size(); i++) {
            searchPlayerByLogin(winnerPlayersLogin.get(i)).addPoints(pointsForPlace(i));
        }

        List<String> loserPlayersLogin = bettingFinalResult.getPlayersLoginFromBettingCardsInOrder(pawn,false);
        loserPlayersLogin.forEach(player -> searchPlayerByLogin(player).addPoints(-1));
    }

    public void calculatePointsForEveryPlayerAfterRound(Pawn winner, Pawn runnerUp) {
        players.stream().forEach(player -> player.calculatePointsAfterRound(winner, runnerUp));
    }

    public Player getNextPlayer() {
        actualPlayerIndex = nextPlayerIndex();
        return players.get(actualPlayerIndex);
    }

    public void setDesertTileInPlayer(DesertTile desertTile) {
        String playerLogin = desertTile.getPlayerLogin();
        Player player = searchPlayerByLogin(playerLogin);
        player.setDesertTile(desertTile);
        player.addPoints(1);
    }

    public List<Player> sortPlayersInDescendingOrder() {
        List<Player> copyPlayers = new LinkedList<>(players);
        Collections.sort(copyPlayers);
        return copyPlayers;
    }

    private int nextPlayerIndex() {
        return (actualPlayerIndex + 1) % numberOfPlayers;
    }

    private int pointsForPlace(int position) {
        switch (position) {
            case 0 : return 8;
            case 1 : return 5;
            case 2 : return 3;
            default : return 2;
        }
    }

    private Player searchPlayerByLogin(String playerLogin) {
        for (int i = 0; i < players.size(); i++) {
            if(players.get(i).equals(playerLogin))
                return players.get(i);
        }
        return null;
    }
}
