package rules.action;

import rules.board.BettingCard;
import rules.board.Board;
import rules.players.Player;

public class PutBettingCardToFinalBettingStackAction implements PlayerAction {
    private final Board board;
    private final Player player;
    private final String color;
    private final String finalBettingStack;

    public PutBettingCardToFinalBettingStackAction(Board board, Player player, String color, String finalBettingStack) {
        this.board = board;
        this.player = player;
        this.color = color;
        this.finalBettingStack = finalBettingStack;
    }

    @Override
    public void performAction() {
        BettingCard bettingCard = player.getBettingCardInColor(color);
        board.addBettingCardToStack(bettingCard, finalBettingStack);
    }
}
