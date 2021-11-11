package UseCase;

import Entity.*;

import java.util.ArrayList;

public class DeckManager {

    private Deck deck;

    public DeckManager() {
        this.deck = new Deck();
    }

    public DeckManager(Deck deck) {
        this.deck = deck;
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
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getId().equals(id)) {
                return cards.get(i);
            }
        }
        return new Card();
    }

    public boolean whetherNull(Card c) {
        return c.getId().equals("null");
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

    //make it a static method so use it in Basic Operations
    public static boolean compareTwoCardsHaveSameFeature(Card lastCard, Card c2, String currentColor) {
        if (lastCard.getColor().equals("black")){
            return lastCard.getFeature().equals(c2.getFeature()) ||
                    c2.getColor().equals(currentColor);
        }
        return lastCard.getFeature().equals(c2.getFeature())|| lastCard.getColor().equals(c2.getColor())
        || c2.getId().equals("null");
    }

    public boolean compareTwoCardsHaveSameColor(Card c1, Card c2) {
        return c1.getColor().equals(c2.getColor());
    }

    public Card createNullCard() {
        return new Card();
    }

    public Card createColorCard(String color) {
        return new Card(color, color);
    }

    public String feature(Card c) {
        return c.getFeature();
    }

    public void addCard(Card c) {
        deck.addCard(c);
    }
}
