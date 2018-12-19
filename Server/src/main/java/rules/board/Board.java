package rules.board;

import rules.Cube;
import rules.Messages;
import rules.board.tiles.bet.BettingTiles;
import rules.board.tiles.desert.DesertTile;
import rules.players.Players;

import java.util.List;

public class Board {
    private final Fields fields;
    private final BettingTiles bettingTiles;
    private final BettingFinalResult bettingLoserStack;
    private final BettingFinalResult bettingWinnerStack;

    public Board(Fields fields, BettingTiles bettingTiles) {
        this.fields = fields;
        this.bettingTiles = bettingTiles;
        this.bettingLoserStack = new BettingFinalResult();
        this.bettingWinnerStack = new BettingFinalResult();
    }

    public void movePawns(int numberOfFieldsToMove, Cube cube) {
        int fieldPositionWithPawnInColor = fields.searchFieldOnThePawnIsStandingUsingCube(cube);
        int nextFieldPosition = numberOfFieldsToMove + fieldPositionWithPawnInColor;

        List<Pawn> pawns = fields.getPawnsToMoveFromField(fieldPositionWithPawnInColor, cube);
        fields.notifyFieldObserver(fieldPositionWithPawnInColor);
        fields.makePawnMoveToDestinationField(nextFieldPosition, pawns);
    }

    public void addBettingCardToStack(BettingCard bettingCard, String finalBettingStack) {
        addBettingCardToSuitableStack(bettingCard, finalBettingStack);
    }

    public void calculatePointsAfterGame(Pawn winnerPawn, Pawn loserPawn, Players players) {
        players.calculatePointsAfterGameForStack(winnerPawn, bettingWinnerStack);
        players.calculatePointsAfterGameForStack(loserPawn, bettingLoserStack);
    }

    public void prepareBoard() {
        bettingTiles.prepareStacksOfBettingTile();
    }

    public boolean isGameFinished() {
        return fields.isFinished();
    }

    public DesertTile getReturnedDesertTile() {
        return fields.getReturnedDesertTile();
    }

    public List<Pawn> getPawnsInOrder() {
        return fields.getPawnsInOrder();
    }

    private void addBettingCardToSuitableStack(BettingCard bettingCard, String finalBettingStack) {
        if (finalBettingStack.equals(Messages.WINNER_STACK)) {
            bettingWinnerStack.addBettingCardToFinalStack(bettingCard);
        } else {
            bettingLoserStack.addBettingCardToFinalStack(bettingCard);
        }
    }

    public BettingTiles getStacksOfBetTile() {
        return bettingTiles;
    }

    public Fields getFields() {
        return fields;
    }
}
