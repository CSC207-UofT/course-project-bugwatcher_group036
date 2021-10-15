package Entity;

import java.util.*;

public class Deck {

    private ArrayList<Card> used;
    private ArrayList<Card> unused;

    /**
     * Construct the Deck in UNO card game.
     *
     */
    public Deck(){
        this.used = new ArrayList<Card>();
        this.unused = new ArrayList<Card>();

//        String[] colors = {"red", "green", "blue", "yellow"};
//        for (String color : colors) {
//            for (int i = 0; i < 10; i++) {
//                Card newCard = new NumberCard(color, i, color + i);
//                unused.add(newCard);
//            }
//        }

    }

    /**
     * To check whether the unused_card_deck is empty.
     *
     * If the card deck is empty, Placed all the used card into the card deck.
     */
    public boolean isEmpty(){
        return unused.isEmpty();
        }

//    /**
//     * To remove a card from the unused card deck.
//     *
//     * @return Card that is removed from the unused card deck
//     */
//    public Card remove_card() {
//        return unused_card_deck.remove(0);
//    }


//    /**
//     * To add a card into the used_card_deck.
//     *
//     */
//    public void add_card(Card card) {
//        used_card_deck.add(card);
//    }

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

    public void putCardToUsedDeck(Card c) {
        used.add(c);
    }

}
