package Entity;

import java.util.*;

public class Deck {

    private ArrayList<Card> used_card_deck;
    private ArrayList<Card> unused_card_deck;

    /**
     * Construct the Deck in UNO card game.
     *
     */
    public Deck(){
        this.used_card_deck = new ArrayList<Card>();
        this.unused_card_deck = new ArrayList<Card>();

        String[] colors = {"red", "green", "blue", "yellow"};
        for (String color : colors) {
            for (int i = 0; i < 10; i++) {
                Card newCard = new Card(color, i, "noFunc", color + i);
                unused_card_deck.add(newCard);
            }
        }

    }

    /**
     * To check whether the unused_card_deck is empty.
     *
     * If the card deck is empty, Placed all the used card into the card deck.
     */
    public boolean is_empty(){
        return unused_card_deck.isEmpty();
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
    public int num_of_cards(ArrayList<Card> deck) {
        return deck.size();
    }

    /**
     * getter
     * @return unsed_card_deck
     */
    public ArrayList<Card> getUsed_card_deck(){
        return used_card_deck;
    }

    /**
     * setter
     * @param replacement the new deck to replace the old one
     */
    public void setUsed_card_deck(ArrayList<Card> replacement){
        used_card_deck = replacement;
    }

    /**
     * getter
     * @return unused_card_deck
     */
    public ArrayList<Card> getUnused_card_deck(){
        return unused_card_deck;
    }

    /**
     * setter
     * @param replacement the new deck to replace the old one
     */
    public void setUnused_card_deck(ArrayList<Card> replacement){
        unused_card_deck = replacement;
    }

    public Card drawCardFromUnusedDeck() {
        Random rand = new Random();
        int index = rand.nextInt(unused_card_deck.size());
        return unused_card_deck.remove(index);
    }

    public boolean shuffleFromUsedToUnused() {
        if (used_card_deck.isEmpty()) {
            return false;
        } else {
            ArrayList<Card> temp = unused_card_deck;
            unused_card_deck = used_card_deck;
            used_card_deck = temp;
            return true;
        }

    }

    public void putCardToUsedDeck(Card c) {
        used_card_deck.add(c);
    }

}
