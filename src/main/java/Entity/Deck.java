package Entity;

import java.io.*;
import java.util.*;

public class Deck implements Serializable{

    private ArrayList<Card> used;
    private ArrayList<Card> unused;

    /**
     * Construct a empty deck in UNO card game.
     *
     */
    public Deck(){
        this.used = new ArrayList<>();
        this.unused = new ArrayList<>();
    }

    /**
     * Check whether the unused_card_deck is empty.
     *
     * If the card deck is empty, Placed all the used card into the card deck.
     */
    public boolean isEmpty(){
        return unused.isEmpty();
        }

    /**
     * Add a card into the used_card_deck.
     *
     */
    public void addCard(Card card) {
        unused.add(card);
    }

    /**
     * Get the numbers of card in a card deck.
     *
     * @return The number of card in a card deck.
     */
    public int numOfCards(ArrayList<Card> deck) {
        return deck.size();
    }

    /**
     * Get the number of used card in the deck.
     *
     * @return Number of used card in the deck.
     */
    public ArrayList<Card> getUsedCardDeck(){
        return used;
    }

    /**
     * Set the used card deck.
     *
     * @param replacement the new deck to replace the old one.
     */
    public void setUsedCardDeck(ArrayList<Card> replacement){
        used = replacement;
    }

    /**
     * Get the number of unused card in the deck.
     *
     * @return umber of unused card in the deck.
     */
    public ArrayList<Card> getUnusedCardDeck(){
        return unused;
    }

    /**
     * Set the unused card deck.
     *
     * @param replacement the new deck to replace the old one.
     */
    public void setUnusedCardDeck(ArrayList<Card> replacement){
        unused = replacement;
    }

    /**
     * Draw card form the unused card deck.
     *
     * @return The card that drawn from unused deck.
     */
    public Card drawCardFromUnusedDeck() {
        Random rand = new Random();
        int index = rand.nextInt(unused.size());
        return unused.remove(index);
    }

    /**
     * Shuffle the card from the used card deck to the unused card deck.
     *
     * @return Whether successful or not.
     */
    public boolean shuffleFromUsedToUnused() {
        if (used.isEmpty()) {
            return false;
        } else {
            ArrayList<Card> temp = unused;
            unused = used;
            used = temp;
            return true;
        }

    }

    /**
     * Put card in to the used Card deck.
     *
     * @param card The card that will be put.
     */
    public void putCardToUsedDeck(Card card) {
        used.add(card);
    }

}
