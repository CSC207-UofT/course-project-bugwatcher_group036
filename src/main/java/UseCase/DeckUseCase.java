package UseCase;

import Entity.Card;
import Entity.Deck;
import Entity.FunctionCard;
import Entity.NumberCard;

import java.util.ArrayList;

public class DeckUseCase {

    private Deck deck;

    public DeckUseCase() {
        this.deck = new Deck();
        String[] colors = {"red", "green", "blue", "yellow"};
        for (String color : colors) {
            for (int i = 0; i < 10; i++) {
                Card newCard = new NumberCard(color, i, color + i);
                deck.getUnusedCardDeck().add(newCard);
            }
        }
    }

    public int[] returnDeckInfo() {
        int[] res = new int[2];
        res[0] = deck.getUsedCardDeck().size();
        res[1] = deck.getUnusedCardDeck().size();
        return res;
    }

    public void shuffleFromUsedToUnused() {
        deck.shuffleFromUsedToUnused();
    }

    public void initializeCard(ArrayList<Card> cards) {
        deck.setUnusedCardDeck(cards);
    }

    public void putCardToUsedDeck(Card c) {
        deck.putCardToUsedDeck(c);
    }

    public Card drawCardFromUnusedDeck() {
        if (deck.getUnusedCardDeck().size() == 0) {
            boolean noCard = deck.shuffleFromUsedToUnused();
            if  (!noCard) {
                return new Card(); // shouldn't we throw exception here?
            }
        }
        return deck.drawCardFromUnusedDeck();
    }

    public boolean checkShuffle() {
        return deck.getUnusedCardDeck().size() == 0;
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

    public String color(Card c){
        return c.getColor();
    }

    public String function(FunctionCard c){
        return c.getFunction();
    }

    public int num(NumberCard c){
        return c.getNumber();
    }

}
