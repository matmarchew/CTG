package rules.board;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class BettingFinalResult {
    private final List<BettingCard> bettingCards;

    public BettingFinalResult() {
        this.bettingCards = new LinkedList<>();
    }

    public List<String> getPlayersLoginFromBettingCardsInOrder(Pawn pawn, boolean isWinner) {
        return bettingCards.stream()
                .filter(bettingCard -> isBettingCardColorIsSuitable(bettingCard, pawn, isWinner))
                .map(bettingCard -> bettingCard.getPlayerLogin())
                .collect(Collectors.toList());
    }

    public void addBettingCardToFinalStack(BettingCard bettingCard) {
        bettingCards.add(bettingCard);
    }

    private boolean isBettingCardColorIsSuitable(BettingCard bettingCard, Pawn pawn, boolean isWinner) {
        return isWinner ? bettingCard.equals(pawn) : !bettingCard.equals(pawn);
    }
}
