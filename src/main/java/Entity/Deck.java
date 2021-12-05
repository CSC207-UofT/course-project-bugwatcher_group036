package Entity;

import Controller.Readfile;

import java.io.*;
import java.util.*;

public class Deck {

    private ArrayList<String> used;
    private ArrayList<String> unused;

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
     * getter
     * @return unused_card_deck
     */
    public ArrayList<String> getUnusedCardDeck(){
        return unused;
    }

    /**
     * when a deck is empty draw a card from a new deck
     */
    public String drawCardFromUnusedDeck() {
        if (unused.size() == 0) {
            return null;
        }
        Random rand = new Random();
        int index = rand.nextInt(unused.size());
        return unused.remove(index);
    }

    /**
     * after draw card new deck become a used deck
     */
    public void shuffleFromUsedToUnused() {
        unused = used;
        used = new ArrayList<>();
    }

    /**
     * add card to UsedDeck
     */
    public void putCardToUsedDeck(String c) {
        used.add(c);
    }
}
