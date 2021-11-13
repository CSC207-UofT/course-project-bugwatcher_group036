package Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

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
     * Construct the Deck in UNO card game with given arraylist of cards.
     *
     */
    public Deck(ArrayList<Card> cards){
        this.used = new ArrayList<>();
        this.unused = cards;
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
    public void addcard(Card card) {
        unused.add(card);
    }

    /**
     * Get the used card in the deck.
     *
     * @return Used card in the deck.
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
     * Get the unused card in the deck.
     *
     * @return Unused card in the deck.
     */
    public ArrayList<Card> getUnusedCardDeck(){
        return unused;
    }

    /**
     * Set the unused card in the deck.
     *
     * @param replacement the new deck to replace the old one
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

    /**
     * This method is added for test, which return a deck contains 40 cards.
     *
      * @return A deck contain 40 cards.
     */
    public ArrayList<Card> setDefaultDeck(){
        String[] colors = {"red", "green", "blue", "yellow"};
        for (String color : colors) {
            for (int i = 0; i < 10; i++) {
                Card newCard = new NumberCard(color, i, color + i);
                unused.add(newCard);
            }
        }

        return unused;
    }

}
