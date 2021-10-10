
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Deck {

    private ArrayList<Card> used_card_deck;
    private ArrayList<Card> unused_card_deck;

    /**
     * Construct the Deck in UNO card game.
     *
     */
    public Deck(){
        this.unused_card_deck = new ArrayList<Card>();
        this.used_card_deck = new ArrayList<Card>();
    }

    /**
     * To check whether the unused_card_deck is empty.
     *
     * If the card deck is empty, Placed all the used card into the card deck.
     */
    public boolean is_empty(){
        return unused_card_deck.isEmpty();
        }

    /**
     * To remove a card from the unused card deck.
     *
     * @return Card that is removed from the unused card deck
     */
    public Card remove_card() {
        return unused_card_deck.remove(0);
    }


    /**
     * To add a card into the used_card_deck.
     *
     *
     */
    public void add_card(Card card) {
        used_card_deck.add(card);
    }

    /**
     * To remove a card from the unused card deck.
     *
     * @return the numbers of card in a card deck.
     */
    public int num_of_cards(ArrayList<Card> deck) {
        return deck.size();
    }
}
