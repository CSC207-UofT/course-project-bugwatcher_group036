package main.UseCase;

import java.util.Random;
import main.Entity.Card;
import main.Entity.Deck;
import java.util.ArrayList;

public class DeckUseCase {

    private Deck d;

    public DeckUseCase() {
        this.d = new Deck();
    }

    public int[] returnDeckInfo() {
        int[] res = new int[2];
        res[0] = d.getUsed_card_deck().size();
        res[1] = d.getUnused_card_deck().size();
        return res;
    }

//    public void shuffleFromUsedToUnused() {
//        ArrayList<Card> temp;
//        temp = d.getUsed_card_deck();
//        d.setUnused_card_deck(temp);
//        d.setUsed_card_deck(new ArrayList<Card>());
//    }

    public void shuffleFromUsedToUnused() {
        d.shuffleFromUsedToUnused();
    }

    public void initializeCard(ArrayList<Card> cards) {
        d.setUnused_card_deck(cards);
    }

//    public void putCardToUsedDeck(Card c) {
//        d.getUsed_card_deck().add(c);
//    }

    public void putCardToUsedDeck(Card c) {
        d.putCardToUsedDeck(c);
    }

    public Card drawCardFromUnusedDeck() {
        if (d.getUnused_card_deck().size() == 0) {
            boolean noCard = d.shuffleFromUsedToUnused();
            if (!noCard) {
                return new Card();
            }
        }
        return d.drawCardFromUnusedDeck();
    }

    public boolean checkShuffle() {
        return d.getUnused_card_deck().size() == 0;
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
