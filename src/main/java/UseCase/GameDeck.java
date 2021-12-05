package UseCase;

import Entity.Deck;

import java.util.ArrayList;
import java.util.Random;

public class GameDeck {

    private Deck deck;

    public GameDeck() {
        this.deck = new Deck();
    }

    /**
     * To check whether the unused_card_deck is empty.
     *
     * If the card deck is empty, Placed all the used card into the card deck.
     */
    public boolean isEmpty(){
        return deck.isEmpty();
    }

    /**
     * getter
     * @return unused_card_deck
     */
    public ArrayList<String> getUnusedCardDeck(){
        return deck.getUnusedCardDeck();
    }

    /**
     * when a deck is empty draw a card from a new deck
     */
    public String drawCardFromUnusedDeck() {
        return deck.drawCardFromUnusedDeck();
    }

    /**
     * after draw card new deck become a used deck
     */
    public void shuffleFromUsedToUnused() {
        deck.shuffleFromUsedToUnused();
    }

    /**
     * add card to UsedDeck
     */
    public void putCardToUsedDeck(String c) {
        deck.putCardToUsedDeck(c);
    }
}