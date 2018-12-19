package rules.action;

import rules.Cube;
import rules.Cubes;
import rules.board.Board;
import rules.players.Player;

public class ThrowCubeAction implements PlayerAction {
    private final Board board;
    private final Cubes cubes;
    private final Player player;

    public ThrowCubeAction(Board board, Cubes cubes, Player player) {
        this.board = board;
        this.cubes = cubes;
        this.player = player;
    }

    @Override
    public void performAction() {
        Cube cube = cubes.getNextCube();
        int numberOfFieldsToPawnsBeMoved = cube.roll();
        player.addPoints(1);
        board.movePawns(numberOfFieldsToPawnsBeMoved, cube);
    }
}
