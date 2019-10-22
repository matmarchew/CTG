import communication.observer.*;

import org.junit.jupiter.api.Test;
import rules.Cube;
import rules.Cubes;
import rules.Game;
import rules.Messages;
import rules.board.*;
import rules.board.tiles.bet.BettingTile;
import rules.board.tiles.bet.BettingTiles;
import rules.board.tiles.bet.StackOfBettingTile;
import rules.board.tiles.desert.DesertTile;
import rules.players.Player;
import rules.players.PlayerSocket;
import rules.players.Players;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class GameTest {
    @Test
    public void shouldReturnValidResultsAfterGame() {
        //Given
        Board board = new Board(generateFields(), generateStacksOfBetTile());
        Players players = new Players(generatePlayers());
        Cubes cubes = spy(new Cubes(generateCubes(), mock(CubesObserver.class)));
        doNothing().when(cubes).shuffle();
        Game game = new Game(board, players, cubes, mock(GameObserver.class));

        //When
        game.startGame();
        List<Player> playersInOrder = game.gameResult();

        //Then
        assertTrue(playersInOrder.get(0).equals("2") && playersInOrder.get(0).getPoints() == 15 &&
                playersInOrder.get(1).equals("4") && playersInOrder.get(1).getPoints() == 8 &&
                playersInOrder.get(2).equals("3") && playersInOrder.get(2).getPoints() == 7 &&
                playersInOrder.get(3).equals("1") && playersInOrder.get(3).getPoints() == 6
        );
    }

    private final static String COLORS[] = {"ORANGE", "BLUE", "YELLOW", "GREEN", "WHITE"};

    private Fields generateFields() {
        List<Field> field = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            field.add(new Field());
        }
        FieldsObserver fieldsObserver = mock(FieldsObserver.class);
        doNothing().when(fieldsObserver).createInfoForWeb(any(Field.class), anyInt());
        return new Fields(field, fieldsObserver);
    }

    private BettingTiles generateStacksOfBetTile() {
        List<StackOfBettingTile> stacksOfBetTiles = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            stacksOfBetTiles.add(new StackOfBettingTile(generateBetTiles(COLORS[i])));
        }
        return new BettingTiles(stacksOfBetTiles);
    }

    private List<BettingTile> generateBetTiles(String color) {
        List<BettingTile> bettingTiles = new LinkedList<>();
        BettingTileObserver bettingTileObserver = mock(BettingTileObserver.class);
        doNothing().when(bettingTileObserver).createInfoForWeb(anyInt(), anyString());
        bettingTiles.add(new BettingTile(color, 5, bettingTileObserver));
        bettingTiles.add(new BettingTile(color, 3, bettingTileObserver));
        bettingTiles.add(new BettingTile(color, 2, bettingTileObserver));
        return bettingTiles;
    }

    private List<Player> generatePlayers() {
        List<Player> players = new LinkedList<>();
        PlayerSocket playerSocket = mock(PlayerSocket.class);
        doNothing().when(playerSocket).sendMessageToAndroid(Messages.START);
        when(playerSocket.receiveMessageFromAndroid()).thenReturn(
                "{\"" + Messages.ACTION_TYPE + "\":\"" + Messages.THROW_CUBE + "\"}",
                "{\"" + Messages.ACTION_TYPE + "\":\"" + Messages.PUT_DESERT_TILE + "\",\"" + Messages.PAGE + "\":" + "\"" + Messages.MIRAGE_PAGE + "\",\"" + Messages.FIELD_NUMBER + "\":\"" + "3\"}",
                "{\"" + Messages.ACTION_TYPE + "\":\"" + Messages.THROW_CUBE + "\"}",
                "{\"" + Messages.ACTION_TYPE + "\":\"" + Messages.PUT_DESERT_TILE + "\",\"" + Messages.PAGE + "\":" + "\"" + Messages.OASIS_PAGE + "\",\"" + Messages.FIELD_NUMBER + "\":\"" + "3\"}",
                "{\"" + Messages.ACTION_TYPE + "\":\"" + Messages.THROW_CUBE + "\"}",
                "{\"" + Messages.ACTION_TYPE + "\":\"" + Messages.GET_BET_TILE + "\",\"" + Messages.COLOR + "\":" + "\"" + COLORS[2] + "\"}",
                "{\"" + Messages.ACTION_TYPE + "\":\"" + Messages.GET_BET_TILE + "\",\"" + Messages.COLOR + "\":" + "\"" + COLORS[4] + "\"}",
                "{\"" + Messages.ACTION_TYPE + "\":\"" + Messages.PUT_BET_CARD + "\",\"" + Messages.COLOR + "\":" + "\"" + COLORS[2] + "\",\"" + Messages.STACK + "\":\"" + Messages.WINNER_STACK +"\"}",
                "{\"" + Messages.ACTION_TYPE + "\":\"" + Messages.PUT_BET_CARD + "\",\"" + Messages.COLOR + "\":" + "\"" + COLORS[2] + "\",\"" + Messages.STACK + "\":\"" + Messages.WINNER_STACK +"\"}",
                "{\"" + Messages.ACTION_TYPE + "\":\"" + Messages.PUT_BET_CARD + "\",\"" + Messages.COLOR + "\":" + "\"" + COLORS[1] + "\",\"" + Messages.STACK + "\":\"" + Messages.LOSER_STACK +"\"}",
                "{\"" + Messages.ACTION_TYPE + "\":\"" + Messages.PUT_BET_CARD + "\",\"" + Messages.COLOR + "\":" + "\"" + COLORS[1] + "\",\"" + Messages.STACK + "\":\"" + Messages.LOSER_STACK +"\"}",
                "{\"" + Messages.ACTION_TYPE + "\":\"" + Messages.PUT_BET_CARD + "\",\"" + Messages.COLOR + "\":" + "\"" + COLORS[2] + "\",\"" + Messages.STACK + "\":\"" + Messages.LOSER_STACK +"\"}",
                "{\"" + Messages.ACTION_TYPE + "\":\"" + Messages.GET_BET_TILE + "\",\"" + Messages.COLOR + "\":" + "\"" + COLORS[1] + "\"}",
                "{\"" + Messages.ACTION_TYPE + "\":\"" + Messages.THROW_CUBE + "\"}"
                );
        for (int i = 0; i < 4; i++) {
            DesertTileObserver desertTileObserver = mock(DesertTileObserver.class);
            doNothing().when(desertTileObserver).createInfoForWeb(anyInt(), anyString(), anyString());
            players.add(new Player(playerSocket, Integer.toString(i + 1), generateBetCards(Integer.toString(i + 1)), new DesertTile(Integer.toString(i + 1), desertTileObserver), mock(PlayerObserver.class)));
        }
        return players;
    }

    private BettingCards generateBetCards(String playerLogin) {
        List<BettingCard> bettingCards = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            bettingCards.add(new BettingCard(COLORS[i], playerLogin));
        }
        return new BettingCards(bettingCards);
    }

    private List<Cube> generateCubes() {
        List<Cube> cubes = new LinkedList<>();
        cubes.add(new CubeForTest(COLORS[0], 2, 3));
        cubes.add(new CubeForTest(COLORS[1], 1, 3));
        cubes.add(new CubeForTest(COLORS[2], 3, 1));
        cubes.add(new CubeForTest(COLORS[3], 2, 1));
        cubes.add(new CubeForTest(COLORS[4], 2));
        return cubes;
    }
}

class CubeForTest extends Cube {
    private int[] moves;
    private int n;

    public CubeForTest(String color, int... moves) {
        super(color, mock(CubeObserver.class));
        this.moves = moves;
        n = 0;
    }

    @Override
    public int roll() {
        return moves[n++];
    }
}