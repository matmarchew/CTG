package rules;

import communication.CustomJSONObject;
import rules.action.PlayerAction;
import rules.board.Board;
import rules.board.Pawn;
import rules.board.tiles.desert.DesertTile;
import rules.players.Player;
import rules.players.Players;

import java.util.List;

public class Game {
    private final Board board;
    private final Players players; //players are in order
    private final Cubes cubes;

    public Game(Board board, Players players, Cubes cubes) {
        this.board = board;
        this.players = players;
        this.cubes = cubes;
    }

    public void startGame() {
        prepareBoardBeforeGame();
        do {
            prepareBoardBeforeRound();
            playRound();
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
            CustomJSONObject json = new CustomJSONObject();
            json.put(Messages.STATE, Messages.START);
            actualPlayer.sendMessageToMobile(json.toString());
            PlayerAction action = actualPlayer.getPlayerAction(board, cubes);
            action.performAction();
            backDesertTileToPlayer(board.getReturnedDesertTile());
        }
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
}
