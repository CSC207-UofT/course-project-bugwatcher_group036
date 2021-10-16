package Entity;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {


    @Test
    public void TestisEmpty() {
        Deck d1 = new Deck();
        assertTrue(d1.isEmpty());

    }

    @Test
    public void TestnumOfCards() {
        Deck d1 = new Deck();
        String[] colors = {"red", "green", "blue", "yellow"};
        for (String color : colors) {
            for (int i = 0; i < 10; i++) {
                Card newCard = new NumberCard(color, i, color + i);
                d1.getUnusedCardDeck().add(newCard);
            }
        }
        assertEquals(40, d1.numOfCards(d1.getUnusedCardDeck()));
    }

    @Test
    public void TestgetUsedCardDeck() {
        Deck d1 = new Deck();
        ArrayList<Card> a1 = new ArrayList<>();
        assertEquals(a1, d1.getUsedCardDeck());
    }

    @Test
    public void TestsetUsedCardDeck() {
        Deck d1 = new Deck();
        ArrayList<Card> a1 = new ArrayList<>();
        Deck d2 = new Deck();
        d1.setUsedCardDeck(a1);
        assertEquals(a1, d1.getUsedCardDeck());
    }

    @Test
    public void TestgetUnusedCardDeck() {
        Deck d1 = new Deck();
        ArrayList<Card> a1 = d1.getUnusedCardDeck();
        assertEquals(a1, d1.getUnusedCardDeck());
    // Need to implement ToCompare
    }

    @Test
    public void TestsetUnusedCardDeck() {
        Deck d1 = new Deck();
        ArrayList<Card> a1 = new ArrayList<>();
        d1.setUnusedCardDeck(a1);
        assertEquals(a1, d1.getUnusedCardDeck());
    }

    @Test
    public void TestdrawCardFromUnusedDeck() {
        Deck d1 = new Deck();
        String[] colors = {"red", "green", "blue", "yellow"};
        for (String color : colors) {
            for (int i = 0; i < 10; i++) {
                Card newCard = new NumberCard(color, i, color + i);
                d1.getUnusedCardDeck().add(newCard);
            }
        }
        Card c1 = d1.drawCardFromUnusedDeck();
        assertFalse(d1.getUnusedCardDeck().contains(c1));
    }

    @Test
    public void TestshuffleFromUsedToUnused() {
        Deck d1 = new Deck();
        assertFalse(d1.shuffleFromUsedToUnused());
    }

    @Test
    public void putCardToUsedDeck() {
        Deck d1 = new Deck();
        String[] colors = {"red", "green", "blue", "yellow"};
        for (String color : colors) {
            for (int i = 0; i < 10; i++) {
                Card newCard = new NumberCard(color, i, color + i);
                d1.getUnusedCardDeck().add(newCard);
            }
        }
        Card c1 = d1.drawCardFromUnusedDeck();
        d1.putCardToUsedDeck(c1);
        assertTrue(d1.getUsedCardDeck().contains(c1));
    }
}