package UseCase;

import Entity.Card;
import Entity.Deck;
import java.util.ArrayList;

public class DeckUseCase {

    private Deck d;

    public DeckUseCase() {
        this.d = new Deck();
    }

    public int[] returnDeckInfo() {
        int[] res = new int[2];
        res[0] = d.getUsedCardDeck().size();
        res[1] = d.getUnusedCardDeck().size();
        return res;
    }

    public void shuffleFromUsedToUnused() {
        d.shuffleFromUsedToUnused();
    }

    public void initializeCard(ArrayList<Card> cards) {
        d.setUnusedCardDeck(cards);
    }

    public void putCardToUsedDeck(Card c) {
        d.putCardToUsedDeck(c);
    }

    public Card drawCardFromUnusedDeck() {
        if (d.getUnusedCardDeck().size() == 0) {
            boolean noCard = d.shuffleFromUsedToUnused();
            if  (!noCard) {
                return new Card(); // shouldn't we throw exception here?
            }
        }
        return d.drawCardFromUnusedDeck();
    }

    public boolean checkShuffle() {
        return d.getUnusedCardDeck().size() == 0;
    }

    public Card extractCard(ArrayList<Card> cards, String id) {
        for (Card c : cards) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return new Card();
    }

    public boolean compareNew(Card c) {
        return c.getColor().equals("black");
    }

}
