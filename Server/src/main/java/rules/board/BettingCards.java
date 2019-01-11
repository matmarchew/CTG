package rules.board;

import java.util.LinkedList;
import java.util.List;

public class BettingCards {
    private final List<BettingCard> bettingCardsInGame;
    private final List<BettingCard> usedBettingCards;

    public BettingCards(List<BettingCard> bettingCards) {
        this.bettingCardsInGame = bettingCards;
        usedBettingCards = new LinkedList<>();
    }

    public BettingCard getBettingCardInColor(String bettingCardColor) {
        BettingCard usedBettingCard =  bettingCardsInGame.stream().filter(bettingCard -> bettingCard.equals(bettingCardColor)).findFirst().get();
        usedBettingCards.add(usedBettingCard);
        return usedBettingCard;
    }

    public List<BettingCard> getUsedBettingCards() {
        return new LinkedList<>(usedBettingCards);
    }
}
