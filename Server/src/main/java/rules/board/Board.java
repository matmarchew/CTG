package rules.board;

import communication.observer.BoardObserver;
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
    private final BoardObserver boardObserver;

    public Board(Fields fields, BettingTiles bettingTiles, BoardObserver boardObserver) {
        this.fields = fields;
        this.bettingTiles = bettingTiles;
        this.boardObserver = boardObserver;
        this.bettingLoserStack = new BettingFinalResult();
        this.bettingWinnerStack = new BettingFinalResult();
    }

    public void calculatePointsAfterGame(Pawn winnerPawn, Pawn loserPawn, Players players) {
        players.calculatePointsAfterGameForStack(winnerPawn, bettingWinnerStack);
        players.calculatePointsAfterGameForStack(loserPawn, bettingLoserStack);
    }

    public List<Pawn> getPawnsInOrder() {
        return fields.getPawnsInOrder();
    }

    public DesertTile getReturnedDesertTile() {
        return fields.getReturnedDesertTile();
    }

    public BettingFinalResult getSuitableFinalBettingStack(String finalBettingStack) {
        if (finalBettingStack.equals(Messages.WINNER_STACK)) {
            return bettingWinnerStack;
        } else {
            return bettingLoserStack;
        }
    }

    public boolean isGameFinished() {
        return fields.isFinished();
    }

    public void notifyBoardObserver() {
        boardObserver.createInfoForWeb();
    }

    public void moveThePawns(int numberOfFieldsToMove, String pawnColor) {
        fields.moveThePawns(numberOfFieldsToMove, pawnColor);
    }

    public void prepareBoard() {
        bettingTiles.prepareStacksOfBettingTile();
    }

    public Fields getFields() {
        return fields;
    }

    public BettingTiles getStacksOfBetTile() {
        return bettingTiles;
    }

}
