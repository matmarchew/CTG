package rules.board;

import java.util.LinkedList;
import java.util.List;

public class BettingCards {
    private final List<BettingCard> bettingCards;
    private final List<BettingCard> usedBettingCards;

    public BettingCards(List<BettingCard> bettingCards) {
        this.bettingCards = bettingCards;
        usedBettingCards = new LinkedList<>();
    }

    public BettingCard getBettingCardInColor(String bettingCardColor) {
        BettingCard usedBettingCard =  bettingCards.stream().filter(bettingCard -> bettingCard.equals(bettingCardColor)).findFirst().get();
        usedBettingCards.add(usedBettingCard);
        return usedBettingCard;
    }

    public List<BettingCard> getBettingCards() {
        return new LinkedList<>(usedBettingCards);
    }
}
