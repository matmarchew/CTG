import communication.WebPageSocket;
import communication.observer.*;
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

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        WebPageSocket webPageSocket= new WebPageSocket();

        Game game = new Game(getBoard(webPageSocket),
                            getConnectedPlayers(1, webPageSocket),
                            getCubes(webPageSocket),
                            new GameObserver(webPageSocket));

        game.startGame();
        game.notifyGameObserver();
    }

    private static Players getConnectedPlayers(int numberOfPlayers, WebPageSocket webPageSocket) {
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

            players.add(new Player(ps, playerLogin ,
                                    new BettingCards(bettingCards),
                                    new DesertTile(playerLogin, new DesertTileObserver(webPageSocket)),
                                    new PlayerObserver(webPageSocket)));
        }
        return new Players(players);
    }

    private static Cubes getCubes(WebPageSocket webPageSocket) {
        List<Cube> cubeList = new LinkedList<>();
        cubeList.add(new Cube("ORANGE", new CubeObserver(webPageSocket)));
        cubeList.add(new Cube("BLUE", new CubeObserver(webPageSocket)));
        cubeList.add(new Cube("YELLOW", new CubeObserver(webPageSocket)));
        cubeList.add(new Cube("GREEN", new CubeObserver(webPageSocket)));
        cubeList.add(new Cube("WHITE", new CubeObserver(webPageSocket)));

        return new Cubes(cubeList, new CubesObserver(webPageSocket));
    }

    private static Board getBoard(WebPageSocket webPageSocket) {
        List<Field> fieldList = new LinkedList<>();
        for (int i = 0; i < 16; i++) {
            fieldList.add(new Field());
        }
        Fields fields = new Fields(fieldList, new FieldsObserver(webPageSocket));

        List<StackOfBettingTile> stackList = new LinkedList<>();
        List<BettingTile> orangeBettingTiles = new LinkedList<>();
        orangeBettingTiles.add(new BettingTile("ORANGE", 5, new BettingTileObserver(webPageSocket)));
        orangeBettingTiles.add(new BettingTile("ORANGE", 3, new BettingTileObserver(webPageSocket)));
        orangeBettingTiles.add(new BettingTile("ORANGE", 2, new BettingTileObserver(webPageSocket)));
        stackList.add(new StackOfBettingTile(orangeBettingTiles));

        List<BettingTile> blueBettingTiles = new LinkedList<>();
        blueBettingTiles.add(new BettingTile("BLUE", 5, new BettingTileObserver(webPageSocket)));
        blueBettingTiles.add(new BettingTile("BLUE", 3, new BettingTileObserver(webPageSocket)));
        blueBettingTiles.add(new BettingTile("BLUE", 2, new BettingTileObserver(webPageSocket)));
        stackList.add(new StackOfBettingTile(blueBettingTiles));

        List<BettingTile> yellowBettingTiles = new LinkedList<>();
        yellowBettingTiles.add(new BettingTile("YELLOW", 5, new BettingTileObserver(webPageSocket)));
        yellowBettingTiles.add(new BettingTile("YELLOW", 3, new BettingTileObserver(webPageSocket)));
        yellowBettingTiles.add(new BettingTile("YELLOW", 2, new BettingTileObserver(webPageSocket)));
        stackList.add(new StackOfBettingTile(yellowBettingTiles));

        List<BettingTile> greenBettingTiles = new LinkedList<>();
        greenBettingTiles.add(new BettingTile("GREEN", 5, new BettingTileObserver(webPageSocket)));
        greenBettingTiles.add(new BettingTile("GREEN", 3, new BettingTileObserver(webPageSocket)));
        greenBettingTiles.add(new BettingTile("GREEN", 2, new BettingTileObserver(webPageSocket)));
        stackList.add(new StackOfBettingTile(greenBettingTiles));

        List<BettingTile> whiteBettingTiles = new LinkedList<>();
        whiteBettingTiles.add(new BettingTile("WHITE", 5, new BettingTileObserver(webPageSocket)));
        whiteBettingTiles.add(new BettingTile("WHITE", 3, new BettingTileObserver(webPageSocket)));
        whiteBettingTiles.add(new BettingTile("WHITE", 2, new BettingTileObserver(webPageSocket)));
        stackList.add(new StackOfBettingTile(whiteBettingTiles));

        BettingTiles stacks = new BettingTiles(stackList);

        return new Board(fields, stacks);
    }
}
