package rules.action;

import rules.board.BettingCard;
import rules.board.BettingFinalResult;
import rules.players.Player;

public class PutBettingCardToFinalBettingStackAction implements PlayerAction {
    private final Player player;
    private final String color;
    private final BettingFinalResult finalBettingStack;

    public PutBettingCardToFinalBettingStackAction(Player player, String color, BettingFinalResult finalBettingStack) {
        this.player = player;
        this.color = color;
        this.finalBettingStack = finalBettingStack;
    }

    @Override
    public void performAction() {
        BettingCard bettingCard = player.getBettingCardInColor(color);
        finalBettingStack.addBettingCardToFinalStack(bettingCard);
    }
}
