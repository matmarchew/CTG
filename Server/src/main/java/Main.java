import communication.observer.CubesObserver;
import rules.*;
import rules.board.*;
import rules.board.tiles.bet.BettingTile;
import rules.board.tiles.bet.StackOfBettingTile;
import rules.board.tiles.bet.BettingTiles;
import rules.board.tiles.desert.DesertTile;
import rules.players.Player;
import rules.players.PlayerSocket;
import rules.players.Players;
import communication.CustomJSONObject;
import communication.Server;
import communication.observer.BettingTileObserver;
import communication.observer.DesertTileObserver;
import communication.observer.FieldsObserver;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(getBoard(), getConnectedPlayers(1), getCubes());
        game.startGame();
    }

    private static Players getConnectedPlayers(int numberOfPlayers) {
        Server server = new Server(4444);
        List<Player> players = new LinkedList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            PlayerSocket ps = new PlayerSocket(server.getNewClient());
            CustomJSONObject json = new CustomJSONObject();
            json.getJSONObjectFromString(ps.receiveMessageFromAndroid());
            String playerLogin = json.getString(Messages.LOGIN);
            List<BettingCard> bettingCards = new LinkedList<>();
            bettingCards.add(new BettingCard("ORANGE", playerLogin));
            bettingCards.add(new BettingCard("BLUE", playerLogin));
            bettingCards.add(new BettingCard("YELLOW", playerLogin));
            bettingCards.add(new BettingCard("GREEN", playerLogin));
            bettingCards.add(new BettingCard("WHITE", playerLogin));

            players.add(new Player(ps, playerLogin , new BettingCards(bettingCards), new DesertTile(playerLogin, new DesertTileObserver())));
        }
        return new Players(players);
    }

    private static Cubes getCubes() {
        List<Cube> cubeList = new LinkedList<>();
        cubeList.add(new Cube("ORANGE"));
        cubeList.add(new Cube("BLUE"));
        cubeList.add(new Cube("YELLOW"));
        cubeList.add(new Cube("GREEN"));
        cubeList.add(new Cube("WHITE"));

        return new Cubes(cubeList, new CubesObserver());
    }

    private static Board getBoard() {
        List<Field> fieldList = new LinkedList<>();
        for (int i = 0; i < 16; i++) {
            fieldList.add(new Field());
        }
        Fields fields = new Fields(fieldList, new FieldsObserver());

        List<StackOfBettingTile> stackList = new LinkedList<>();
        List<BettingTile> orangeBettingTiles = new LinkedList<>();
        orangeBettingTiles.add(new BettingTile("ORANGE", 5, new BettingTileObserver()));
        orangeBettingTiles.add(new BettingTile("ORANGE", 3, new BettingTileObserver()));
        orangeBettingTiles.add(new BettingTile("ORANGE", 2, new BettingTileObserver()));
        stackList.add(new StackOfBettingTile(orangeBettingTiles));

        List<BettingTile> blueBettingTiles = new LinkedList<>();
        blueBettingTiles.add(new BettingTile("BLUE", 5, new BettingTileObserver()));
        blueBettingTiles.add(new BettingTile("BLUE", 3, new BettingTileObserver()));
        blueBettingTiles.add(new BettingTile("BLUE", 2, new BettingTileObserver()));
        stackList.add(new StackOfBettingTile(blueBettingTiles));

        List<BettingTile> yellowBettingTiles = new LinkedList<>();
        yellowBettingTiles.add(new BettingTile("YELLOW", 5, new BettingTileObserver()));
        yellowBettingTiles.add(new BettingTile("YELLOW", 3, new BettingTileObserver()));
        yellowBettingTiles.add(new BettingTile("YELLOW", 2, new BettingTileObserver()));
        stackList.add(new StackOfBettingTile(yellowBettingTiles));

        List<BettingTile> greenBettingTiles = new LinkedList<>();
        greenBettingTiles.add(new BettingTile("GREEN", 5, new BettingTileObserver()));
        greenBettingTiles.add(new BettingTile("GREEN", 3, new BettingTileObserver()));
        greenBettingTiles.add(new BettingTile("GREEN", 2, new BettingTileObserver()));
        stackList.add(new StackOfBettingTile(greenBettingTiles));

        List<BettingTile> whiteBettingTiles = new LinkedList<>();
        whiteBettingTiles.add(new BettingTile("WHITE", 5, new BettingTileObserver()));
        whiteBettingTiles.add(new BettingTile("WHITE", 3, new BettingTileObserver()));
        whiteBettingTiles.add(new BettingTile("WHITE", 2, new BettingTileObserver()));
        stackList.add(new StackOfBettingTile(whiteBettingTiles));

        BettingTiles stacks = new BettingTiles(stackList);

        return new Board(fields, stacks);
    }
}
