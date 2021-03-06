package rules;

import communication.CustomJSONObject;
import communication.observer.GameObserver;
import rules.action.PlayerAction;
import rules.board.Board;
import rules.board.Pawn;
import rules.board.tiles.desert.DesertTile;
import rules.players.Player;
import rules.players.Players;

import java.util.LinkedList;
import java.util.List;

public class Game {
    private final Board board;
    private final Players players; //players are in order
    private final Cubes cubes;
    private final GameObserver gameObserver;

    public Game(Board board, Players players, Cubes cubes, GameObserver gameObserver) {
        this.board = board;
        this.players = players;
        this.cubes = cubes;
        this.gameObserver = gameObserver;
    }

    public void startGame() {
        prepareBoardBeforeGame();
        do {
            prepareBoardBeforeRound();
            playRound();
            cubes.notifyCubesObserver();
            calculatePointsAfterRound();
        } while (!isFinishedGame());
        calculatePointsAfterGame();
    }

    private void prepareBoardBeforeGame() {
        cubes.prepareCubes();
        startPlacementPawnsOnTheBoard();
    }

    private void prepareBoardBeforeRound() {
        cubes.prepareCubes();
        board.prepareBoard();
    }

    private void playRound() {
        while (cubes.isCubesExist() && !isFinishedGame()) {
            Player actualPlayer = players.getNextPlayer();
            actualPlayer.notifyPlayerObserver();

            sendActualStateToPlayer(actualPlayer);

            PlayerAction action = actualPlayer.getPlayerAction(board, cubes);
            action.performAction();
            backDesertTileToPlayer(board.getReturnedDesertTile());
        }
    }

    private void sendActualStateToPlayer(Player actualPlayer) {
        CustomJSONObject json = new CustomJSONObject();
        json.put(Messages.BET_CARD, actualPlayer.getUsedBetCards());
        json.put(Messages.BET_TILES, board.getStacksOfBetTile().getTopBettingTileFromEveryStack());
        json.put(Messages.FIELD_NUMBER, board.getFields().getFieldsWhereNoPutDesertTile());
        json.put(Messages.DESERT_TILE, actualPlayer.isHaveDesertTile().toString());
        json.put(Messages.STATE, Messages.START);
        actualPlayer.sendMessageToMobile(json.toString());
    }

    private void calculatePointsAfterRound() {
        List<Pawn> pawnFirstPlaces = board.getPawnsInOrder();
        Pawn winner = pawnFirstPlaces.get(0);
        Pawn runnerUp = pawnFirstPlaces.get(1);
        players.calculatePointsForEveryPlayerAfterRound(winner, runnerUp);
    }

    private void calculatePointsAfterGame() {
        List<Pawn> pawnFirstPlaces = board.getPawnsInOrder();
        Pawn winner = pawnFirstPlaces.get(0);
        Pawn loser = pawnFirstPlaces.get(pawnFirstPlaces.size() - 1);
        board.calculatePointsAfterGame(winner, loser, players);
    }

    private void startPlacementPawnsOnTheBoard() {
        while (cubes.isCubesExist()) {
            Cube cube = cubes.getNextCube();
            board.movePawns(cube.roll(), cube);
        }
    }

    private void backDesertTileToPlayer(DesertTile desertTile) {
        if (desertTile != null)
            players.setDesertTileInPlayer(desertTile);
    }

    private boolean isFinishedGame() {
        return board.isGameFinished();
    }

    public List<Player> gameResult() {
        return players.sortPlayersInDescendingOrder();
    }

    public void notifyGameObserver() {
        List<CustomJSONObject> playerList = new LinkedList<>();
        gameResult().forEach(player -> {
            CustomJSONObject json = new CustomJSONObject();
            json.put(Messages.LOGIN, player.getLogin());
            json.put(Messages.POINTS, player.getPoints() + "");
            playerList.add(json);
        });
        gameObserver.notifyWebAboutFinalResults(playerList);
    }
}
