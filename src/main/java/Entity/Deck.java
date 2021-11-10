package Entity;

import java.io.*;
import java.util.*;

public class Deck implements Serializable{

    private ArrayList<Card> used;
    private ArrayList<Card> unused;

    /**
     * Construct the Deck in UNO card game.
     *
     */
    public Deck(){
        this.used = new ArrayList<>();
        this.unused = new ArrayList<>();
    }

    /**
     * To check whether the unused_card_deck is empty.
     *
     * If the card deck is empty, Placed all the used card into the card deck.
     */
    public boolean isEmpty(){
        return unused.isEmpty();
        }

    /**
     * To add a card into the used_card_deck.
     *
     */
    public void addCard(Card card) {
        unused.add(card);
    }

    /**
     * To return the numbers of card in a card deck.
     *
     * @return the numbers of card in a card deck.
     */
    public int numOfCards(ArrayList<Card> deck) {
        return deck.size();
    }

    /**
     * getter
     * @return unsed_card_deck
     */
    public ArrayList<Card> getUsedCardDeck(){
        return used;
    }

    /**
     * setter
     * @param replacement the new deck to replace the old one
     */
    public void setUsedCardDeck(ArrayList<Card> replacement){
        used = replacement;
    }

    /**
     * getter
     * @return unused_card_deck
     */
    public ArrayList<Card> getUnusedCardDeck(){
        return unused;
    }

    /**
     * setter
     * @param replacement the new deck to replace the old one
     */
    public void setUnusedCardDeck(ArrayList<Card> replacement){
        unused = replacement;
    }

    public Card drawCardFromUnusedDeck() {
        Random rand = new Random();
        int index = rand.nextInt(unused.size());
        return unused.remove(index);
    }

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

    public void putCardToUsedDeck(Card card) {
        used.add(card);
    }

}
