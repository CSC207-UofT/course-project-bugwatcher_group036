package UseCase;

import Entity.Deck;

import java.util.ArrayList;

/**
 * The GameDeck.
 */
public class GameDeck {

    private final Deck deck;

    /**
     * Intialize GameDeck.
     * @param gateway the gateway interface for reading file.
     */
    public GameDeck(ReadFile gateway) {
        this.deck = new Deck();
        ArrayList<String> unused = gateway.readFromFile();
        deck.setUnused(unused);
    }

    /**
     * To check whether the unused_card_deck is empty.
     * If the card deck is empty, Placed all the used card into the card deck.
     */
    public boolean unusedIsEmpty(){
        return deck.unusedIsEmpty();
    }

    public boolean bothIsEmpty() {
        return deck.bothIsEmpty();
    }

    /**
     * Getter method for unused card.
     * @return the card deck that has no used card.
     */
    public ArrayList<String> getUnusedCardDeck(){
        return deck.getUnusedCardDeck();
    }

    /**
     * Draw a card from the unused card deck.
     * @return the card that draw from the unused card deck.
     */
    public String drawCardFromUnusedDeck() {
        return deck.drawCardFromUnusedDeck();
    }

    /**
     * To shuffle all cards from used card to unused card.
     */
    public void shuffleFromUsedToUnused() {
        deck.shuffleFromUsedToUnused();
    }

    /**
     * Add card to UsedDeck.
     */
    public void putCardToUsedDeck(String c) {
        deck.putCardToUsedDeck(c);
    }


}
