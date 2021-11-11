package UseCase;

import Entity.*;

import java.io.IOException;
import java.util.ArrayList;

public class DeckManager {

    private Deck d;

    public DeckManager() {
        this.d = new Deck();
    }

    public DeckManager(Deck d) {
        this.d = d;
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
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getId().equals(id)) {
                return cards.get(i);
            }
        }
        return new Card();
    }

    public boolean whetherNull(Card c) {
        return c.getId().equals("nullid");
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
        || c2.getId().equals("nullid");
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
        d.addcard(c);
    }

    public Deck getD(){return d;}
}
